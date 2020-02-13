http://florent-dupont.blogspot.com/2014/01/arquillian-jpa-et-datasets-12-premiere.html
http://florent-dupont.blogspot.com/2014/01/arquillian-jpa-et-datasets-22-utiliser.html


sudo systemctl start docker

docker build -t jakarta-article:1.0.0 -f src/microshed-test/docker/Dockerfile .

docker run -p 8080:8080 -d -it jakarta-article:1.0.0

docker exec -it 87872a942cc7 /bin/sh

bin/asadmin start-domain

bin/asadmin deploy /home/baptiste/IdeaProjects/jakarta-article/target/jakarta-article.war

bin/asadmin deploy --force=true /home/baptiste/IdeaProjects/jakarta-article/target/jakarta-article.war

https://github.com/hantsy/ee8-sandbox/blob/master/jaxrs-sse/src/main/java/com/hantsylabs/example/ee8/jaxrs/SseCdiResource.java

https://allegro.tech/2014/10/async-rest.html

NEXT EXCEPTION MAPPER
CDI EVENT