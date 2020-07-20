
# Projekt bazy danych Biblioteki w PL/SQL

## Spis treści
1. [Autor](https://github.com/piotranon/BD_2020_DlugoszPiotr#autor)
1. [Opis](https://github.com/piotranon/BD_2020_DlugoszPiotr#opis)
1. [Cel projektu / Funkcjonalności](https://github.com/piotranon/BD_2020_DlugoszPiotr#cel-projektu--funkcjonalno%C5%9Bci)
1. [Przedstawienie projektu](https://github.com/piotranon/BD_2020_DlugoszPiotr#przedstawienie-projektu)
1. [Technologie](https://github.com/piotranon/BD_2020_DlugoszPiotr#technologie)
1. [Baza danych](https://github.com/piotranon/BD_2020_DlugoszPiotr/blob/master/docs/sql.md) 
1. [Zmapowane obiekty w hibernate](https://github.com/piotranon/BD_2020_DlugoszPiotr#zmapowane-obiekty-w-hibernate)
1. [Uruchomienie](https://github.com/piotranon/BD_2020_DlugoszPiotr#uruchomienie)
1. [Dokumentacja Projektu](https://github.com/piotranon/BD_2020_DlugoszPiotr/blob/master/docs/Piotr%20D%C5%82ugosz%20Dokumentacja.docx)

#### Autor
Piotr Długosz

#### Opis
Projekt został wykonany na potrzeby zaliczenia z przemiotu "Bazy danych" na 2 roku informatyki na Uniwersytecie Rzeszowskim.
W projekcie stworzono Bazę danych w PL/SQL (w tym paczki z procedurami, funkcjami, trigerami, sekwencjami) [SQL z opisem](https://github.com/piotranon/BD_2020_DlugoszPiotr/blob/master/docs/sql.md).

#### Cel projektu / Funkcjonalności
Stworzenie w pełni funkcjonalnego niezależnego systemu, który mógłby zostać wykorzystany w bibliotece. Każdy użytkownik ma dostęp do spisu książek, który może przeszukiwać, wyświetlać więcej informacji odnośnie książki. Pracownicy księgarni mogą wypożyczać książki użytkownikom, zwracać je, edytować, dodawać nowe. Sprawdzać szczegółowe dane użytkowników po zalogowaniu.

#### Przedstawienie projektu [(YT)](https://www.youtube.com/watch?v=Bp3E7BYhgSk)
[![Przedstawienie projektu](https://img.youtube.com/vi/Bp3E7BYhgSk/maxresdefault.jpg)](https://www.youtube.com/watch?v=Bp3E7BYhgSk)

#### Technologie
Do stworzenia aplikacji zostały użyte technologie takie jak:
1. [JavaFx](https://openjfx.io/)
1. [Hibernate](https://hibernate.org/)

#### [Baza danych](https://github.com/piotranon/BD_2020_DlugoszPiotr/blob/master/docs/sql.md)

#### Zmapowane obiekty w hibernate

[Adres](https://github.com/piotranon/BD_2020_DlugoszPiotr/blob/5ec9a7fb946c9ab7cfd4f8f40856304629181e05/DB/src/main/java/entity/Adres.java#L8), [Autorzy](https://github.com/piotranon/BD_2020_DlugoszPiotr/blob/5ec9a7fb946c9ab7cfd4f8f40856304629181e05/DB/src/main/java/entity/Autorzy.java#L8), [Kategorie](https://github.com/piotranon/BD_2020_DlugoszPiotr/blob/5ec9a7fb946c9ab7cfd4f8f40856304629181e05/DB/src/main/java/entity/Kategorie.java#L8), [Klienci](https://github.com/piotranon/BD_2020_DlugoszPiotr/blob/5ec9a7fb946c9ab7cfd4f8f40856304629181e05/DB/src/main/java/entity/Klienci.java#L8), [Ksiazki](https://github.com/piotranon/BD_2020_DlugoszPiotr/blob/5ec9a7fb946c9ab7cfd4f8f40856304629181e05/DB/src/main/java/entity/Ksiazki.java#L10), [Pracownicy](https://github.com/piotranon/BD_2020_DlugoszPiotr/blob/5ec9a7fb946c9ab7cfd4f8f40856304629181e05/DB/src/main/java/entity/Pracownicy.java#L9), [Tag](https://github.com/piotranon/BD_2020_DlugoszPiotr/blob/5ec9a7fb946c9ab7cfd4f8f40856304629181e05/DB/src/main/java/entity/Tag.java#L8), [Wydawnictwa](https://github.com/piotranon/BD_2020_DlugoszPiotr/blob/5ec9a7fb946c9ab7cfd4f8f40856304629181e05/DB/src/main/java/entity/Wydawnictwa.java#L8), [Wypozyczenia](https://github.com/piotranon/BD_2020_DlugoszPiotr/blob/5ec9a7fb946c9ab7cfd4f8f40856304629181e05/DB/src/main/java/entity/Wypozyczenia.java#L9)

#### Uruchomienie
1. Pierwsze uruchomienie
   * Sklonuj repozytorium.
   * Stwórz bazę danych "Student" z loginem i hasłem "student". (zmieniając connection.url, connection.username, connection.password  w pliku [hibernate.cfg.xml](https://github.com/piotranon/BD_2020_DlugoszPiotr/blob/master/DB/src/main/resources/hibernate.cfg.xml) możemy zmienić dane do logowania)
   * Zaimportuj bazę danych z pliku [export.sql](https://github.com/piotranon/BD_2020_DlugoszPiotr/blob/master/export.sql)
   * Importujemy biblioteki mavenowe w środowisku.
   * Uruchamiamy aplikację przez klasę App.
   * W przykładowej bazie jest dodany jeden pracownik z loginem: "login" , oraz hasłem: "haslo" (na widoku z logowaniem domyślnie są przypisane te wartości nie trzeba ich wpisywać).
2. Ponowne uruchomienie
   * Uruchomienie aplikacji przez klasę App.
   
#### [Dokumentacja Projektu](https://github.com/piotranon/BD_2020_DlugoszPiotr/blob/master/docs/Piotr%20D%C5%82ugosz%20Dokumentacja.docx)
