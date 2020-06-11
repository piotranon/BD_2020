package entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Klienci {
    @Id
    @GeneratedValue (generator = "KLIENCI_SEQ")
    private int id_klienta;
    private String imie;
    private String nazwisko;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="id_adresu",nullable = false)
    private Adres adres;

    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "klient"
    )
    private List<Wypozyczenia> wypozyczenia=new ArrayList<>();

    public Klienci() {
    }

    public Klienci(Klienci k) {
        id_klienta=k.id_klienta;
        imie=k.imie;
        nazwisko=k.nazwisko;
        adres=k.adres;
        wypozyczenia=k.wypozyczenia;
    }

    public int getId_klienta() {
        return id_klienta;
    }

    public void setId_klienta(int id_klienta) {
        this.id_klienta = id_klienta;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public Adres getAdres() {
        return adres;
    }

    public void setAdres(Adres adres) {
        this.adres = adres;
    }

    public List<Wypozyczenia> getWypozyczenia() {
        return wypozyczenia;
    }

    public void setWypozyczenia(List<Wypozyczenia> wypozyczenia) {
        this.wypozyczenia = wypozyczenia;
    }

    public String getMiejscowosc(){return adres.getMiejscowosc();}

    public String getUlica(){return adres.getUlica();}

    public String getNrBudynku(){return  String.valueOf(adres.getNumer_Budynku());}

    @Override
    public String toString() {
        return "Klienci{" +
                "id_klienta=" + id_klienta +
                ", imie='" + imie + '\'' +
                ", nazwisko='" + nazwisko + '\'' +
                ", adres=" + adres +
                ", wypozyczenia=" + wypozyczenia +
                '}';
    }
}
