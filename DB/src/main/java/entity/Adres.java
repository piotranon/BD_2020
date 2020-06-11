package entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Adres {
    @Id
    @GeneratedValue (generator = "ADRES_SEQ")
    private int id_adresu;
    private String miejscowosc;
    private String kod_Pocztowy;
    private String ulica;
    private int numer_Budynku;

    @OneToMany(
            cascade = CascadeType.MERGE,
            mappedBy = "adres"
    )
    private List<Pracownicy> pracownicy=new ArrayList<>();

    @OneToMany(
            cascade = CascadeType.MERGE ,
            mappedBy = "adres"
    )
    private List<Klienci> klienci=new ArrayList<>();

    @OneToOne(mappedBy = "adres")
    private Wydawnictwa wydawnictwo;

    public Adres() {
    }

    public int getId_adresu() {
        return id_adresu;
    }

    public void setId_adresu(int id_adresu) {
        this.id_adresu = id_adresu;
    }

    public String getMiejscowosc() {
        return miejscowosc;
    }

    public void setMiejscowosc(String miejscowosc) {
        this.miejscowosc = miejscowosc;
    }

    public String getKod_Pocztowy() {
        return kod_Pocztowy;
    }

    public void setKod_Pocztowy(String kod_Pocztowy) {
        this.kod_Pocztowy = kod_Pocztowy;
    }

    public String getUlica() {
        return ulica;
    }

    public void setUlica(String ulica) {
        this.ulica = ulica;
    }

    public int getNumer_Budynku() {
        return numer_Budynku;
    }

    public void setNumer_Budynku(int numer_Budynku) {
        this.numer_Budynku = numer_Budynku;
    }

    public List<Pracownicy> getPracownicy() {
        return pracownicy;
    }

    public void setPracownicy(List<Pracownicy> pracownicy) {
        this.pracownicy = pracownicy;
    }

    public List<Klienci> getKlienci() {
        return klienci;
    }

    public void setKlienci(List<Klienci> klienci) {
        this.klienci = klienci;
    }

    public Wydawnictwa getWydawnictwo() {
        return wydawnictwo;
    }

    public void setWydawnictwo(Wydawnictwa wydawnictwo) {
        this.wydawnictwo = wydawnictwo;
    }

    @Override
    public String toString() {
        return "Adres{" +
                "id_adresu=" + id_adresu +
                ", miejscowosc='" + miejscowosc + '\'' +
                ", kod_Pocztowy='" + kod_Pocztowy + '\'' +
                ", ulica='" + ulica + '\'' +
                ", numer_Budynku=" + numer_Budynku +
                '}';
    }
}
