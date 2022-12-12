;             
CREATE USER IF NOT EXISTS "SA" SALT '7249551a84887d3d' HASH '59aa820497dc1d48a3305e55687621f9334f6d01709d519e820f60fbf2195845' ADMIN;         
CREATE SEQUENCE "PUBLIC"."HIBERNATE_SEQUENCE" START WITH 51;  
CREATE MEMORY TABLE "PUBLIC"."BOOK_ORDER"(
    "ID" BIGINT NOT NULL,
    "PRICE" DOUBLE,
    "QUANTITY" BIGINT,
    "BOOK_ID" BIGINT,
    "CUSTOMER_ORDER_ID" BIGINT
); 
ALTER TABLE "PUBLIC"."BOOK_ORDER" ADD CONSTRAINT "PUBLIC"."CONSTRAINT_F" PRIMARY KEY("ID");   
-- 22 +/- SELECT COUNT(*) FROM PUBLIC.BOOK_ORDER;             
INSERT INTO "PUBLIC"."BOOK_ORDER" VALUES
(19, 10.0, 1, 10, 18),
(20, 10.0, 1, 9, 18),
(22, 10.0, 1, 10, 21),
(23, 10.0, 1, 9, 21),
(25, 10.0, 1, 10, 24),
(26, 10.0, 1, 9, 24),
(28, 10.0, 1, 5, 27),
(29, 10.0, 1, 5, 27),
(31, 10.0, 1, 7, 30),
(32, 10.0, 1, 8, 30),
(34, 10.0, 1, 6, 33),
(35, 10.0, 1, 8, 33),
(37, 10.0, 1, 12, 36),
(38, 10.0, 1, 11, 36),
(40, 10.0, 1, 12, 39),
(41, 10.0, 1, 11, 39),
(43, 10.0, 1, 13, 42),
(44, 10.0, 1, 12, 42),
(46, 10.0, 1, 13, 45),
(47, 10.0, 1, 12, 45),
(49, 10.0, 1, 13, 48),
(50, 10.0, 1, 12, 48);               
CREATE MEMORY TABLE "PUBLIC"."BOOK_STOCK"(
    "ID" BIGINT NOT NULL,
    "AUTHOR" VARCHAR(255),
    "NAME" VARCHAR(255),
    "PRICE" DOUBLE,
    "QUANTITY" BIGINT,
    "VERSION" BIGINT
);            
ALTER TABLE "PUBLIC"."BOOK_STOCK" ADD CONSTRAINT "PUBLIC"."CONSTRAINT_F0" PRIMARY KEY("ID");  
-- 12 +/- SELECT COUNT(*) FROM PUBLIC.BOOK_STOCK;             
INSERT INTO "PUBLIC"."BOOK_STOCK" VALUES
(4, 'Dostoyevski', STRINGDECODE('Su\u00e7 ve Ceza5'), 10.0, 5, 0),
(5, 'Dostoyevski', STRINGDECODE('Su\u00e7 ve Ceza5'), 10.0, 3, 1),
(6, 'Dostoyevski', STRINGDECODE('Su\u00e7 ve Ceza5'), 10.0, 4, 1),
(7, 'Dostoyevski', STRINGDECODE('Su\u00e7 ve Ceza5'), 10.0, 4, 1),
(8, 'Dostoyevski', STRINGDECODE('Su\u00e7 ve Ceza5'), 10.0, 3, 2),
(9, 'Dostoyevski', STRINGDECODE('Su\u00e7 ve Ceza5'), 10.0, 2, 3),
(10, 'Dostoyevski', STRINGDECODE('Su\u00e7 ve Ceza5'), 10.0, 2, 3),
(11, 'Dostoyevski', STRINGDECODE('Su\u00e7 ve Ceza5'), 10.0, 3, 2),
(12, 'Dostoyevski', STRINGDECODE('Su\u00e7 ve Ceza5'), 10.0, 0, 5),
(13, 'Dostoyevski', STRINGDECODE('Su\u00e7 ve Ceza5'), 10.0, 2, 3),
(14, 'Dostoyevski', STRINGDECODE('Su\u00e7 ve Ceza5'), 10.0, 5, 0),
(15, 'Dostoyevski', STRINGDECODE('Su\u00e7 ve Ceza5'), 10.0, 5, 0);
CREATE MEMORY TABLE "PUBLIC"."CUSTOMER"(
    "ID" BIGINT NOT NULL,
    "NAME" VARCHAR(255),
    "SURNAME" VARCHAR(255),
    "USER_ID" BIGINT
);          
ALTER TABLE "PUBLIC"."CUSTOMER" ADD CONSTRAINT "PUBLIC"."CONSTRAINT_5" PRIMARY KEY("ID");     
-- 1 +/- SELECT COUNT(*) FROM PUBLIC.CUSTOMER;
INSERT INTO "PUBLIC"."CUSTOMER" VALUES
(17, 'Ali', 'Veli', 16);              
CREATE MEMORY TABLE "PUBLIC"."CUSTOMER_ORDER"(
    "ID" BIGINT NOT NULL,
    "CREATE_DATE_TIME" TIMESTAMP,
    "PRICE" DOUBLE,
    "STATUS" INTEGER,
    "UPDATE_DATE_TIME" TIMESTAMP,
    "CUSTOMER_ID" BIGINT
);     
ALTER TABLE "PUBLIC"."CUSTOMER_ORDER" ADD CONSTRAINT "PUBLIC"."CONSTRAINT_3" PRIMARY KEY("ID");               
-- 11 +/- SELECT COUNT(*) FROM PUBLIC.CUSTOMER_ORDER;         
INSERT INTO "PUBLIC"."CUSTOMER_ORDER" VALUES
(18, TIMESTAMP '2022-12-11 17:30:08.489165', 20.0, 0, TIMESTAMP '2022-12-11 17:30:08.489165', 17),
(21, TIMESTAMP '2022-12-11 17:30:31.942248', 20.0, 0, TIMESTAMP '2022-12-11 17:30:31.942248', 17),
(24, TIMESTAMP '2022-12-11 17:30:32.617245', 20.0, 0, TIMESTAMP '2022-12-11 17:30:32.617245', 17),
(27, TIMESTAMP '2022-12-11 17:30:58.839914', 20.0, 0, TIMESTAMP '2022-12-11 17:30:58.839914', 17),
(30, TIMESTAMP '2022-12-11 17:31:09.328735', 20.0, 0, TIMESTAMP '2022-12-11 17:31:09.328735', 17),
(33, TIMESTAMP '2022-12-11 17:31:14.605301', 20.0, 0, TIMESTAMP '2022-12-11 17:31:14.605301', 17),
(36, TIMESTAMP '2022-12-11 17:31:24.953648', 20.0, 0, TIMESTAMP '2022-12-11 17:31:24.953648', 17),
(39, TIMESTAMP '2022-12-11 17:31:27.432486', 20.0, 0, TIMESTAMP '2022-12-11 17:31:27.432486', 17),
(42, TIMESTAMP '2022-12-11 17:31:39.268394', 20.0, 0, TIMESTAMP '2022-12-11 17:31:39.268394', 17),
(45, TIMESTAMP '2022-12-11 17:31:40.516227', 20.0, 0, TIMESTAMP '2022-12-11 17:31:40.516227', 17),
(48, TIMESTAMP '2022-12-11 17:31:41.607456', 20.0, 0, TIMESTAMP '2022-12-11 17:31:41.607456', 17);      
CREATE MEMORY TABLE "PUBLIC"."ROLE"(
    "ID" BIGINT NOT NULL,
    "NAME" VARCHAR(255),
    "USER_ID" BIGINT
);           
ALTER TABLE "PUBLIC"."ROLE" ADD CONSTRAINT "PUBLIC"."CONSTRAINT_2" PRIMARY KEY("ID");         
-- 2 +/- SELECT COUNT(*) FROM PUBLIC.ROLE;    
INSERT INTO "PUBLIC"."ROLE" VALUES
(1, 'Customer', 16),
(2, NULL, NULL);    
CREATE MEMORY TABLE "PUBLIC"."USER"(
    "ID" BIGINT NOT NULL,
    "PASSWORD" VARCHAR(255),
    "USER_NAME" VARCHAR(255)
);               
ALTER TABLE "PUBLIC"."USER" ADD CONSTRAINT "PUBLIC"."CONSTRAINT_27" PRIMARY KEY("ID");        
-- 2 +/- SELECT COUNT(*) FROM PUBLIC.USER;    
INSERT INTO "PUBLIC"."USER" VALUES
(3, '$2a$10$hZID051L2TBv20KC9jlEse/6Glt1zCXQJ8T88p2G4TG4v/CAq1kEC', 'ali'),
(16, '$2a$10$unQ0TyIucwAxJvPmIvZ69eApdYKAHG9b0gCADjq.xNoVFvEYXQfxi', 'ali18@mail.com');      
ALTER TABLE "PUBLIC"."ROLE" ADD CONSTRAINT "PUBLIC"."UK_8SEWWNPAMNGI6B1DWAA88ASKK" UNIQUE("NAME");            
ALTER TABLE "PUBLIC"."USER" ADD CONSTRAINT "PUBLIC"."UK_LQJRCOBRH9JC8WPCAR64Q1BFH" UNIQUE("USER_NAME");       
ALTER TABLE "PUBLIC"."ROLE" ADD CONSTRAINT "PUBLIC"."FK61G3AMBULT7V7NH59XIRGD9NF" FOREIGN KEY("USER_ID") REFERENCES "PUBLIC"."USER"("ID") NOCHECK;            
ALTER TABLE "PUBLIC"."BOOK_ORDER" ADD CONSTRAINT "PUBLIC"."FKBN0F4TWXFUBUXG0OIL4OXUKNO" FOREIGN KEY("CUSTOMER_ORDER_ID") REFERENCES "PUBLIC"."CUSTOMER_ORDER"("ID") NOCHECK;  
ALTER TABLE "PUBLIC"."BOOK_ORDER" ADD CONSTRAINT "PUBLIC"."FKGU1CBRVBN5EL6FDG9T1YP1RKR" FOREIGN KEY("BOOK_ID") REFERENCES "PUBLIC"."BOOK_STOCK"("ID") NOCHECK;
ALTER TABLE "PUBLIC"."CUSTOMER" ADD CONSTRAINT "PUBLIC"."FKJ8DLM21J202CADSBFKOEM0S58" FOREIGN KEY("USER_ID") REFERENCES "PUBLIC"."USER"("ID") NOCHECK;        
ALTER TABLE "PUBLIC"."CUSTOMER_ORDER" ADD CONSTRAINT "PUBLIC"."FKF9ABD30BHIQVUGAYXLPQ8RYQ9" FOREIGN KEY("CUSTOMER_ID") REFERENCES "PUBLIC"."CUSTOMER"("ID") NOCHECK;          