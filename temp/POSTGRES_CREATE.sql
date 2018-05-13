CREATE TABLE MITARBEITERJPA4 (ID BIGINT NOT NULL, NAME VARCHAR(255), PRIMARY KEY (ID))
CREATE TABLE PERSON1 (ID BIGINT NOT NULL, NAME VARCHAR(255), PRIMARY KEY (ID))
CREATE TABLE ADRESSEJPA22 (ID BIGINT NOT NULL, ORT VARCHAR(255), PRIMARY KEY (ID))
CREATE TABLE ITEM (ID BIGINT NOT NULL, PREIS INTEGER, TITLE VARCHAR(255), VERKAUFT TIMESTAMP, BUYER_ID BIGINT, SELLER_ID BIGINT, PRIMARY KEY (ID))
CREATE TABLE BUCHJPA31 (ID BIGINT NOT NULL, TITEL VARCHAR(255), VERLAG_ID BIGINT, PRIMARY KEY (ID))
CREATE TABLE ADRESSEJPA21 (ID BIGINT NOT NULL, ORT VARCHAR(255), PRIMARY KEY (ID))
CREATE TABLE ADRESSEJPA7 (ID BIGINT NOT NULL, ORT VARCHAR(255), PRIMARY KEY (ID))
CREATE TABLE BUCHJPA32 (ID BIGINT NOT NULL, TITEL VARCHAR(255), VERLAG_ID BIGINT, PRIMARY KEY (ID))
CREATE TABLE GESCHLECHT (ID BIGINT NOT NULL, GESCHLECHT VARCHAR(255), PRIMARY KEY (ID))
CREATE TABLE CUSTOMER (ID BIGINT NOT NULL, NAME VARCHAR(255), ORT VARCHAR(255), PRIMARY KEY (ID))
CREATE TABLE PROJEKTJPA4 (ID BIGINT NOT NULL, BEZEICHNUNG VARCHAR(255), PRIMARY KEY (ID))
CREATE TABLE KONTOJPA99 (ID BIGINT NOT NULL, BEZEICHNUNG VARCHAR(255), SALDO DECIMAL(9,2), WERT DECIMAL(9,2), PRIMARY KEY (ID))
CREATE TABLE PERSONJPA7 (ID BIGINT NOT NULL, KZ INTEGER, NAME VARCHAR(255), ADDR_ID BIGINT, PRIMARY KEY (ID))
CREATE TABLE PERSONJPA1 (ID BIGINT NOT NULL, DATUM DATE, NAME VARCHAR(255), PINTZAHL INTEGER, PLONGZAHL BIGINT, SALDO DECIMAL(38), SHORTZAHL SMALLINT, VERSION INTEGER, WERT BIGINT, WINTZAHL INTEGER, WLONGZAHL BIGINT, ZEIT TIME, ZEITSTEMPEL TIMESTAMP, PRIMARY KEY (ID))
CREATE TABLE PERSONJPA21 (ID BIGINT NOT NULL, NAME VARCHAR(255), ADRESSE_ID BIGINT UNIQUE, PRIMARY KEY (ID))
CREATE TABLE PERSONJPA22 (ID BIGINT NOT NULL, NAME VARCHAR(255), ADRESSE_ID BIGINT UNIQUE, PRIMARY KEY (ID))
CREATE TABLE VERLAGJPA31 (ID BIGINT NOT NULL, NAME VARCHAR(255), PRIMARY KEY (ID))
CREATE TABLE VERLAGJPA32 (ID BIGINT NOT NULL, NAME VARCHAR(255), PRIMARY KEY (ID))
CREATE TABLE PERSONJPA5 (ID BIGINT NOT NULL, DATUM DATE, GEWICHT SMALLINT, INTZAHL INTEGER, LONGZAHL BIGINT, NACHNAME VARCHAR(255), VERSION INTEGER, VORNAME VARCHAR(255), ZEIT TIME, ZEITSTEMPEL TIMESTAMP, PRIMARY KEY (ID))
CREATE TABLE PERSONJPA99 (ID BIGINT NOT NULL, NAME VARCHAR(255), GESCHLECHT_ID BIGINT, PRIMARY KEY (ID))
CREATE TABLE PERSONJPA6 (ID BIGINT NOT NULL, DATUM DATE, GEWICHT SMALLINT, INTZAHL INTEGER, LONGZAHL BIGINT, NACHNAME VARCHAR(255), VERSION INTEGER, VORNAME VARCHAR(255), ZEIT TIME, ZEITSTEMPEL TIMESTAMP, PRIMARY KEY (ID))
CREATE TABLE PROJEKTJPA4_MITARBEITERJPA4 (mitarbeiterListe_ID BIGINT NOT NULL, projektListe_ID BIGINT NOT NULL, PRIMARY KEY (mitarbeiterListe_ID, projektListe_ID))
ALTER TABLE ITEM ADD CONSTRAINT FK_ITEM_BUYER_ID FOREIGN KEY (BUYER_ID) REFERENCES CUSTOMER (ID)
ALTER TABLE ITEM ADD CONSTRAINT FK_ITEM_SELLER_ID FOREIGN KEY (SELLER_ID) REFERENCES CUSTOMER (ID)
ALTER TABLE BUCHJPA31 ADD CONSTRAINT FK_BUCHJPA31_VERLAG_ID FOREIGN KEY (VERLAG_ID) REFERENCES VERLAGJPA31 (ID)
ALTER TABLE BUCHJPA32 ADD CONSTRAINT FK_BUCHJPA32_VERLAG_ID FOREIGN KEY (VERLAG_ID) REFERENCES VERLAGJPA32 (ID)
ALTER TABLE PERSONJPA7 ADD CONSTRAINT FK_PERSONJPA7_ADDR_ID FOREIGN KEY (ADDR_ID) REFERENCES ADRESSEJPA7 (ID)
ALTER TABLE PERSONJPA21 ADD CONSTRAINT FK_PERSONJPA21_ADRESSE_ID FOREIGN KEY (ADRESSE_ID) REFERENCES ADRESSEJPA21 (ID)
ALTER TABLE PERSONJPA22 ADD CONSTRAINT FK_PERSONJPA22_ADRESSE_ID FOREIGN KEY (ADRESSE_ID) REFERENCES ADRESSEJPA22 (ID)
ALTER TABLE PERSONJPA99 ADD CONSTRAINT FK_PERSONJPA99_GESCHLECHT_ID FOREIGN KEY (GESCHLECHT_ID) REFERENCES GESCHLECHT (ID)
ALTER TABLE PROJEKTJPA4_MITARBEITERJPA4 ADD CONSTRAINT FK_PROJEKTJPA4_MITARBEITERJPA4_mitarbeiterListe_ID FOREIGN KEY (mitarbeiterListe_ID) REFERENCES MITARBEITERJPA4 (ID)
ALTER TABLE PROJEKTJPA4_MITARBEITERJPA4 ADD CONSTRAINT FK_PROJEKTJPA4_MITARBEITERJPA4_projektListe_ID FOREIGN KEY (projektListe_ID) REFERENCES PROJEKTJPA4 (ID)
CREATE TABLE SEQUENCE (SEQ_NAME VARCHAR(50) NOT NULL, SEQ_COUNT DECIMAL(38), PRIMARY KEY (SEQ_NAME))
INSERT INTO SEQUENCE(SEQ_NAME, SEQ_COUNT) values ('SEQ_GEN', 0)