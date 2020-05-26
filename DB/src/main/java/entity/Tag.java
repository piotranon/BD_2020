package entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Tag {
    @Id
    @GeneratedValue (generator = "tag_increment")
    private BigDecimal id_tagu;
    private String nazwa;

    @ManyToMany
    @JoinTable(
            name="ksiazki_tag",
            joinColumns = {@JoinColumn(name = "id_tagu")},
            inverseJoinColumns = {@JoinColumn(name="id_ksiazki")}
    )
    private List<Ksiazki> ksiazki = new ArrayList<Ksiazki>();

    public Tag() {
    }

    public BigDecimal getId_tagu() {
        return id_tagu;
    }

    public void setId_tagu(BigDecimal id_tagu) {
        this.id_tagu = id_tagu;
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
        return "Tag{" +
                "id_tagu=" + id_tagu +
                ", nazwa='" + nazwa + '\'' +
                ", ksiazki=" + ksiazki +
                '}';
    }
}
