package entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@NamedStoredProcedureQuery(
        name = "getWypozyczenie",
        procedureName = "getWypozyczenie",
        parameters = {
                @StoredProcedureParameter(mode = ParameterMode.IN,type = Integer.class),
                @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, type = void.class)
        }
)
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
    public Wypozyczenia(Wypozyczenia w) {
        id_wypozyczenia=w.id_wypozyczenia;
        ksiazka=w.ksiazka;
        pracownik=w.pracownik;
        klient=w.klient;
        data_wypozyczenia=w.data_wypozyczenia;
        data_zwrotu=w.data_zwrotu;
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

    public String getTytul()
    {
        return ksiazka.getTytul();
    }

    public String getImie()
    {
        return klient.getImie();
    }

    public String getNazwisko()
    {
        return klient.getNazwisko();
    }

    @Override
    public String toString() {
        return "Wypozyczenia{" +
                "id_wypozyczenia=" + id_wypozyczenia +
                ", ksiazka=" + ksiazka +
                ", pracownik=" + pracownik +
                ", data_wypozyczenia=" + data_wypozyczenia +
                ", data_zwrotu=" + data_zwrotu +
                ", ksiazka=" + ksiazka +
                '}';
    }
}
