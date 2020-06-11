package entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Tag {
    @Id
    @GeneratedValue (generator = "TAG_SEQ")
    private int id_tagu;
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

    public Tag(int id_tagu,String nazwa) {
        this.id_tagu = id_tagu;
        this.nazwa = nazwa;
    }

    public int getId_tagu() {
        return id_tagu;
    }

    public void setId_tagu(int id_tagu) {
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
                '}';
    }
}
