package entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
public class Ksiazki {
    @Id
    @GeneratedValue (generator = "ksiazki_increment")
    private int id_ksiazki;
    private String tytul;
    private Date data_wydania;
    private int ilosc;

    @ManyToOne
    @JoinColumn(name="id_wydawnictwa",nullable = false)
    private Wydawnictwa wydawnictwo;

    @ManyToOne
    @JoinColumn(name="id_kategorii",nullable = false)
    private Kategorie kategoria;

    @ManyToMany
    @JoinTable(
            name="autorzy_ksiazki",
            joinColumns = {@JoinColumn(name = "id_ksiazki")},
            inverseJoinColumns = {@JoinColumn(name="id_autora")}
    )
    private List<Autorzy> autorzy=new ArrayList<>();

    @ManyToMany
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

    public int getId_ksiazki() {
        return id_ksiazki;
    }

    public void setId_ksiazki(int id_ksiazki) {
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

    public int getIlosc() {
        return ilosc;
    }

    public void setIlosc(int ilosc) {
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
