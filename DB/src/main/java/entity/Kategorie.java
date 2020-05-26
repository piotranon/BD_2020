package entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Kategorie {
    @Id
    @GeneratedValue (generator = "kategorie_increment")
    private BigDecimal id_kategorii;
    private String nazwa;

    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "kategoria"
    )
    private List<Ksiazki> ksiazki=new ArrayList<>();

    public Kategorie() {
    }

    public BigDecimal getId_kategorii() {
        return id_kategorii;
    }

    public void setId_kategorii(BigDecimal id_kategorii) {
        this.id_kategorii = id_kategorii;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public List<Ksiazki> getKsiazki() {
        return ksiazki;
    }

    public void setKsiazki(List<Ksiazki> ksiazki) {
        this.ksiazki = ksiazki;
    }

    @Override
    public String toString() {
        return "Kategorie{" +
                "id_kategorii=" + id_kategorii +
                ", nazwa='" + nazwa + '\'' +
                ", ksiazki=" + ksiazki +
                '}';
    }
}
