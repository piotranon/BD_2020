package entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@NamedStoredProcedureQuery(
        name = "GETALLKSIAZKI",
        procedureName = "GETALLKSIAZKI"
)
@Entity
public class Ksiazki {
    @Id
    @GeneratedValue (generator = "ksiazki_increment")
    private BigDecimal id_ksiazki;
    private String tytul;
    private Date data_wydania;
    private BigDecimal ilosc;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="id_wydawnictwa",nullable = false)
    private Wydawnictwa wydawnictwo;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="id_kategorii",nullable = false)
    private Kategorie kategoria;

    @ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(
            name="autorzy_ksiazki",
            joinColumns = {@JoinColumn(name = "id_ksiazki")},
            inverseJoinColumns = {@JoinColumn(name="id_autora")}
    )
    private List<Autorzy> autorzy=new ArrayList<>();

    @ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(
            name="ksiazki_tag",
            joinColumns = {@JoinColumn(name = "id_ksiazki")},
            inverseJoinColumns = {@JoinColumn(name="id_tagu")}
    )
    private List<Tag> tags=new ArrayList<>();

    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "ksiazka"
    )
    private List<Wypozyczenia> wypozyczenia=new ArrayList<>();


    public Ksiazki() {
    }

    public BigDecimal getId_ksiazki() {
        return id_ksiazki;
    }

    public void setId_ksiazki(BigDecimal id_ksiazki) {
        this.id_ksiazki = id_ksiazki;
    }

    public String getTytul() {
        return tytul;
    }

    public void setTytul(String tytul) {
        this.tytul = tytul;
    }

    public Date getData_wydania() {
        return data_wydania;
    }

    public void setData_wydania(Date data_wydania) {
        this.data_wydania = data_wydania;
    }

    public BigDecimal getIlosc() {
        return ilosc;
    }

    public void setIlosc(BigDecimal ilosc) {
        this.ilosc = ilosc;
    }

    public Wydawnictwa getWydawnictwo() {
        return wydawnictwo;
    }

    public void setWydawnictwo(Wydawnictwa wydawnictwo) {
        this.wydawnictwo = wydawnictwo;
    }

    public Kategorie getKategoria() {
        return kategoria;
    }

    public void setKategoria(Kategorie kategoria) {
        this.kategoria = kategoria;
    }

    public List<Autorzy> getAutorzy() {
        return autorzy;
    }

    public void setAutorzy(List<Autorzy> autorzy) {
        this.autorzy = autorzy;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public List<Wypozyczenia> getWypozyczenia() {
        return wypozyczenia;
    }

    public void setWypozyczenia(List<Wypozyczenia> wypozyczenia) {
        this.wypozyczenia = wypozyczenia;
    }

    @Override
    public String toString() {
        return "Ksiazki{" +
                "id_ksiazki=" + id_ksiazki +
                ", tytul='" + tytul + '\'' +
                ", data_wydania=" + data_wydania +
                ", ilosc=" + ilosc +
                ", wydawnictwo=" + wydawnictwo +
                ", kategoria=" + kategoria +
                ", autorzy=" + autorzy +
                ", tags=" + tags +
                ", wypozyczenia=" + wypozyczenia +
                '}';
    }
}
