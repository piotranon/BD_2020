## Projekt bazy danych Biblioteki w PostgreSQL
#### Autor
Piotr Długosz 2 rok informatyki labolatoria 1 Uniwersytet Rzeszowski
#### Założenia
Wykonanie pełnego funkcjonalnego oprogramowania z interfejsem graficznym wykorzystując Bazę danych Oracle PL/SQL.
#### Opis
Baza przechowuje informację na temat książek, autorów, wydawnictw, klientów, pracowników, wypożyczeń.
#### Relacje
Każdy pracownik, klient posiada jeden adres.  
Każdy pracownik, klient może uczestniczyć w wielu wypożyczeniach.  
Każda książka może zostać wypożyczona wiele razy.  
Każda książka posiada jedno wydawnictwo.  
Każda książka może zawierać wiele autorów, tagów.  
#### Schemat ERD

![erd-diagram](./erd.png)
#### PLSQL Tabeli
```PL/SQL
CREATE TABLE pracownicy (
  id_pracownika int PRIMARY KEY,
  imie varchar(255),
  nazwisko varchar(255),
  pesel varchar(255),
  data_urodzenia date,
  id_adresu int
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
  id_kategorii int,
  id_autora int
);
CREATE TABLE ksiazki_tag (
  id_ksiazki int,
  id_tagu int
);
CREATE TABLE tag (
  id_tagu int PRIMARY KEY,
  nazwa varchar(255)
);
CREATE TABLE wydawnictwa (
  id_wydawnictwa int PRIMARY KEY,
  nazwa varchar(255),
  miejscowosc varchar(255),
  kod_pocztowy varchar(255),
  ulica varchar(255),
  numer_budynku int
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
  nazwa varchar(255)
);
CREATE TABLE wypozyczenia (
  id_wypozyczenia int PRIMARY KEY,
  id_ksiazki int,
  id_pracownika int,
  id_klienta int,
  data_wpozyczenia date,
  data_zwrotu date
);

ALTER TABLE pracownicy ADD CONSTRAINT pracownik_adres FOREIGN KEY (id_adresu) REFERENCES adres (id_adresu);

ALTER TABLE klienci ADD CONSTRAINT klient_adres FOREIGN KEY (id_adresu) REFERENCES adres (id_adresu);

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
