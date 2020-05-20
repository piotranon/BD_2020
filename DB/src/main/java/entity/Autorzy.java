package entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Autorzy {
    @Id
    @GeneratedValue (generator = "autorzy_increment")
    private int id_autora;

    private String imie;
    private String nazwisko;

    @ManyToMany
    @JoinTable(
            name="autorzy_ksiazki",
            joinColumns = {@JoinColumn(name = "id_autora")},
            inverseJoinColumns = {@JoinColumn(name="id_ksiazki")}
    )
    private List<Ksiazki> ksiazki=new ArrayList<>();

    public Autorzy() {
    }

    public int getId_autora() {
        return id_autora;
    }

    public void setId_autora(int id_autora) {
        this.id_autora = id_autora;
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

    public List<Ksiazki> getKsiazki() {
        return ksiazki;
    }

    public void setKsiazki(List<Ksiazki> ksiazki) {
        this.ksiazki = ksiazki;
    }

    @Override
    public String toString() {
        return "Autorzy{" +
                "id_autora=" + id_autora +
                ", imie='" + imie + '\'' +
                ", nazwisko='" + nazwisko + '\'' +
                ", ksiazki=" + ksiazki +
                '}';
    }
}
