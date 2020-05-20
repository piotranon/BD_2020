package entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Pracownicy {
    @Id
    @GeneratedValue (generator = "pracownicy_increment")
    private int id_pracownika;
    private String imie;
    private String nazwisko;
    private String pesel;
    private Date data_urodzenia;

    @ManyToOne
    @JoinColumn(name="id_adresu",nullable = false)
    private Adres adres;

    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "pracownik"
    )
    private List<Wypozyczenia> wypozyczenia=new ArrayList<>();

    public Pracownicy() {
    }

    public int getId_pracownika() {
        return id_pracownika;
    }

    public void setId_pracownika(int id_pracownika) {
        this.id_pracownika = id_pracownika;
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

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public Date getData_urodzenia() {
        return data_urodzenia;
    }

    public void setData_urodzenia(Date data_urodzenia) {
        this.data_urodzenia = data_urodzenia;
    }

    public Adres getAdres() {
        return adres;
    }

    public void setAdres(Adres adres) {
        this.adres = adres;
    }

    public List<Wypozyczenia> getWypozyczenia() {
        return wypozyczenia;
    }

    public void setWypozyczenia(List<Wypozyczenia> wypozyczenia) {
        this.wypozyczenia = wypozyczenia;
    }

    @Override
    public String toString() {
        return "Pracownicy{" +
                "id_pracownika=" + id_pracownika +
                ", imie='" + imie + '\'' +
                ", nazwisko='" + nazwisko + '\'' +
                ", pesel='" + pesel + '\'' +
                ", data_urodzenia=" + data_urodzenia +
                ", adres=" + adres +
                ", wypozyczenia=" + wypozyczenia +
                '}';
    }
}
