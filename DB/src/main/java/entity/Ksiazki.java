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
    private int id_ksiazki;
    private String tytul;
    private Date data_wydania;
    private int ilosc;

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

    public Ksiazki(Ksiazki ksiazka) {
        this.id_ksiazki=ksiazka.getId_ksiazki();
        this.tytul=ksiazka.getTytul();
        this.data_wydania=ksiazka.getData_wydania();
        this.ilosc=ksiazka.getIlosc();
        this.wydawnictwo=ksiazka.getWydawnictwo();
        this.kategoria=ksiazka.getKategoria();
        this.autorzy=ksiazka.getAutorzy();
        this.tags=ksiazka.getTags();
    }

    public String getWydawnictwoName()
    {
        return wydawnictwo.getNazwa();
    }

    public String getKategoriaName()
    {
        return kategoria.getNazwa();
    }

    public String getTagsWithHash()
    {
        StringBuilder xd = new StringBuilder("");
        for(int i=0;i<tags.size();i++)
        {
            xd.append("#"+tags.get(i)+" ");
        }
        return xd.toString();
    }

    public String getAutorzyNames()
    {
        StringBuilder xd = new StringBuilder("");
        for(int i=0;i<autorzy.size();i++)
        {
            xd.append(autorzy.get(i).getImie()+" "+autorzy.get(i).getNazwisko().substring(0,1)+". ,");
        }
        if(xd.length()>1)
            xd.deleteCharAt(xd.length()-1);
        return xd.toString();
    }


    public int getPopularnosc()
    {
        return wypozyczenia.size();
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
