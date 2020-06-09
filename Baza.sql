CREATE TABLE pracownicy (
        id_pracownika int PRIMARY KEY,
        imie varchar(255),
        nazwisko varchar(255),
        pesel varchar(255),
        data_urodzenia date,
        id_adresu int,
        login varchar(255),
        haslo varchar(255),
        CONSTRAINT unique_pesel UNIQUE (pesel)
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
        data_wpozyczenia date,
        data_zwrotu date
);