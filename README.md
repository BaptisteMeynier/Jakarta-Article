# Fish Shop Jakarta ee 8 application

This application is a showcase of some Jakarta ee 8 features. 
I use Payara Fish micro serveur but you could take any Jakarta server implementation like Open Liberty, Wildfly, Glassfish.
Also You will find some technicals to test your application.

## Pesistence (JPA)

### Native query

```
    public Shop findShopByName(String shopName) {
        return (Shop) entityManager.createNativeQuery(String.format("select * from Shop where name = '%s'", shopName),Shop.class)
                .setParameter("name", shopName)
                .getSingleResult();
    }
```

### Named query

```
@Entity
@NamedQueries({
        @NamedQuery(name = "Fish.getAll", query = "select f from Fish f"),
        @NamedQuery(name = "Fish.findByName", query = "select f from Fish f where f.name = :fishName"),
        @NamedQuery(name = "Fish.countByFamily", query = "select count(fi) from Fish fi where fi.family.name = :familyName")
}
)
public class Fish implements Serializable {

```
```
    public int countFishByFamily(String familyName) {
        return entityManager.createNamedQuery("Fish.countByFamily", Long.class)
                .setParameter("familyName", familyName)
                .getSingleResult()
                .intValue();
    }
```

### Criteria query
Very usefull for big form
```
    public Stock findStock(String shopName, String fishName) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Stock> listCriteria = builder.createQuery(Stock.class);

        Root<Stock> listRoot = listCriteria.from(Stock.class);
        listCriteria.select(listRoot)
                .where(builder.and(
                        builder.equal(listRoot.get("shop").get("name"),shopName),
                        builder.equal(listRoot.get("fish").get("name"),fishName)
                        ));
        TypedQuery<Stock> query = entityManager.createQuery(listCriteria);
        return query.getSingleResult();
    }
```

## Injection (CDI)

### Service
```
    @Inject
    private FishServiceImpl fishService;
```
### Producer
Allow to define yourself instantiation of injected bean
```
@ApplicationScoped
public class EntityManagerConfigurator {

    @PersistenceContext
    EntityManager em;

    @Produces
    public EntityManager configureEm() {
        return em;
    }
}
```
### Event
Allow to perform some reactive treatment.
```
    @Inject
    private Event<StockEvent> stockEvent;

    @Override
    public float buy(String shopName, String fishName, int quantity) {
        float price = fishService.buy(shopName, fishName, quantity);
        stockEvent.fireAsync(new StockEvent(ShopTransactionType.PURCHASE,price));
        return price;
    }
```

```
    public void getStockEvent(@ObservesAsync StockEvent event) {
        eventsMap.forEach((key, value) -> value.add(event));
    }
```

### Decorator
Allow to execute code after calling of a method 
```
<?xml version="1.0" encoding="UTF-8"?>
<beans
        xmlns="http://xmlns.jcp.org/xml/ns/javaee"
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
        xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee
                      http://xmlns.jcp.org/xml/ns/javaee/beans_1_1.xsd"
        bean-discovery-mode="all">
    <decorators>
        <class>com.meynier.jakarta.decorator.FishServiceDecorator</class>
    </decorators>
</beans>
```
```
@Decorator
public abstract class FishServiceDecorator implements FishService {

    @Inject
    private Event<StockEvent> stockEvent;

    @Inject
    @Delegate
    @Any
    FishService fishService;

    @Override
    public float buy(String shopName, String fishName, int quantity) {
        float price = fishService.buy(shopName, fishName, quantity);
        stockEvent.fireAsync(new StockEvent(ShopTransactionType.PURCHASE,price));
        return price;
    }
```


## Rest Service (JAX-RS)

### Activation

```
@ApplicationPath("/petshop")
public class PetShopApplication extends Application {}
```

### Rest endpoint
```
@Valid
@Path("fish")
@ApplicationScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
public class FishResource {

    @Inject
    private FishServiceImpl fishService;

    @GET
    @Path("{fishFamily}")
    public int countByFamily(@NotBlank @PathParam("fishFamily") String fishFamily) {
        return fishService.countByFamily(fishFamily);
    }
```

### Exception mapper
Allow to catch a specific kind of exception and return a jax-rs response. 
```
@Provider
public class NoResultExceptionMapper implements ExceptionMapper<NoResultException> {

    @Override
    public Response toResponse(NoResultException arg0) {
        return Response.status(Response.Status.NOT_FOUND).build();
    }

}
```

### Async Rest endpoint
Allow to delegate the treatment to an other threadpool (usefull for long treatment)
```
    @Resource(name="concurrent/__defaultManagedExecutorService")
    private Executor executor;

    @GET
    @Path("negotiate/{fishName}")
    public void negotiatePrice(@Suspended final AsyncResponse asyncResponse) {
        this.fishService.callManager(executor)
            .thenApply(info -> asyncResponse.resume("None !!!"))
            .exceptionally(e -> asyncResponse.resume(Response.status(INTERNAL_SERVER_ERROR).entity(e).build()));

        asyncResponse.setTimeout(2000, TimeUnit.MILLISECONDS);
        asyncResponse.setTimeoutHandler(ar -> ar.resume(
                Response.status(SERVICE_UNAVAILABLE).entity("Operation timed out, manager is on a meeting").build()));
    }

```

### Server Sent Event (SSE)
Is unilaterally conversation Server to client. It's nice to avoid client pooling.

```
    @GET
    @Path("stock")
    @Produces(MediaType.SERVER_SENT_EVENTS)
    public void getStockPrices(@Context SseEventSink sseEventSink) {
        String userSession = UUID.randomUUID().toString();
        eventsMap.put(userSession, new ArrayBlockingQueue<>(20));
        try{
            StockEvent poll;
            while(Objects.nonNull(poll = eventsMap.get(userSession).poll(5, TimeUnit.SECONDS))){
                    OutboundSseEvent sseEvent = this.eventBuilder
                            .name("stock")
                            .id(poll.getUuid())
                            .mediaType(MediaType.APPLICATION_JSON_TYPE)
                            .data(StockEvent.class, poll)
                            .reconnectDelay(3000)
                            .comment("price change")
                            .build();
                    sseEventSink.send(sseEvent);
            }
        } catch (InterruptedException e) {
            LOGGER.log(Level.SEVERE,e.getMessage(),e);
        }finally {
            eventsMap.remove(userSession);
            sseEventSink.close();
        }

    }
```

# How test a Jakarta ee application ?

## Tests
### Directly by instantiate containers

For example for entity manager:
```
entityManagerFactory = Persistence.createEntityManagerFactory("JPADemo", properties);
entityManager = entityManagerFactory.createEntityManager();
```

### Mockito
Mockito of course can be also very usefull

### HK2
HK2 is an implementation of JSR-330 in a JavaSE environment.
It allow to instantiate services.

## JUnit Weld Extension
Weld is an implementation of CDI. This JUnit extension allow to easily inject your bean without start the server integraly.

## Integration Tests

### Arquillian
Arquillian download a Jakarta ee server and execute your tests on this environment.

## Smoke Tests

### Microshed
MicroShed allow to test docker image (same technology as TestContainer). 
It can interact with the image calling jax-rs methods, performing real rest calls. 
