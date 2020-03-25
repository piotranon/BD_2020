## Projekt bazy danych Biblioteki w PostgreSQL
#### Autor
Piotr Długosz 2 rok informatyki labolatoria 1 Uniwersytet Rzeszowski
#### Założenia
Wykonanie pełnego funkcjonalnego oprogramowania z interfejsem graficznym wykorzystując PostgreSQL.
#### Opis
Baza przechowuje informację na temat książek, autorów, wydawnictw, klientów, pracowników, wypożyczeń.
#### Relacje
Każdy pracownik, klient posiada jeden adres.  
Każdy pracownik, klient może uczestniczyć w wielu wypożyczeniach.  
Każda książka może zostać wypożyczona wiele razy.  
Każda książka posiada jedno wydawnictwo.  
Każda książka może zawierać wiele autorów, tagów.  
#### Schemat ERD
##### [erd-diagram-online](https://dbdiagram.io/d/5e7b39634495b02c3b88be7c)
![erd-diagram](./erd.png)
#### PostgreSQL Tabeli
```PostgreSQL
CREATE TABLE "pracownicy" (
  "id_pracownika" SERIAL PRIMARY KEY,
  "imie" varchar,
  "nazwisko" varchar,
  "pesel" varchar,
  "data_urodzenia" date,
  "id_adresu" int
);

CREATE TABLE "klienci" (
  "id_klienta" SERIAL PRIMARY KEY,
  "imie" varchar,
  "nazwisko" varchar,
  "id_adresu" int
);

CREATE TABLE "adres" (
  "id_adresu" SERIAL PRIMARY KEY,
  "miejscowosc" varchar,
  "kod_pocztowy" varchar,
  "ulica" varchar,
  "numer_budynku" int,
  "numer_lokalu" int
);

CREATE TABLE "ksiazki" (
  "id_ksiazki" SERIAL PRIMARY KEY,
  "tytul" varchar,
  "data_wydania" date,
  "ilosc" int,
  "id_wydawnictwa" int,
  "id_kategorii" int,
  "id_autora" int
);

CREATE TABLE "ksiazki_tag" (
  "id_ksiazki" int,
  "id_tagu" int
);

CREATE TABLE "tag" (
  "id_tagu" SERIAL PRIMARY KEY,
  "nazwa" varchar
);

CREATE TABLE "wydawnictwa" (
  "id_wydawnictwa" SERIAL PRIMARY KEY,
  "nazwa" varchar,
  "miejscowosc" varchar,
  "kod_pocztowy" varchar,
  "ulica" varchar,
  "numer_budynku" int
);

CREATE TABLE "autorzy" (
  "id_autora" SERIAL PRIMARY KEY,
  "imie" varchar,
  "nazwisko" varchar
);

CREATE TABLE "autorzy_ksiazki" (
  "id_autora" int,
  "id_ksiazki" int
);

CREATE TABLE "kategorie" (
  "id_kategorii" SERIAL PRIMARY KEY,
  "nazwa" varchar
);

CREATE TABLE "wypozyczenia" (
  "id_wypozyczenia" SERIAL PRIMARY KEY,
  "id_ksiazki" int,
  "id_pracownika" int,
  "id_klienta" int,
  "data_wpozyczenia" date,
  "data_zwrotu" date
);

ALTER TABLE "pracownicy" ADD FOREIGN KEY ("id_adresu") REFERENCES "adres" ("id_adresu");

ALTER TABLE "klienci" ADD FOREIGN KEY ("id_adresu") REFERENCES "adres" ("id_adresu");

ALTER TABLE "ksiazki" ADD FOREIGN KEY ("id_wydawnictwa") REFERENCES "wydawnictwa" ("id_wydawnictwa");

ALTER TABLE "ksiazki" ADD FOREIGN KEY ("id_kategorii") REFERENCES "kategorie" ("id_kategorii");

ALTER TABLE "ksiazki_tag" ADD FOREIGN KEY ("id_ksiazki") REFERENCES "ksiazki" ("id_ksiazki");

ALTER TABLE "ksiazki_tag" ADD FOREIGN KEY ("id_tagu") REFERENCES "tag" ("id_tagu");

ALTER TABLE "autorzy_ksiazki" ADD FOREIGN KEY ("id_autora") REFERENCES "autorzy" ("id_autora");

ALTER TABLE "autorzy_ksiazki" ADD FOREIGN KEY ("id_ksiazki") REFERENCES "ksiazki" ("id_ksiazki");

ALTER TABLE "wypozyczenia" ADD FOREIGN KEY ("id_ksiazki") REFERENCES "ksiazki" ("id_ksiazki");

ALTER TABLE "wypozyczenia" ADD FOREIGN KEY ("id_pracownika") REFERENCES "pracownicy" ("id_pracownika");

ALTER TABLE "wypozyczenia" ADD FOREIGN KEY ("id_klienta") REFERENCES "klienci" ("id_klienta");

```
