package entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Wypozyczenia {
    @Id
    @GeneratedValue (generator = "wypozyczenia_increment")
    private int id_wypozyczenia;

    @ManyToOne
    @JoinColumn(name="id_ksiazki",referencedColumnName = "id_ksiazki",nullable = false)
    private Ksiazki ksiazka;

    @ManyToOne
    @JoinColumn(name="id_pracownika",referencedColumnName = "id_pracownika",nullable = false)
    private Pracownicy pracownik;

    @ManyToOne
    @JoinColumn(name="id_klienta",referencedColumnName = "id_klienta",nullable = false)
    private Klienci klient;

    private Date data_wypozyczenia;
    private Date data_zwrotu;

    public Wypozyczenia() {
    }

    public int getId_wypozyczenia() {
        return id_wypozyczenia;
    }

    public void setId_wypozyczenia(int id_wypozyczenia) {
        this.id_wypozyczenia = id_wypozyczenia;
    }

    public Ksiazki getKsiazka() {
        return ksiazka;
    }

    public void setKsiazka(Ksiazki ksiazka) {
        this.ksiazka = ksiazka;
    }

    public Pracownicy getPracownik() {
        return pracownik;
    }

    public void setPracownik(Pracownicy pracownik) {
        this.pracownik = pracownik;
    }

    public Klienci getKlient() {
        return klient;
    }

    public void setKlient(Klienci klient) {
        this.klient = klient;
    }

    public Date getData_wypozyczenia() {
        return data_wypozyczenia;
    }

    public void setData_wypozyczenia(Date data_wypozyczenia) {
        this.data_wypozyczenia = data_wypozyczenia;
    }

    public Date getData_zwrotu() {
        return data_zwrotu;
    }

    public void setData_zwrotu(Date data_zwrotu) {
        this.data_zwrotu = data_zwrotu;
    }

    @Override
    public String toString() {
        return "Wypozyczenia{" +
                "id_wypozyczenia=" + id_wypozyczenia +
                ", ksiazka=" + ksiazka +
                ", pracownik=" + pracownik +
                ", klient=" + klient +
                ", data_wypozyczenia=" + data_wypozyczenia +
                ", data_zwrotu=" + data_zwrotu +
                ", ksiazka=" + ksiazka +
                '}';
    }
}