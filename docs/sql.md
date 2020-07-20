## SQL BAZY DANYCH
### Spis treści
   1. [Relacje]()
   1. [Schemat ERD]()
   1. [Tabele](https://github.com/piotranon/BD_2020_DlugoszPiotr/blob/master/docs/sql.md#tabele)
   1. [Paczka pobranie](https://github.com/piotranon/BD_2020_DlugoszPiotr/blob/master/docs/sql.md#paczka-pobranie---zawiera-procedury-pobieraj%C4%85ce)
   1. [Paczka dodawanie](https://github.com/piotranon/BD_2020_DlugoszPiotr/blob/master/docs/sql.md#paczka-dodanie)
   1. [Trigery](https://github.com/piotranon/BD_2020_DlugoszPiotr/blob/master/docs/sql.md#trigery)
   1. [Sekwencje](https://github.com/piotranon/BD_2020_DlugoszPiotr/blob/master/docs/sql.md#sekwencje)
   1. [Klasa](https://github.com/piotranon/BD_2020_DlugoszPiotr/blob/master/docs/sql.md#klasa)
   1. [Funkcje](https://github.com/piotranon/BD_2020_DlugoszPiotr/blob/master/docs/sql.md#funkcje)
   1. [Wyeksportowana baza danych]((https://github.com/piotranon/BD_2020_DlugoszPiotr/blob/master/export.sql)
   
### Relacje
Każdy pracownik, klient posiada jeden adres.   
Wiele klientów, pracowników może posiadać jeden adres.  
Każdy pracownik, klient może uczestniczyć w wielu wypożyczeniach.  
Każda książka może zostać wypożyczona wiele razy.  
Każda książka posiada jedno wydawnictwo, kategorię.  
Każda książka może zawierać wiele autorów, tagów.

### Schemat ERD

![erd-diagram](./erd.png)

### Tabele
```sql
CREATE TABLE pracownicy (
        id_pracownika int PRIMARY KEY,
        imie varchar(255),
        nazwisko varchar(255),
        pesel varchar(255),
        data_urodzenia date,
        id_adresu int,
        login varchar(255),
        haslo varchar(255),
        CONSTRAINT unique_pesel UNIQUE (pesel,login)
);
CREATE TABLE klienci (
        id_klienta int PRIMARY KEY,
        imie varchar(255),
        nazwisko varchar(255),
        id_adresu int
);
CREATE TABLE adres (
        id_adresu int PRIMARY KEY,
        miejscowosc varchar(255),
        kod_pocztowy varchar(255),
        ulica varchar(255),
        numer_budynku int
);
CREATE TABLE ksiazki (
        id_ksiazki int PRIMARY KEY,
        tytul varchar(255),
        data_wydania date,
        ilosc int,
        id_wydawnictwa int,
        id_kategorii int
);
CREATE TABLE ksiazki_tag (
        id_ksiazki int,
        id_tagu int
);
CREATE TABLE tag (
        id_tagu int PRIMARY KEY,
        nazwa varchar(255),
        CONSTRAINT unique_tag UNIQUE (nazwa)
);
CREATE TABLE wydawnictwa (
        id_wydawnictwa int PRIMARY KEY,
        nazwa varchar(255),
        id_adresu int,
        CONSTRAINT unique_wydawnictwo UNIQUE (nazwa)
);
CREATE TABLE autorzy (
        id_autora int PRIMARY KEY,
        imie varchar(255),
        nazwisko varchar(255)
);
CREATE TABLE autorzy_ksiazki (
        id_autora int,
        id_ksiazki int
);
CREATE TABLE kategorie (
        id_kategorii int PRIMARY KEY,
        nazwa varchar(255),
        CONSTRAINT unique_kategoria UNIQUE (nazwa)
);
CREATE TABLE wypozyczenia (
        id_wypozyczenia int PRIMARY KEY,
        id_ksiazki int,
        id_pracownika int,
        id_klienta int,
        data_wypozyczenia date,
        data_zwrotu date
);
ALTER TABLE pracownicy ADD CONSTRAINT pracownik_adres FOREIGN KEY (id_adresu) REFERENCES adres (id_adresu);
    
ALTER TABLE klienci ADD CONSTRAINT klient_adres FOREIGN KEY (id_adresu) REFERENCES adres (id_adresu);
    
ALTER TABLE wydawnictwa ADD CONSTRAINT wydawnictwo_adres FOREIGN KEY (id_adresu) REFERENCES adres (id_adresu);
    
ALTER TABLE wypozyczenia ADD CONSTRAINT wypozyczenia_pracownik FOREIGN KEY (id_pracownika) REFERENCES pracownicy (id_pracownika);
ALTER TABLE wypozyczenia ADD CONSTRAINT wypozyczenia_klient FOREIGN KEY (id_klienta) REFERENCES klienci (id_klienta);
ALTER TABLE wypozyczenia ADD CONSTRAINT wypozyczenia_ksiazka FOREIGN KEY (id_ksiazki) REFERENCES ksiazki (id_ksiazki);
    
ALTER TABLE ksiazki ADD CONSTRAINT ksiazki_wypozyczenia FOREIGN KEY (id_ksiazki) REFERENCES wypozyczenia (id_ksiazki);
ALTER TABLE ksiazki ADD CONSTRAINT ksiazka_wydawnictwo FOREIGN KEY (id_wydawnictwa) REFERENCES wydawnictwa (id_wydawnictwa);
ALTER TABLE ksiazki ADD CONSTRAINT ksiazka_kategoria FOREIGN KEY (id_kategorii) REFERENCES kategorie (id_kategorii);
    
ALTER TABLE ksiazki_tag ADD CONSTRAINT tag_ksiazka FOREIGN KEY (id_ksiazki) REFERENCES ksiazki (id_ksiazki);
ALTER TABLE ksiazki_tag ADD CONSTRAINT ksiazka_tag2 FOREIGN KEY (id_tagu) REFERENCES tag (id_tagu);
    
ALTER TABLE autorzy_ksiazki ADD CONSTRAINT autorzy_ksiazka FOREIGN KEY (id_autora) REFERENCES autorzy (id_autora);
ALTER TABLE autorzy_ksiazki ADD CONSTRAINT ksiazka_autorzy FOREIGN KEY (id_ksiazki) REFERENCES ksiazki (id_ksiazki);
```
   
### Paczka pobranie - zawiera procedury pobierające 
```sql
create or replace NONEDITIONABLE package pobranie
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

create or replace NONEDITIONABLE package body pobranie
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
```
### Paczka dodanie
```sql
create or replace NONEDITIONABLE package dodawanie
is

procedure Autor (imie IN VARCHAR2, nazwisko IN VARCHAR2);
procedure Kategoria (nazwa IN VARCHAR2);
procedure Tag (nazwaTagu IN VARCHAR2);
procedure Adres (miejscowosc IN VARCHAR2,kod_pocztowy IN VARCHAR2,ulica IN VARCHAR2, nr_budynku IN INTEGER);
procedure Wydawnictwa (nazwa IN VARCHAR2, id_adresu IN INTEGER);
procedure Pracownicy (imie IN VARCHAR2,nazwisko IN VARCHAR2, pesel IN VARCHAR2, data_urodzenia IN Date,id_adresu IN INTEGER,login IN VARCHAR2,haslo IN VARCHAR2);

end;

create or replace NONEDITIONABLE package body dodawanie
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
```
### Trigery
```sql
create or replace NONEDITIONABLE TRIGGER ADRES_TRG 
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

create or replace NONEDITIONABLE TRIGGER AUTORZY_TRG 
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

create or replace NONEDITIONABLE TRIGGER KATEGORIE_TRG 
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

create or replace NONEDITIONABLE TRIGGER KLIENCI_TRG 
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

create or replace NONEDITIONABLE TRIGGER KlienciImie
BEFORE INSERT ON Klienci 
FOR EACH ROW
begin
IF LENGTH(TRIM(TRANSLATE(LOWER(:new.imie), ' +ąćęńłóśżźqabcdefghijklmnoprstuvwxyz',' '))) != 0 THEN
RAISE_APPLICATION_ERROR( -20033, 'Imie moze zawierac tylko litery' );
end if;
end;

create or replace NONEDITIONABLE TRIGGER KlienciNazwisko
BEFORE INSERT ON Klienci 
FOR EACH ROW
begin
IF LENGTH(TRIM(TRANSLATE(LOWER(:new.nazwisko), ' +ąćęńłóśżźqabcdefghijklmnoprstuvwxyz',' '))) != 0 THEN
RAISE_APPLICATION_ERROR( -20033, 'Imie moze zawierac tylko litery' );
end if;
end;

create or replace NONEDITIONABLE TRIGGER KSIAZKI_TRG 
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

create or replace NONEDITIONABLE TRIGGER NazwaMiejscowososci
BEFORE INSERT ON Adres 
FOR EACH ROW
begin
IF LENGTH(TRIM(TRANSLATE(LOWER(:new.miejscowosc), ' +ąćęńłóśżźqabcdefghijklmnoprstuvwxyz',' '))) != 0 THEN
RAISE_APPLICATION_ERROR( -20033, 'Imie moze zawierac tylko litery' );
end if;
end;

create or replace NONEDITIONABLE TRIGGER PRACOWNICY_TRG 
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

create or replace NONEDITIONABLE TRIGGER PracownicyImie
BEFORE INSERT ON Pracownicy 
FOR EACH ROW
begin
IF LENGTH(TRIM(TRANSLATE(LOWER(:new.imie), ' +ąćęńłóśżźqabcdefghijklmnoprstuvwxyz',' '))) != 0 THEN
RAISE_APPLICATION_ERROR( -20033, 'Imie moze zawierac tylko litery' );
end if;
end;

create or replace NONEDITIONABLE TRIGGER PracownicyNazwisko
BEFORE INSERT ON Pracownicy
FOR EACH ROW
begin
IF LENGTH(TRIM(TRANSLATE(LOWER(:new.nazwisko), ' +ąćęńłóśżźqabcdefghijklmnoprstuvwxyz',' '))) != 0 THEN
RAISE_APPLICATION_ERROR( -20033, 'Imie moze zawierac tylko litery' );
end if;
end;

create or replace NONEDITIONABLE TRIGGER PracownikPesel 
BEFORE INSERT ON pracownicy 
FOR EACH ROW
begin
IF LENGTH(TRIM(TRANSLATE(:new.PESEL, ' +-.0123456789',' '))) != 0 THEN
RAISE_APPLICATION_ERROR( -20031, 'PESEL moze zawierac tylko liczby' );
end if;
end;

create or replace NONEDITIONABLE TRIGGER TAG_TRG 
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

create or replace NONEDITIONABLE TRIGGER WYDAWNICTWA_TRG 
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

create or replace NONEDITIONABLE TRIGGER WYPOZYCZENIA_TRG 
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
```
### Sekwencje
```sql

CREATE SEQUENCE  "STUDENT"."ADRES_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 41 CACHE 20 NOORDER  NOCYCLE  NOKEEP  NOSCALE  GLOBAL ;
CREATE SEQUENCE  "STUDENT"."AUTORZY_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 81 CACHE 20 NOORDER  NOCYCLE  NOKEEP  NOSCALE  GLOBAL ;
CREATE SEQUENCE  "STUDENT"."KATEGORIE_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 61 CACHE 20 NOORDER  NOCYCLE  NOKEEP  NOSCALE  GLOBAL ;
CREATE SEQUENCE  "STUDENT"."KLIENCI_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 41 CACHE 20 NOORDER  NOCYCLE  NOKEEP  NOSCALE  GLOBAL ;
CREATE SEQUENCE  "STUDENT"."KSIAZKI_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 81 CACHE 20 NOORDER  NOCYCLE  NOKEEP  NOSCALE  GLOBAL ;
CREATE SEQUENCE  "STUDENT"."PRACOWNICY_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 41 CACHE 20 NOORDER  NOCYCLE  NOKEEP  NOSCALE  GLOBAL ;
CREATE SEQUENCE  "STUDENT"."TAG_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 61 CACHE 20 NOORDER  NOCYCLE  NOKEEP  NOSCALE  GLOBAL ;
CREATE SEQUENCE  "STUDENT"."WYDAWNICTWA_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 61 CACHE 20 NOORDER  NOCYCLE  NOKEEP  NOSCALE  GLOBAL ;
CREATE SEQUENCE  "STUDENT"."WYPOZYCZENIA_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 61 CACHE 20 NOORDER  NOCYCLE  NOKEEP  NOSCALE  GLOBAL ;

```

### Klasa
```sql
create or replace NONEDITIONABLE type array_t IS VARRAY(12) OF INTEGER;
```

### Funkcje
```sql
create or replace NONEDITIONABLE function diffdate (date1 in date, date2 in date)
return number is 
begin
  return date2-date1;
end;

create or replace NONEDITIONABLE function wykresPopularnosci (id_k NUMBER)
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
```
