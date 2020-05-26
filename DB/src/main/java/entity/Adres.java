package entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@NamedStoredProcedureQuery(
        name = "GETADRES",
        procedureName = "GETADRES"
)
@Entity
public class Adres {
    @Id
    @GeneratedValue (generator = "ADRES_INCREMENT")
    private BigDecimal id_adresu;
    private String miejscowosc;
    private String kod_Pocztowy;
    private String ulica;
    private BigDecimal numer_Budynku;

    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "adres"
    )
    private List<Pracownicy> pracownicy=new ArrayList<>();

    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "adres"
    )
    private List<Klienci> klienci=new ArrayList<>();

    @OneToOne(mappedBy = "adres")
    private Wydawnictwa wydawnictwo;

    public Adres() {
    }

    public BigDecimal getId_adresu() {
        return id_adresu;
    }

    public void setId_adresu(BigDecimal id_adresu) {
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

    public BigDecimal getNumer_Budynku() {
        return numer_Budynku;
    }

    public void setNumer_Budynku(BigDecimal numer_Budynku) {
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
}
