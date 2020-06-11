###Dokumentacja Projektu
1. **Spis treści**

   - Dokumentacja procedur
       1. GETPRACOWNIK

   - Dokumentacja widoków
       1. Panel logowania
       1. Menu

1. **Pakiety**
        **DODAWANIE**
       ```
           create or replace NONEDITIONABLE package dodawanie
           is
           
           procedure Autor (imie IN VARCHAR2, nazwisko IN VARCHAR2);
           procedure Kategoria (nazwa IN VARCHAR2);
           procedure Tag (nazwaTagu IN VARCHAR2);
           procedure Adres (miejscowosc IN VARCHAR2,kod_pocztowy IN VARCHAR2,ulica IN VARCHAR2, nr_budynku IN INTEGER);
           procedure Wydawnictwa (nazwa IN VARCHAR2, id_adresu IN INTEGER);
           procedure Pracownicy (imie IN VARCHAR2,nazwisko IN VARCHAR2, pesel IN VARCHAR2, data_urodzenia IN Date,id_adresu IN INTEGER,login IN VARCHAR2,haslo IN VARCHAR2);
           
           end;
        ```
1. **Widoki**
   - **Panel logowania**  
    Wykorzystuję procedurę ["GETPRACOWNIK"](). Dane pracownika zapisuje do pamięci.  
    
        ![Wygląd panelu logowania](./login.png)
    
   - **Menu**   
    Zawiera przyciski do korzystania z różnych funkcjonalności projektu. 
      
        ![Wyglad_menu](./menu.png)
        
   - **Menu**