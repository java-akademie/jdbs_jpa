ALTER TABLE ITEM DROP CONSTRAINT FK_ITEM_BUYER_ID
ALTER TABLE ITEM DROP CONSTRAINT FK_ITEM_SELLER_ID
ALTER TABLE BUCHJPA31 DROP CONSTRAINT FK_BUCHJPA31_VERLAG_ID
ALTER TABLE BUCHJPA32 DROP CONSTRAINT FK_BUCHJPA32_VERLAG_ID
ALTER TABLE PERSONJPA7 DROP CONSTRAINT FK_PERSONJPA7_ADDR_ID
ALTER TABLE PERSONJPA21 DROP CONSTRAINT FK_PERSONJPA21_ADRESSE_ID
ALTER TABLE PERSONJPA22 DROP CONSTRAINT FK_PERSONJPA22_ADRESSE_ID
ALTER TABLE PERSONJPA99 DROP CONSTRAINT FK_PERSONJPA99_GESCHLECHT_ID
ALTER TABLE PROJEKTJPA4_MITARBEITERJPA4 DROP CONSTRAINT FK_PROJEKTJPA4_MITARBEITERJPA4_mitarbeiterListe_ID
ALTER TABLE PROJEKTJPA4_MITARBEITERJPA4 DROP CONSTRAINT FK_PROJEKTJPA4_MITARBEITERJPA4_projektListe_ID
DROP TABLE MITARBEITERJPA4
DROP TABLE PERSON1
DROP TABLE ADRESSEJPA22
DROP TABLE ITEM
DROP TABLE BUCHJPA31
DROP TABLE ADRESSEJPA21
DROP TABLE ADRESSEJPA7
DROP TABLE BUCHJPA32
DROP TABLE GESCHLECHT
DROP TABLE CUSTOMER
DROP TABLE PROJEKTJPA4
DROP TABLE KONTOJPA99
DROP TABLE PERSONJPA7
DROP TABLE PERSONJPA1
DROP TABLE PERSONJPA21
DROP TABLE PERSONJPA22
DROP TABLE VERLAGJPA31
DROP TABLE VERLAGJPA32
DROP TABLE PERSONJPA5
DROP TABLE PERSONJPA99
DROP TABLE PERSONJPA6
DROP TABLE PROJEKTJPA4_MITARBEITERJPA4
DELETE FROM SEQUENCE WHERE SEQ_NAME = 'SEQ_GEN'
