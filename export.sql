--------------------------------------------------------
--  File created - pi¹tek-czerwca-12-2020   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Type ARRAY_T
--------------------------------------------------------

  CREATE OR REPLACE NONEDITIONABLE TYPE "STUDENT"."ARRAY_T" IS VARRAY(12) OF INTEGER;

/
--------------------------------------------------------
--  DDL for Sequence ADRES_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "STUDENT"."ADRES_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 61 CACHE 20 NOORDER  NOCYCLE  NOKEEP  NOSCALE  GLOBAL ;
--------------------------------------------------------
--  DDL for Sequence AUTORZY_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "STUDENT"."AUTORZY_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 81 CACHE 20 NOORDER  NOCYCLE  NOKEEP  NOSCALE  GLOBAL ;
--------------------------------------------------------
--  DDL for Sequence KATEGORIE_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "STUDENT"."KATEGORIE_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 61 CACHE 20 NOORDER  NOCYCLE  NOKEEP  NOSCALE  GLOBAL ;
--------------------------------------------------------
--  DDL for Sequence KLIENCI_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "STUDENT"."KLIENCI_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 41 CACHE 20 NOORDER  NOCYCLE  NOKEEP  NOSCALE  GLOBAL ;
--------------------------------------------------------
--  DDL for Sequence KSIAZKI_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "STUDENT"."KSIAZKI_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 81 CACHE 20 NOORDER  NOCYCLE  NOKEEP  NOSCALE  GLOBAL ;
--------------------------------------------------------
--  DDL for Sequence PRACOWNICY_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "STUDENT"."PRACOWNICY_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 41 CACHE 20 NOORDER  NOCYCLE  NOKEEP  NOSCALE  GLOBAL ;
--------------------------------------------------------
--  DDL for Sequence TAG_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "STUDENT"."TAG_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 61 CACHE 20 NOORDER  NOCYCLE  NOKEEP  NOSCALE  GLOBAL ;
--------------------------------------------------------
--  DDL for Sequence WYDAWNICTWA_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "STUDENT"."WYDAWNICTWA_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 61 CACHE 20 NOORDER  NOCYCLE  NOKEEP  NOSCALE  GLOBAL ;
--------------------------------------------------------
--  DDL for Sequence WYPOZYCZENIA_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "STUDENT"."WYPOZYCZENIA_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 61 CACHE 20 NOORDER  NOCYCLE  NOKEEP  NOSCALE  GLOBAL ;
--------------------------------------------------------
--  DDL for Table ADRES
--------------------------------------------------------

  CREATE TABLE "STUDENT"."ADRES" 
   (	"ID_ADRESU" NUMBER(*,0), 
	"MIEJSCOWOSC" VARCHAR2(255 BYTE), 
	"KOD_POCZTOWY" VARCHAR2(255 BYTE), 
	"ULICA" VARCHAR2(255 BYTE), 
	"NUMER_BUDYNKU" NUMBER(*,0)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table AUTORZY
--------------------------------------------------------

  CREATE TABLE "STUDENT"."AUTORZY" 
   (	"ID_AUTORA" NUMBER(*,0), 
	"IMIE" VARCHAR2(255 BYTE), 
	"NAZWISKO" VARCHAR2(255 BYTE)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table AUTORZY_KSIAZKI
--------------------------------------------------------

  CREATE TABLE "STUDENT"."AUTORZY_KSIAZKI" 
   (	"ID_AUTORA" NUMBER(*,0), 
	"ID_KSIAZKI" NUMBER(*,0)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table KATEGORIE
--------------------------------------------------------

  CREATE TABLE "STUDENT"."KATEGORIE" 
   (	"ID_KATEGORII" NUMBER(*,0), 
	"NAZWA" VARCHAR2(255 BYTE)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table KLIENCI
--------------------------------------------------------

  CREATE TABLE "STUDENT"."KLIENCI" 
   (	"ID_KLIENTA" NUMBER(*,0), 
	"IMIE" VARCHAR2(255 BYTE), 
	"NAZWISKO" VARCHAR2(255 BYTE), 
	"ID_ADRESU" NUMBER(*,0)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table KSIAZKI
--------------------------------------------------------

  CREATE TABLE "STUDENT"."KSIAZKI" 
   (	"ID_KSIAZKI" NUMBER(*,0), 
	"TYTUL" VARCHAR2(255 BYTE), 
	"DATA_WYDANIA" DATE, 
	"ILOSC" NUMBER(*,0), 
	"ID_WYDAWNICTWA" NUMBER(*,0), 
	"ID_KATEGORII" NUMBER(*,0)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table KSIAZKI_TAG
--------------------------------------------------------

  CREATE TABLE "STUDENT"."KSIAZKI_TAG" 
   (	"ID_KSIAZKI" NUMBER(*,0), 
	"ID_TAGU" NUMBER(*,0)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table PRACOWNICY
--------------------------------------------------------

  CREATE TABLE "STUDENT"."PRACOWNICY" 
   (	"ID_PRACOWNIKA" NUMBER(*,0), 
	"IMIE" VARCHAR2(255 BYTE), 
	"NAZWISKO" VARCHAR2(255 BYTE), 
	"PESEL" VARCHAR2(255 BYTE), 
	"DATA_URODZENIA" DATE, 
	"ID_ADRESU" NUMBER(*,0), 
	"LOGIN" VARCHAR2(255 BYTE), 
	"HASLO" VARCHAR2(255 BYTE)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table TAG
--------------------------------------------------------

  CREATE TABLE "STUDENT"."TAG" 
   (	"ID_TAGU" NUMBER(*,0), 
	"NAZWA" VARCHAR2(255 BYTE)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table WYDAWNICTWA
--------------------------------------------------------

  CREATE TABLE "STUDENT"."WYDAWNICTWA" 
   (	"ID_WYDAWNICTWA" NUMBER(*,0), 
	"NAZWA" VARCHAR2(255 BYTE), 
	"ID_ADRESU" NUMBER(*,0)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table WYPOZYCZENIA
--------------------------------------------------------

  CREATE TABLE "STUDENT"."WYPOZYCZENIA" 
   (	"ID_WYPOZYCZENIA" NUMBER(*,0), 
	"ID_KSIAZKI" NUMBER(*,0), 
	"ID_PRACOWNIKA" NUMBER(*,0), 
	"ID_KLIENTA" NUMBER(*,0), 
	"DATA_ZWROTU" DATE, 
	"DATA_WYPOZYCZENIA" TIMESTAMP (6)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
REM INSERTING into STUDENT.ADRES
SET DEFINE OFF;
Insert into STUDENT.ADRES (ID_ADRESU,MIEJSCOWOSC,KOD_POCZTOWY,ULICA,NUMER_BUDYNKU) values ('2','Rzeszów','35-213','Dêbicka','402');
Insert into STUDENT.ADRES (ID_ADRESU,MIEJSCOWOSC,KOD_POCZTOWY,ULICA,NUMER_BUDYNKU) values ('3','Rzeszów','35-213','Dêbicka','402');
Insert into STUDENT.ADRES (ID_ADRESU,MIEJSCOWOSC,KOD_POCZTOWY,ULICA,NUMER_BUDYNKU) values ('21','Wo³owiec','38–307','Sêkowa','11');
Insert into STUDENT.ADRES (ID_ADRESU,MIEJSCOWOSC,KOD_POCZTOWY,ULICA,NUMER_BUDYNKU) values ('23','Warszawa','00-732','Czerska','8');
Insert into STUDENT.ADRES (ID_ADRESU,MIEJSCOWOSC,KOD_POCZTOWY,ULICA,NUMER_BUDYNKU) values ('4','O¿arów Mazowiecki','05-850','Poznañska','91');
Insert into STUDENT.ADRES (ID_ADRESU,MIEJSCOWOSC,KOD_POCZTOWY,ULICA,NUMER_BUDYNKU) values ('5','Rzeszów','35-213','Dêbicka','402');
Insert into STUDENT.ADRES (ID_ADRESU,MIEJSCOWOSC,KOD_POCZTOWY,ULICA,NUMER_BUDYNKU) values ('24','Rzeszów','35-200','Krakowska','12');
Insert into STUDENT.ADRES (ID_ADRESU,MIEJSCOWOSC,KOD_POCZTOWY,ULICA,NUMER_BUDYNKU) values ('7','O¿arów Mazowiecki','05-850','Poznañska','91');
Insert into STUDENT.ADRES (ID_ADRESU,MIEJSCOWOSC,KOD_POCZTOWY,ULICA,NUMER_BUDYNKU) values ('22','Warszawa','01-527','Mieros³awskiego','11');
Insert into STUDENT.ADRES (ID_ADRESU,MIEJSCOWOSC,KOD_POCZTOWY,ULICA,NUMER_BUDYNKU) values ('41','Bia³ystok','15-113','Andersa','40');
REM INSERTING into STUDENT.AUTORZY
SET DEFINE OFF;
Insert into STUDENT.AUTORZY (ID_AUTORA,IMIE,NAZWISKO) values ('44','Wojciech','Chmielarz');
Insert into STUDENT.AUTORZY (ID_AUTORA,IMIE,NAZWISKO) values ('45','Bo¿ena','Aksamit');
Insert into STUDENT.AUTORZY (ID_AUTORA,IMIE,NAZWISKO) values ('46','Piotr','G³uchowski');
Insert into STUDENT.AUTORZY (ID_AUTORA,IMIE,NAZWISKO) values ('1','Piotr','D³ugosz');
Insert into STUDENT.AUTORZY (ID_AUTORA,IMIE,NAZWISKO) values ('21','Remigiusz','Mróz');
Insert into STUDENT.AUTORZY (ID_AUTORA,IMIE,NAZWISKO) values ('41','Paulina','Œwist');
Insert into STUDENT.AUTORZY (ID_AUTORA,IMIE,NAZWISKO) values ('42','Elisabeth','Carpenter');
Insert into STUDENT.AUTORZY (ID_AUTORA,IMIE,NAZWISKO) values ('43','MAGGIE','NELSON');
Insert into STUDENT.AUTORZY (ID_AUTORA,IMIE,NAZWISKO) values ('61','Alicja','Sinicka');
Insert into STUDENT.AUTORZY (ID_AUTORA,IMIE,NAZWISKO) values ('62','Tomasz','Dzia³owy');
REM INSERTING into STUDENT.AUTORZY_KSIAZKI
SET DEFINE OFF;
Insert into STUDENT.AUTORZY_KSIAZKI (ID_AUTORA,ID_KSIAZKI) values ('43','45');
Insert into STUDENT.AUTORZY_KSIAZKI (ID_AUTORA,ID_KSIAZKI) values ('46','47');
Insert into STUDENT.AUTORZY_KSIAZKI (ID_AUTORA,ID_KSIAZKI) values ('45','47');
Insert into STUDENT.AUTORZY_KSIAZKI (ID_AUTORA,ID_KSIAZKI) values ('21','21');
Insert into STUDENT.AUTORZY_KSIAZKI (ID_AUTORA,ID_KSIAZKI) values ('41','43');
Insert into STUDENT.AUTORZY_KSIAZKI (ID_AUTORA,ID_KSIAZKI) values ('42','44');
Insert into STUDENT.AUTORZY_KSIAZKI (ID_AUTORA,ID_KSIAZKI) values ('44','46');
REM INSERTING into STUDENT.KATEGORIE
SET DEFINE OFF;
Insert into STUDENT.KATEGORIE (ID_KATEGORII,NAZWA) values ('22','Romans');
Insert into STUDENT.KATEGORIE (ID_KATEGORII,NAZWA) values ('2','Krymina³');
Insert into STUDENT.KATEGORIE (ID_KATEGORII,NAZWA) values ('21','Autobiografia');
Insert into STUDENT.KATEGORIE (ID_KATEGORII,NAZWA) values ('23','Zbrodnia');
Insert into STUDENT.KATEGORIE (ID_KATEGORII,NAZWA) values ('41','Kosmos');
REM INSERTING into STUDENT.KLIENCI
SET DEFINE OFF;
Insert into STUDENT.KLIENCI (ID_KLIENTA,IMIE,NAZWISKO,ID_ADRESU) values ('1','Piotr','D³ugosz','5');
Insert into STUDENT.KLIENCI (ID_KLIENTA,IMIE,NAZWISKO,ID_ADRESU) values ('21','Tomasz','R¹czy','24');
REM INSERTING into STUDENT.KSIAZKI
SET DEFINE OFF;
Insert into STUDENT.KSIAZKI (ID_KSIAZKI,TYTUL,DATA_WYDANIA,ILOSC,ID_WYDAWNICTWA,ID_KATEGORII) values ('45','ARGONAUCI',to_date('20/05/06','RR/MM/DD'),'5','21','21');
Insert into STUDENT.KSIAZKI (ID_KSIAZKI,TYTUL,DATA_WYDANIA,ILOSC,ID_WYDAWNICTWA,ID_KATEGORII) values ('47','Zatoka œwiñ',to_date('20/04/07','RR/MM/DD'),'2','23','23');
Insert into STUDENT.KSIAZKI (ID_KSIAZKI,TYTUL,DATA_WYDANIA,ILOSC,ID_WYDAWNICTWA,ID_KATEGORII) values ('21','Lot 202',to_date('20/05/20','RR/MM/DD'),'2','2','2');
Insert into STUDENT.KSIAZKI (ID_KSIAZKI,TYTUL,DATA_WYDANIA,ILOSC,ID_WYDAWNICTWA,ID_KATEGORII) values ('44','Tylko matka…',to_date('20/06/03','RR/MM/DD'),'3','2','2');
Insert into STUDENT.KSIAZKI (ID_KSIAZKI,TYTUL,DATA_WYDANIA,ILOSC,ID_WYDAWNICTWA,ID_KATEGORII) values ('43','Przekrêt',to_date('20/06/03','RR/MM/DD'),'11','2','2');
Insert into STUDENT.KSIAZKI (ID_KSIAZKI,TYTUL,DATA_WYDANIA,ILOSC,ID_WYDAWNICTWA,ID_KATEGORII) values ('46','Wyrwa',to_date('20/06/05','RR/MM/DD'),'1','22','22');
REM INSERTING into STUDENT.KSIAZKI_TAG
SET DEFINE OFF;
Insert into STUDENT.KSIAZKI_TAG (ID_KSIAZKI,ID_TAGU) values ('45','42');
Insert into STUDENT.KSIAZKI_TAG (ID_KSIAZKI,ID_TAGU) values ('47','48');
Insert into STUDENT.KSIAZKI_TAG (ID_KSIAZKI,ID_TAGU) values ('47','45');
Insert into STUDENT.KSIAZKI_TAG (ID_KSIAZKI,ID_TAGU) values ('47','21');
Insert into STUDENT.KSIAZKI_TAG (ID_KSIAZKI,ID_TAGU) values ('21','21');
Insert into STUDENT.KSIAZKI_TAG (ID_KSIAZKI,ID_TAGU) values ('21','22');
Insert into STUDENT.KSIAZKI_TAG (ID_KSIAZKI,ID_TAGU) values ('21','23');
Insert into STUDENT.KSIAZKI_TAG (ID_KSIAZKI,ID_TAGU) values ('43','22');
Insert into STUDENT.KSIAZKI_TAG (ID_KSIAZKI,ID_TAGU) values ('43','21');
Insert into STUDENT.KSIAZKI_TAG (ID_KSIAZKI,ID_TAGU) values ('44','22');
Insert into STUDENT.KSIAZKI_TAG (ID_KSIAZKI,ID_TAGU) values ('44','21');
Insert into STUDENT.KSIAZKI_TAG (ID_KSIAZKI,ID_TAGU) values ('44','41');
Insert into STUDENT.KSIAZKI_TAG (ID_KSIAZKI,ID_TAGU) values ('46','22');
Insert into STUDENT.KSIAZKI_TAG (ID_KSIAZKI,ID_TAGU) values ('46','21');
Insert into STUDENT.KSIAZKI_TAG (ID_KSIAZKI,ID_TAGU) values ('46','41');
REM INSERTING into STUDENT.PRACOWNICY
SET DEFINE OFF;
Insert into STUDENT.PRACOWNICY (ID_PRACOWNIKA,IMIE,NAZWISKO,PESEL,DATA_URODZENIA,ID_ADRESU,LOGIN,HASLO) values ('6','Piotr','Dlugosz','123',to_date('20/06/10','RR/MM/DD'),'2','login','haslo');
Insert into STUDENT.PRACOWNICY (ID_PRACOWNIKA,IMIE,NAZWISKO,PESEL,DATA_URODZENIA,ID_ADRESU,LOGIN,HASLO) values ('21','p','d','1',to_date('20/06/11','RR/MM/DD'),'2','1','2');
REM INSERTING into STUDENT.TAG
SET DEFINE OFF;
Insert into STUDENT.TAG (ID_TAGU,NAZWA) values ('41','Thriller');
Insert into STUDENT.TAG (ID_TAGU,NAZWA) values ('43','Pamiêtnik');
Insert into STUDENT.TAG (ID_TAGU,NAZWA) values ('44','Litera faktu');
Insert into STUDENT.TAG (ID_TAGU,NAZWA) values ('1','Rzeszów');
Insert into STUDENT.TAG (ID_TAGU,NAZWA) values ('21','Sensacja');
Insert into STUDENT.TAG (ID_TAGU,NAZWA) values ('22','Krymina³');
Insert into STUDENT.TAG (ID_TAGU,NAZWA) values ('23','Polski autor');
Insert into STUDENT.TAG (ID_TAGU,NAZWA) values ('42','Biografia');
Insert into STUDENT.TAG (ID_TAGU,NAZWA) values ('45','Korupcja');
Insert into STUDENT.TAG (ID_TAGU,NAZWA) values ('48','seks');
REM INSERTING into STUDENT.WYDAWNICTWA
SET DEFINE OFF;
Insert into STUDENT.WYDAWNICTWA (ID_WYDAWNICTWA,NAZWA,ID_ADRESU) values ('21','WYDAWNICTWO CZARNE','21');
Insert into STUDENT.WYDAWNICTWA (ID_WYDAWNICTWA,NAZWA,ID_ADRESU) values ('23','Agora','23');
Insert into STUDENT.WYDAWNICTWA (ID_WYDAWNICTWA,NAZWA,ID_ADRESU) values ('2','Œwiat Ksi¹¿ki','7');
Insert into STUDENT.WYDAWNICTWA (ID_WYDAWNICTWA,NAZWA,ID_ADRESU) values ('22','Marginesy','22');
Insert into STUDENT.WYDAWNICTWA (ID_WYDAWNICTWA,NAZWA,ID_ADRESU) values ('41','Wydawnictwo Kobiece','41');
REM INSERTING into STUDENT.WYPOZYCZENIA
SET DEFINE OFF;
Insert into STUDENT.WYPOZYCZENIA (ID_WYPOZYCZENIA,ID_KSIAZKI,ID_PRACOWNIKA,ID_KLIENTA,DATA_ZWROTU,DATA_WYPOZYCZENIA) values ('1','21','6','1',to_date('20/06/11','RR/MM/DD'),to_timestamp('20/06/11 00:45:34,996000000','RR/MM/DD HH24:MI:SSXFF'));
Insert into STUDENT.WYPOZYCZENIA (ID_WYPOZYCZENIA,ID_KSIAZKI,ID_PRACOWNIKA,ID_KLIENTA,DATA_ZWROTU,DATA_WYPOZYCZENIA) values ('2','21','6','1',to_date('20/06/11','RR/MM/DD'),to_timestamp('20/06/11 00:50:33,599000000','RR/MM/DD HH24:MI:SSXFF'));
Insert into STUDENT.WYPOZYCZENIA (ID_WYPOZYCZENIA,ID_KSIAZKI,ID_PRACOWNIKA,ID_KLIENTA,DATA_ZWROTU,DATA_WYPOZYCZENIA) values ('3','21','6','1',to_date('20/06/11','RR/MM/DD'),to_timestamp('20/06/11 01:28:44,133000000','RR/MM/DD HH24:MI:SSXFF'));
Insert into STUDENT.WYPOZYCZENIA (ID_WYPOZYCZENIA,ID_KSIAZKI,ID_PRACOWNIKA,ID_KLIENTA,DATA_ZWROTU,DATA_WYPOZYCZENIA) values ('4','21','6','1',to_date('20/06/11','RR/MM/DD'),to_timestamp('20/06/11 02:07:04,429000000','RR/MM/DD HH24:MI:SSXFF'));
Insert into STUDENT.WYPOZYCZENIA (ID_WYPOZYCZENIA,ID_KSIAZKI,ID_PRACOWNIKA,ID_KLIENTA,DATA_ZWROTU,DATA_WYPOZYCZENIA) values ('5','21','6','1',to_date('20/06/11','RR/MM/DD'),to_timestamp('20/06/11 02:13:51,888000000','RR/MM/DD HH24:MI:SSXFF'));
Insert into STUDENT.WYPOZYCZENIA (ID_WYPOZYCZENIA,ID_KSIAZKI,ID_PRACOWNIKA,ID_KLIENTA,DATA_ZWROTU,DATA_WYPOZYCZENIA) values ('6','21','6','1',to_date('20/06/11','RR/MM/DD'),to_timestamp('20/06/11 02:14:34,806000000','RR/MM/DD HH24:MI:SSXFF'));
Insert into STUDENT.WYPOZYCZENIA (ID_WYPOZYCZENIA,ID_KSIAZKI,ID_PRACOWNIKA,ID_KLIENTA,DATA_ZWROTU,DATA_WYPOZYCZENIA) values ('7','21','6','1',to_date('20/06/11','RR/MM/DD'),to_timestamp('20/06/11 02:47:58,164000000','RR/MM/DD HH24:MI:SSXFF'));
Insert into STUDENT.WYPOZYCZENIA (ID_WYPOZYCZENIA,ID_KSIAZKI,ID_PRACOWNIKA,ID_KLIENTA,DATA_ZWROTU,DATA_WYPOZYCZENIA) values ('21','21','6','1',to_date('20/06/11','RR/MM/DD'),to_timestamp('20/06/11 21:43:53,223000000','RR/MM/DD HH24:MI:SSXFF'));
Insert into STUDENT.WYPOZYCZENIA (ID_WYPOZYCZENIA,ID_KSIAZKI,ID_PRACOWNIKA,ID_KLIENTA,DATA_ZWROTU,DATA_WYPOZYCZENIA) values ('22','21','6','1',to_date('20/06/11','RR/MM/DD'),to_timestamp('20/06/11 21:44:06,069000000','RR/MM/DD HH24:MI:SSXFF'));
Insert into STUDENT.WYPOZYCZENIA (ID_WYPOZYCZENIA,ID_KSIAZKI,ID_PRACOWNIKA,ID_KLIENTA,DATA_ZWROTU,DATA_WYPOZYCZENIA) values ('23','21','6','1',to_date('20/06/11','RR/MM/DD'),to_timestamp('20/06/11 21:45:15,173000000','RR/MM/DD HH24:MI:SSXFF'));
Insert into STUDENT.WYPOZYCZENIA (ID_WYPOZYCZENIA,ID_KSIAZKI,ID_PRACOWNIKA,ID_KLIENTA,DATA_ZWROTU,DATA_WYPOZYCZENIA) values ('24','21','6','1',to_date('20/06/11','RR/MM/DD'),to_timestamp('20/06/11 22:11:40,562000000','RR/MM/DD HH24:MI:SSXFF'));
Insert into STUDENT.WYPOZYCZENIA (ID_WYPOZYCZENIA,ID_KSIAZKI,ID_PRACOWNIKA,ID_KLIENTA,DATA_ZWROTU,DATA_WYPOZYCZENIA) values ('25','21','6','1',to_date('20/06/11','RR/MM/DD'),to_timestamp('20/06/11 22:13:47,581000000','RR/MM/DD HH24:MI:SSXFF'));
Insert into STUDENT.WYPOZYCZENIA (ID_WYPOZYCZENIA,ID_KSIAZKI,ID_PRACOWNIKA,ID_KLIENTA,DATA_ZWROTU,DATA_WYPOZYCZENIA) values ('41','43','6','21',null,to_timestamp('20/06/11 23:28:50,121000000','RR/MM/DD HH24:MI:SSXFF'));
--------------------------------------------------------
--  DDL for Index UNIQUE_KATEGORIA
--------------------------------------------------------

  CREATE UNIQUE INDEX "STUDENT"."UNIQUE_KATEGORIA" ON "STUDENT"."KATEGORIE" ("NAZWA") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Index UNIQUE_PESEL
--------------------------------------------------------

  CREATE UNIQUE INDEX "STUDENT"."UNIQUE_PESEL" ON "STUDENT"."PRACOWNICY" ("PESEL") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Index UNIQUE_TAG
--------------------------------------------------------

  CREATE UNIQUE INDEX "STUDENT"."UNIQUE_TAG" ON "STUDENT"."TAG" ("NAZWA") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Index UNIQUE_WYDAWNICTWO
--------------------------------------------------------

  CREATE UNIQUE INDEX "STUDENT"."UNIQUE_WYDAWNICTWO" ON "STUDENT"."WYDAWNICTWA" ("NAZWA") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Trigger ADRES_TRG
--------------------------------------------------------

  CREATE OR REPLACE NONEDITIONABLE TRIGGER "STUDENT"."ADRES_TRG" 
BEFORE INSERT ON ADRES 
FOR EACH ROW 
BEGIN
  <<COLUMN_SEQUENCES>>
  BEGIN
    IF INSERTING AND :NEW.ID_ADRESU IS NULL THEN
      SELECT ADRES_SEQ.NEXTVAL INTO :NEW.ID_ADRESU FROM SYS.DUAL;
    END IF;
  END COLUMN_SEQUENCES;
END;
/
ALTER TRIGGER "STUDENT"."ADRES_TRG" ENABLE;
--------------------------------------------------------
--  DDL for Trigger AUTORZY_TRG
--------------------------------------------------------

  CREATE OR REPLACE NONEDITIONABLE TRIGGER "STUDENT"."AUTORZY_TRG" 
BEFORE INSERT ON AUTORZY 
FOR EACH ROW 
BEGIN
  <<COLUMN_SEQUENCES>>
  BEGIN
    IF INSERTING AND :NEW.ID_AUTORA IS NULL THEN
      SELECT AUTORZY_SEQ.NEXTVAL INTO :NEW.ID_AUTORA FROM SYS.DUAL;
    END IF;
  END COLUMN_SEQUENCES;
END;
/
ALTER TRIGGER "STUDENT"."AUTORZY_TRG" ENABLE;
--------------------------------------------------------
--  DDL for Trigger KATEGORIE_TRG
--------------------------------------------------------

  CREATE OR REPLACE NONEDITIONABLE TRIGGER "STUDENT"."KATEGORIE_TRG" 
BEFORE INSERT ON KATEGORIE 
FOR EACH ROW 
BEGIN
  <<COLUMN_SEQUENCES>>
  BEGIN
    IF INSERTING AND :NEW.ID_KATEGORII IS NULL THEN
      SELECT KATEGORIE_SEQ.NEXTVAL INTO :NEW.ID_KATEGORII FROM SYS.DUAL;
    END IF;
  END COLUMN_SEQUENCES;
END;
/
ALTER TRIGGER "STUDENT"."KATEGORIE_TRG" ENABLE;
--------------------------------------------------------
--  DDL for Trigger KLIENCIIMIE
--------------------------------------------------------

  CREATE OR REPLACE NONEDITIONABLE TRIGGER "STUDENT"."KLIENCIIMIE" 
BEFORE INSERT ON Klienci 
FOR EACH ROW
begin
IF LENGTH(TRIM(TRANSLATE(LOWER(:new.imie), ' +¹æêñ³óœ¿Ÿqabcdefghijklmnoprstuvwxyz',' '))) != 0 THEN
RAISE_APPLICATION_ERROR( -20033, 'Imie moze zawierac tylko litery' );
end if;
end;
/
ALTER TRIGGER "STUDENT"."KLIENCIIMIE" ENABLE;
--------------------------------------------------------
--  DDL for Trigger KLIENCINAZWISKO
--------------------------------------------------------

  CREATE OR REPLACE NONEDITIONABLE TRIGGER "STUDENT"."KLIENCINAZWISKO" 
BEFORE INSERT ON Klienci 
FOR EACH ROW
begin
IF LENGTH(TRIM(TRANSLATE(LOWER(:new.nazwisko), ' +¹æêñ³óœ¿Ÿqabcdefghijklmnoprstuvwxyz',' '))) != 0 THEN
RAISE_APPLICATION_ERROR( -20033, 'Imie moze zawierac tylko litery' );
end if;
end;
/
ALTER TRIGGER "STUDENT"."KLIENCINAZWISKO" ENABLE;
--------------------------------------------------------
--  DDL for Trigger KLIENCI_TRG
--------------------------------------------------------

  CREATE OR REPLACE NONEDITIONABLE TRIGGER "STUDENT"."KLIENCI_TRG" 
BEFORE INSERT ON KLIENCI 
FOR EACH ROW 
BEGIN
  <<COLUMN_SEQUENCES>>
  BEGIN
    IF INSERTING AND :NEW.ID_KLIENTA IS NULL THEN
      SELECT KLIENCI_SEQ.NEXTVAL INTO :NEW.ID_KLIENTA FROM SYS.DUAL;
    END IF;
  END COLUMN_SEQUENCES;
END;
/
ALTER TRIGGER "STUDENT"."KLIENCI_TRG" ENABLE;
--------------------------------------------------------
--  DDL for Trigger KSIAZKI_TRG
--------------------------------------------------------

  CREATE OR REPLACE NONEDITIONABLE TRIGGER "STUDENT"."KSIAZKI_TRG" 
BEFORE INSERT ON KSIAZKI 
FOR EACH ROW 
BEGIN
  <<COLUMN_SEQUENCES>>
  BEGIN
    IF INSERTING AND :NEW.ID_KSIAZKI IS NULL THEN
      SELECT KSIAZKI_SEQ.NEXTVAL INTO :NEW.ID_KSIAZKI FROM SYS.DUAL;
    END IF;
  END COLUMN_SEQUENCES;
END;
/
ALTER TRIGGER "STUDENT"."KSIAZKI_TRG" ENABLE;
--------------------------------------------------------
--  DDL for Trigger NAZWAMIEJSCOWOSOSCI
--------------------------------------------------------

  CREATE OR REPLACE NONEDITIONABLE TRIGGER "STUDENT"."NAZWAMIEJSCOWOSOSCI" 
BEFORE INSERT ON Adres 
FOR EACH ROW
begin
IF LENGTH(TRIM(TRANSLATE(LOWER(:new.miejscowosc), ' +¹æêñ³óœ¿Ÿqabcdefghijklmnoprstuvwxyz',' '))) != 0 THEN
RAISE_APPLICATION_ERROR( -20033, 'Imie moze zawierac tylko litery' );
end if;
end;
/
ALTER TRIGGER "STUDENT"."NAZWAMIEJSCOWOSOSCI" ENABLE;
--------------------------------------------------------
--  DDL for Trigger PRACOWNICYIMIE
--------------------------------------------------------

  CREATE OR REPLACE NONEDITIONABLE TRIGGER "STUDENT"."PRACOWNICYIMIE" 
BEFORE INSERT ON Pracownicy 
FOR EACH ROW
begin
IF LENGTH(TRIM(TRANSLATE(LOWER(:new.imie), ' +¹æêñ³óœ¿Ÿqabcdefghijklmnoprstuvwxyz',' '))) != 0 THEN
RAISE_APPLICATION_ERROR( -20033, 'Imie moze zawierac tylko litery' );
end if;
end;
/
ALTER TRIGGER "STUDENT"."PRACOWNICYIMIE" ENABLE;
--------------------------------------------------------
--  DDL for Trigger PRACOWNICYNAZWISKO
--------------------------------------------------------

  CREATE OR REPLACE NONEDITIONABLE TRIGGER "STUDENT"."PRACOWNICYNAZWISKO" 
BEFORE INSERT ON Pracownicy
FOR EACH ROW
begin
IF LENGTH(TRIM(TRANSLATE(LOWER(:new.nazwisko), ' +¹æêñ³óœ¿Ÿqabcdefghijklmnoprstuvwxyz',' '))) != 0 THEN
RAISE_APPLICATION_ERROR( -20033, 'Imie moze zawierac tylko litery' );
end if;
end;
/
ALTER TRIGGER "STUDENT"."PRACOWNICYNAZWISKO" ENABLE;
--------------------------------------------------------
--  DDL for Trigger PRACOWNICY_TRG
--------------------------------------------------------

  CREATE OR REPLACE NONEDITIONABLE TRIGGER "STUDENT"."PRACOWNICY_TRG" 
BEFORE INSERT ON PRACOWNICY 
FOR EACH ROW 
BEGIN
  <<COLUMN_SEQUENCES>>
  BEGIN
    IF INSERTING AND :NEW.ID_PRACOWNIKA IS NULL THEN
      SELECT PRACOWNICY_SEQ.NEXTVAL INTO :NEW.ID_PRACOWNIKA FROM SYS.DUAL;
    END IF;
  END COLUMN_SEQUENCES;
END;
/
ALTER TRIGGER "STUDENT"."PRACOWNICY_TRG" ENABLE;
--------------------------------------------------------
--  DDL for Trigger PRACOWNIKPESEL
--------------------------------------------------------

  CREATE OR REPLACE NONEDITIONABLE TRIGGER "STUDENT"."PRACOWNIKPESEL" 
BEFORE INSERT ON pracownicy 
FOR EACH ROW
begin
IF LENGTH(TRIM(TRANSLATE(:new.PESEL, ' +-.0123456789',' '))) != 0 THEN
RAISE_APPLICATION_ERROR( -20031, 'PESEL moze zawierac tylko liczby' );
end if;
end;
/
ALTER TRIGGER "STUDENT"."PRACOWNIKPESEL" ENABLE;
--------------------------------------------------------
--  DDL for Trigger TAG_TRG
--------------------------------------------------------

  CREATE OR REPLACE NONEDITIONABLE TRIGGER "STUDENT"."TAG_TRG" 
BEFORE INSERT ON TAG 
FOR EACH ROW 
BEGIN
  <<COLUMN_SEQUENCES>>
  BEGIN
    IF INSERTING AND :NEW.ID_TAGU IS NULL THEN
      SELECT TAG_SEQ.NEXTVAL INTO :NEW.ID_TAGU FROM SYS.DUAL;
    END IF;
  END COLUMN_SEQUENCES;
END;
/
ALTER TRIGGER "STUDENT"."TAG_TRG" ENABLE;
--------------------------------------------------------
--  DDL for Trigger WYDAWNICTWA_TRG
--------------------------------------------------------

  CREATE OR REPLACE NONEDITIONABLE TRIGGER "STUDENT"."WYDAWNICTWA_TRG" 
BEFORE INSERT ON WYDAWNICTWA 
FOR EACH ROW 
BEGIN
  <<COLUMN_SEQUENCES>>
  BEGIN
    IF INSERTING AND :NEW.ID_WYDAWNICTWA IS NULL THEN
      SELECT WYDAWNICTWA_SEQ.NEXTVAL INTO :NEW.ID_WYDAWNICTWA FROM SYS.DUAL;
    END IF;
  END COLUMN_SEQUENCES;
END;
/
ALTER TRIGGER "STUDENT"."WYDAWNICTWA_TRG" ENABLE;
--------------------------------------------------------
--  DDL for Trigger WYPOZYCZENIA_TRG
--------------------------------------------------------

  CREATE OR REPLACE NONEDITIONABLE TRIGGER "STUDENT"."WYPOZYCZENIA_TRG" 
BEFORE INSERT ON WYPOZYCZENIA 
FOR EACH ROW 
BEGIN
  <<COLUMN_SEQUENCES>>
  BEGIN
    IF INSERTING AND :NEW.ID_WYPOZYCZENIA IS NULL THEN
      SELECT WYPOZYCZENIA_SEQ.NEXTVAL INTO :NEW.ID_WYPOZYCZENIA FROM SYS.DUAL;
    END IF;
  END COLUMN_SEQUENCES;
END;
/
ALTER TRIGGER "STUDENT"."WYPOZYCZENIA_TRG" ENABLE;
--------------------------------------------------------
--  DDL for Package DODAWANIE
--------------------------------------------------------

  CREATE OR REPLACE NONEDITIONABLE PACKAGE "STUDENT"."DODAWANIE" 
is

procedure Autor (imie IN VARCHAR2, nazwisko IN VARCHAR2);
procedure Kategoria (nazwa IN VARCHAR2);
procedure Tag (nazwaTagu IN VARCHAR2);
procedure Adres (miejscowosc IN VARCHAR2,kod_pocztowy IN VARCHAR2,ulica IN VARCHAR2, nr_budynku IN INTEGER);
procedure Wydawnictwa (nazwa IN VARCHAR2, id_adresu IN INTEGER);
procedure Pracownicy (imie IN VARCHAR2,nazwisko IN VARCHAR2, pesel IN VARCHAR2, data_urodzenia IN Date,id_adresu IN INTEGER,login IN VARCHAR2,haslo IN VARCHAR2);

end;

/
--------------------------------------------------------
--  DDL for Package POBRANIE
--------------------------------------------------------

  CREATE OR REPLACE NONEDITIONABLE PACKAGE "STUDENT"."POBRANIE" 
is

procedure Adres (d_id IN INTEGER,adresData OUT SYS_REFCURSOR);
procedure Autor (a_id IN INTEGER,autorData OUT SYS_REFCURSOR);
procedure Autorzy (autorzyData OUT SYS_REFCURSOR);
procedure Kategorie (kategorieData OUT SYS_REFCURSOR);
procedure Ksiazki (ksiazkiData OUT SYS_REFCURSOR);
procedure Pracownicy (pracownicyData OUT SYS_REFCURSOR);
procedure Pracownik (d_login IN VARCHAR2,d_haslo IN VARCHAR2,pracownikData OUT SYS_REFCURSOR);
procedure Tagi (tagiData OUT SYS_REFCURSOR);
procedure Wydawnictwo (id_w IN INTEGER, wydawnictwoData OUT SYS_REFCURSOR);
procedure Wydawnictwa (wydawnictwaData OUT SYS_REFCURSOR);
procedure WypozyczeniaIlosc (id_ksiazki IN INTEGER,ilosc OUT INTEGER);
procedure WypozyczeniaDlaKsiazki (id_k IN INTEGER,wynik OUT array_t);

end;

/
--------------------------------------------------------
--  DDL for Package Body DODAWANIE
--------------------------------------------------------

  CREATE OR REPLACE NONEDITIONABLE PACKAGE BODY "STUDENT"."DODAWANIE" 
is
procedure Autor (imie IN VARCHAR2, nazwisko IN VARCHAR2)
    AS
    BEGIN
        INSERT INTO autorzy(imie,nazwisko) VALUES (imie,nazwisko);
        Commit;
    END;
procedure Kategoria (nazwa IN VARCHAR2)
    AS
    BEGIN
        INSERT INTO kategorie(nazwa) VALUES (nazwa);
        Commit;
    END;
procedure Tag (nazwaTagu IN VARCHAR2)
    AS
    BEGIN
        INSERT INTO tag(nazwa) VALUES (nazwaTagu);
        Commit;
    END;
procedure Adres (miejscowosc IN VARCHAR2,kod_pocztowy IN VARCHAR2,ulica IN VARCHAR2, nr_budynku IN INTEGER)
    AS
    BEGIN
        INSERT INTO adres(miejscowosc,kod_pocztowy,ulica,numer_budynku) VALUES (miejscowosc,kod_pocztowy,ulica,nr_budynku);
        Commit;
    END;
procedure Wydawnictwa (nazwa IN VARCHAR2, id_adresu IN INTEGER)
    AS
    BEGIN
        INSERT INTO wydawnictwa(nazwa,id_adresu) VALUES (nazwa,id_adresu);
        Commit;
    END;
procedure Pracownicy (imie IN VARCHAR2,nazwisko IN VARCHAR2, pesel IN VARCHAR2, data_urodzenia IN Date,id_adresu IN INTEGER,login IN VARCHAR2,haslo IN VARCHAR2)
    AS
    BEGIN
        INSERT INTO pracownicy(imie,nazwisko,pesel,data_urodzenia,id_adresu,login,haslo) VALUES (imie,nazwisko,pesel,data_urodzenia,id_adresu,login,haslo);
        Commit;
    END;
end;

/
--------------------------------------------------------
--  DDL for Package Body POBRANIE
--------------------------------------------------------

  CREATE OR REPLACE NONEDITIONABLE PACKAGE BODY "STUDENT"."POBRANIE" 
is

procedure Adres (d_id IN INTEGER,adresData OUT SYS_REFCURSOR)
    AS
    BEGIN
    OPEN adresData FOR
        Select 
            ID_ADRESU ,
            MIEJSCOWOSC ,
            KOD_POCZTOWY ,
            ULICA ,
            NUMER_BUDYNKU 
        from Adres
        Where id_adresu=d_id;
    END;
procedure Autor (a_id IN INTEGER,autorData OUT SYS_REFCURSOR)
    AS
    BEGIN
        OPEN autorData FOR
            Select 
                *
            from autorzy
            WHERE id_autora=a_id;
    END;
procedure Autorzy (autorzyData OUT SYS_REFCURSOR)
    AS
    BEGIN
        OPEN autorzyData FOR
            Select 
                *
            from autorzy;
    END;
procedure Kategorie (kategorieData OUT SYS_REFCURSOR)
    AS
    BEGIN
        OPEN kategorieData FOR
            Select 
                *
            from kategorie;
    END;
procedure Ksiazki (ksiazkiData OUT SYS_REFCURSOR)
    AS
    BEGIN
        OPEN ksiazkiData FOR
            Select 
                *
            from ksiazki;
    END;
procedure Pracownicy (pracownicyData OUT SYS_REFCURSOR)
    AS
    BEGIN
        OPEN pracownicyData FOR
            Select 
                *
            from Pracownicy;
    END;
procedure Pracownik (d_login IN VARCHAR2,d_haslo IN VARCHAR2,pracownikData OUT SYS_REFCURSOR)
    AS
    BEGIN
        OPEN pracownikData FOR
            Select 
                ID_PRACOWNIKA ,
                IMIE ,
                NAZWISKO ,
                PESEL ,
                DATA_URODZENIA ,
                ID_ADRESU ,
                LOGIN ,
                HASLO  
            from Pracownicy
            Where login=d_login AND haslo=d_haslo;
    END;
procedure Tagi (tagiData OUT SYS_REFCURSOR)
    AS
    BEGIN
        OPEN tagiData FOR
            Select 
                *
            from tag;
    END;
procedure Wydawnictwo (id_w IN INTEGER, wydawnictwoData OUT SYS_REFCURSOR)
    AS
    BEGIN
        OPEN wydawnictwoData FOR
            Select 
                *
            from wydawnictwa where id_w=id_wydawnictwa;
    END;
procedure Wydawnictwa (wydawnictwaData OUT SYS_REFCURSOR)
    AS
    BEGIN
        OPEN wydawnictwaData FOR
            Select 
                *
            from wydawnictwa;
    END;
procedure WypozyczeniaIlosc (id_ksiazki IN INTEGER,ilosc OUT INTEGER)
    AS
    BEGIN
        Select
            count(id_ksiazki)
        into
            ilosc
        from 
            wypozyczenia;
    END;
procedure WypozyczeniaDlaKsiazki (id_k IN INTEGER,wynik OUT array_t)
    AS
    BEGIN
        wynik:=wykrespopularnosci(id_k);
    END;
end;

/
--------------------------------------------------------
--  DDL for Function DIFFDATE
--------------------------------------------------------

  CREATE OR REPLACE NONEDITIONABLE FUNCTION "STUDENT"."DIFFDATE" (date1 in date, date2 in date)
return number is 
begin
  return date2-date1;
end;

/
--------------------------------------------------------
--  DDL for Function WYKRESPOPULARNOSCI
--------------------------------------------------------

  CREATE OR REPLACE NONEDITIONABLE FUNCTION "STUDENT"."WYKRESPOPULARNOSCI" (id_k NUMBER)
RETURN array_t as
    Cursor listaWypozyczen is Select * from wypozyczenia where id_ksiazki=id_k;
    roznica number;
    dataWPetli date;
    wynik array_t;
begin
    wynik:=array_t(0,0,0,0,0,0,0,0,0,0,0,0);
    for record in listaWypozyczen
    loop
        for i in 1..12
        loop
            roznica:=diffdate(record.data_wypozyczenia,CURRENT_DATE);
            if (roznica<(i*30) AND roznica>((i-1)*30)) then
                wynik(i):=wynik(i)+1;
            end if;
        end loop;
    end loop;

     for i in 1..12
        loop
            dbms_output.put_line(wynik(i)); 
        end loop;

    return wynik;
end;

/
--------------------------------------------------------
--  Constraints for Table AUTORZY
--------------------------------------------------------

  ALTER TABLE "STUDENT"."AUTORZY" ADD PRIMARY KEY ("ID_AUTORA")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
--------------------------------------------------------
--  Constraints for Table ADRES
--------------------------------------------------------

  ALTER TABLE "STUDENT"."ADRES" ADD PRIMARY KEY ("ID_ADRESU")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
--------------------------------------------------------
--  Constraints for Table WYDAWNICTWA
--------------------------------------------------------

  ALTER TABLE "STUDENT"."WYDAWNICTWA" ADD PRIMARY KEY ("ID_WYDAWNICTWA")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
  ALTER TABLE "STUDENT"."WYDAWNICTWA" ADD CONSTRAINT "UNIQUE_WYDAWNICTWO" UNIQUE ("NAZWA")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
--------------------------------------------------------
--  Constraints for Table KATEGORIE
--------------------------------------------------------

  ALTER TABLE "STUDENT"."KATEGORIE" ADD PRIMARY KEY ("ID_KATEGORII")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
  ALTER TABLE "STUDENT"."KATEGORIE" ADD CONSTRAINT "UNIQUE_KATEGORIA" UNIQUE ("NAZWA")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
--------------------------------------------------------
--  Constraints for Table TAG
--------------------------------------------------------

  ALTER TABLE "STUDENT"."TAG" ADD PRIMARY KEY ("ID_TAGU")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
  ALTER TABLE "STUDENT"."TAG" ADD CONSTRAINT "UNIQUE_TAG" UNIQUE ("NAZWA")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
--------------------------------------------------------
--  Constraints for Table PRACOWNICY
--------------------------------------------------------

  ALTER TABLE "STUDENT"."PRACOWNICY" ADD PRIMARY KEY ("ID_PRACOWNIKA")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
  ALTER TABLE "STUDENT"."PRACOWNICY" ADD CONSTRAINT "UNIQUE_PESEL" UNIQUE ("PESEL")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
--------------------------------------------------------
--  Constraints for Table KLIENCI
--------------------------------------------------------

  ALTER TABLE "STUDENT"."KLIENCI" ADD PRIMARY KEY ("ID_KLIENTA")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
--------------------------------------------------------
--  Constraints for Table KSIAZKI
--------------------------------------------------------

  ALTER TABLE "STUDENT"."KSIAZKI" ADD PRIMARY KEY ("ID_KSIAZKI")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
--------------------------------------------------------
--  Constraints for Table WYPOZYCZENIA
--------------------------------------------------------

  ALTER TABLE "STUDENT"."WYPOZYCZENIA" ADD PRIMARY KEY ("ID_WYPOZYCZENIA")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table AUTORZY_KSIAZKI
--------------------------------------------------------

  ALTER TABLE "STUDENT"."AUTORZY_KSIAZKI" ADD CONSTRAINT "FKDVWRWPFHH76KX1UPWA9M8HDBQ" FOREIGN KEY ("ID_AUTORA")
	  REFERENCES "STUDENT"."AUTORZY" ("ID_AUTORA") ENABLE;
  ALTER TABLE "STUDENT"."AUTORZY_KSIAZKI" ADD CONSTRAINT "FKRKQUYTFGNQ9PUG7F0ANC99SH3" FOREIGN KEY ("ID_KSIAZKI")
	  REFERENCES "STUDENT"."KSIAZKI" ("ID_KSIAZKI") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table KLIENCI
--------------------------------------------------------

  ALTER TABLE "STUDENT"."KLIENCI" ADD CONSTRAINT "FKFW6UGDC9H4RJJRXMH2RU3ASER" FOREIGN KEY ("ID_ADRESU")
	  REFERENCES "STUDENT"."ADRES" ("ID_ADRESU") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table KSIAZKI
--------------------------------------------------------

  ALTER TABLE "STUDENT"."KSIAZKI" ADD CONSTRAINT "FK13MGWOA26VWWX0RKU0A0MBSWY" FOREIGN KEY ("ID_KATEGORII")
	  REFERENCES "STUDENT"."KATEGORIE" ("ID_KATEGORII") ENABLE;
  ALTER TABLE "STUDENT"."KSIAZKI" ADD CONSTRAINT "FKC4E381TJ0QF35S66VEE5NH2CY" FOREIGN KEY ("ID_WYDAWNICTWA")
	  REFERENCES "STUDENT"."WYDAWNICTWA" ("ID_WYDAWNICTWA") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table KSIAZKI_TAG
--------------------------------------------------------

  ALTER TABLE "STUDENT"."KSIAZKI_TAG" ADD CONSTRAINT "FK31RVHVGTWKERM5R924K4XXW61" FOREIGN KEY ("ID_KSIAZKI")
	  REFERENCES "STUDENT"."KSIAZKI" ("ID_KSIAZKI") ENABLE;
  ALTER TABLE "STUDENT"."KSIAZKI_TAG" ADD CONSTRAINT "FKHD757IAPCAE528HQ9HMLVB2K4" FOREIGN KEY ("ID_TAGU")
	  REFERENCES "STUDENT"."TAG" ("ID_TAGU") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table PRACOWNICY
--------------------------------------------------------

  ALTER TABLE "STUDENT"."PRACOWNICY" ADD CONSTRAINT "FKB42V02S6RO3FJEYJ484FCOI4F" FOREIGN KEY ("ID_ADRESU")
	  REFERENCES "STUDENT"."ADRES" ("ID_ADRESU") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table WYDAWNICTWA
--------------------------------------------------------

  ALTER TABLE "STUDENT"."WYDAWNICTWA" ADD CONSTRAINT "FKLB2NY0V8VA7JB0WJ39JXI79HV" FOREIGN KEY ("ID_ADRESU")
	  REFERENCES "STUDENT"."ADRES" ("ID_ADRESU") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table WYPOZYCZENIA
--------------------------------------------------------

  ALTER TABLE "STUDENT"."WYPOZYCZENIA" ADD CONSTRAINT "FK88Q4V1MW216EWPUVBS6FJ1T25" FOREIGN KEY ("ID_KLIENTA")
	  REFERENCES "STUDENT"."KLIENCI" ("ID_KLIENTA") ENABLE;
  ALTER TABLE "STUDENT"."WYPOZYCZENIA" ADD CONSTRAINT "FK9WO3UM1HG8WRXI9EU91WEU93L" FOREIGN KEY ("ID_KSIAZKI")
	  REFERENCES "STUDENT"."KSIAZKI" ("ID_KSIAZKI") ENABLE;
  ALTER TABLE "STUDENT"."WYPOZYCZENIA" ADD CONSTRAINT "FK51FIMCPR7XM2Q4M46HGIY58JY" FOREIGN KEY ("ID_PRACOWNIKA")
	  REFERENCES "STUDENT"."PRACOWNICY" ("ID_PRACOWNIKA") ENABLE;
