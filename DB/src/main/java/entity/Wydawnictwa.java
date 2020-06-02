package entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Wydawnictwa {
    @Id
    @GeneratedValue (generator = "wydawnictwa_increment")
    private int id_wydawnictwa;
    private String nazwa;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_adresu")
    private Adres adres;

    @OneToMany(
            cascade = CascadeType.MERGE,
            mappedBy = "wydawnictwo"
    )
    private List<Ksiazki> ksiazki=new ArrayList<>();

    public Wydawnictwa() {
    }

    public int getId_wydawnictwa() {
        return id_wydawnictwa;
    }

    public void setId_wydawnictwa(int id_wydawnictwa) {
        this.id_wydawnictwa = id_wydawnictwa;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public Adres getAdres() {
        return adres;
    }

    public void setAdres(Adres adres) {
        this.adres = adres;
    }

    public List<Ksiazki> getKsiazki() {
        return ksiazki;
    }

    public void setKsiazki(List<Ksiazki> ksiazki) {
        this.ksiazki = ksiazki;
    }

    @Override
    public String toString() {
        return "Wydawnictwa{" +
                "id_wydawnictwa=" + id_wydawnictwa +
                ", nazwa='" + nazwa + '\'' +
                ", adres=" + adres +
                '}';
    }
}
