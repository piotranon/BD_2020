## SQL BAZY DANYCH
### SPIS
   1. [Paczka pobranie]()
   1. [Paczka dodawanie]()
   1. [Trigery]()
   1. [Sekwencje]()
   1. [Klasa]()
   1. [Funkcje]()
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
