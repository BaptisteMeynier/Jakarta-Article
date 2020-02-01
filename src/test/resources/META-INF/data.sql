
--- FISH_FAMILY ---

INSERT INTO FAMILY (id,name,water_type) VALUES (1,'Cichlidae',1);
INSERT INTO FAMILY (id,name,water_type) VALUES (2,'Cyprinidae',1);
INSERT INTO FAMILY (id,name,water_type) VALUES (3,'Characidae',1);
INSERT INTO FAMILY (id,name,water_type) VALUES (4,'Pomacentridae',2);

--- FISH ---

INSERT INTO FISH (id, name, temperature, price, family_id) VALUES (1,'Discus','24',25,1);
INSERT INTO FISH (id, name, temperature, price, family_id) VALUES (2,'Scalaire','24',18,1);
INSERT INTO FISH (id, name, temperature, price, family_id) VALUES (3,'Pelmato','25',8,1);
INSERT INTO FISH (id, name, temperature, price, family_id) VALUES (4,'Neolamprologus','25',6,1);
INSERT INTO FISH (id, name, temperature, price, family_id) VALUES (5,'Brème commune','15',2,2);
INSERT INTO FISH (id, name, temperature, price, family_id) VALUES (6,'Spirlin','15',2,2);
INSERT INTO FISH (id, name, temperature, price, family_id) VALUES (7,'Barbus raye','23',3.4,2);
INSERT INTO FISH (id, name, temperature, price, family_id) VALUES (8,'Suceur de pierres ','21',1.90,2);
INSERT INTO FISH (id, name, temperature, price, family_id) VALUES (9,'carpe Koi','19',14.9,2);
INSERT INTO FISH (id, name, temperature, price, family_id) VALUES (10,'Neon bleu','24',0.4,3);
INSERT INTO FISH (id, name, temperature, price, family_id) VALUES (11,'Cardinalis','24',0.4,3);
INSERT INTO FISH (id, name, temperature, price, family_id) VALUES (12,'Tetra argente','24',0.5,3);
INSERT INTO FISH (id, name, temperature, price, family_id) VALUES (13,'Nez rouge','24',0.4,3);
INSERT INTO FISH (id, name, temperature, price, family_id) VALUES (14,'Tetra joyau','24',2.6,3);
INSERT INTO FISH (id, name, temperature, price, family_id) VALUES (15,'Fantome rouge','24',4.2,3);
INSERT INTO FISH (id, name, temperature, price, family_id) VALUES (16,'Le demoiselle noire','24',18.6,4);
INSERT INTO FISH (id, name, temperature, price, family_id) VALUES (17,'Poisson clown','22',8.2,4);
INSERT INTO FISH (id, name, temperature, price, family_id) VALUES (18,'Sergent major','21',13,4);
INSERT INTO FISH (id, name, temperature, price, family_id) VALUES (19,'Demoiselle bicolore','19',18,4);


--- SHOP ---

INSERT INTO SHOP (id, name, email, creation, phone_number, account) VALUES (1,'Magic Fish','magic.fish@mail.com',TO_DATE('01/12/2000', 'DD/MM/YYYY'),'0102030405',4200);

--- STOCK ---

INSERT INTO STOCK (shop_id, fish_id, quantity) VALUES (1,1,12);
INSERT INTO STOCK (shop_id, fish_id, quantity) VALUES (1,2, 5);
INSERT INTO STOCK (shop_id, fish_id, quantity) VALUES (1,3, 9);
INSERT INTO STOCK (shop_id, fish_id, quantity) VALUES (1,4, 1);
INSERT INTO STOCK (shop_id, fish_id, quantity) VALUES (1,5, 15);
INSERT INTO STOCK (shop_id, fish_id, quantity) VALUES (1,6, 5);
INSERT INTO STOCK (shop_id, fish_id, quantity) VALUES (1,7, 20);
INSERT INTO STOCK (shop_id, fish_id, quantity) VALUES (1,8, 7);
INSERT INTO STOCK (shop_id, fish_id, quantity) VALUES (1,9, 8);
INSERT INTO STOCK (shop_id, fish_id, quantity) VALUES (1,10, 9);
INSERT INTO STOCK (shop_id, fish_id, quantity) VALUES (1,11, 10);
INSERT INTO STOCK (shop_id, fish_id, quantity) VALUES (1,12, 30);
INSERT INTO STOCK (shop_id, fish_id, quantity) VALUES (1,13, 24);
INSERT INTO STOCK (shop_id, fish_id, quantity) VALUES (1,14, 10);
INSERT INTO STOCK (shop_id, fish_id, quantity) VALUES (1,15, 16);
INSERT INTO STOCK (shop_id, fish_id, quantity) VALUES (1,16, 3);
INSERT INTO STOCK (shop_id, fish_id, quantity) VALUES (1,17, 7);
INSERT INTO STOCK (shop_id, fish_id, quantity) VALUES (1,18, 1);
INSERT INTO STOCK (shop_id, fish_id, quantity) VALUES (1,19, 3);