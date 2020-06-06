package controllers;

import entity.Adres;
import entity.Wydawnictwa;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class addPublish {

    @FXML
    private TextField name;

    @FXML
    private TextField miejscowosc;

    @FXML
    private TextField kodpocztowy;

    @FXML
    private TextField ulica;

    @FXML
    private Button clearing;

    @FXML
    private TextField nrbudynku;

    private Wydawnictwa wydawnictwa=new Wydawnictwa();

    public Wydawnictwa getWydawnictwa() {
        return wydawnictwa;
    }

    @FXML
    void add(ActionEvent event) {
        boolean validData = true;
        if(name.getText().length()<1)
            validData = false;
        if(miejscowosc.getText().length()<1)
            validData = false;
        if(kodpocztowy.getText().length()<1)
            validData = false;
        if(ulica.getText().length()<1)
            validData = false;
        if(nrbudynku.getText().length()<1)
            validData = false;

        if(validData)
        {
            wydawnictwa.setNazwa(name.getText());
            Adres ad=new Adres();
            ad.setUlica(ulica.getText());
            ad.setMiejscowosc(miejscowosc.getText());
            ad.setKod_Pocztowy(kodpocztowy.getText());
            ad.setNumer_Budynku(Integer.valueOf(nrbudynku.getText()));
            ad.setWydawnictwo(wydawnictwa);
            wydawnictwa.setAdres(ad);
            db.session.save(ad);
            db.session.saveOrUpdate(wydawnictwa);
            db.session.getTransaction().commit();
            ((Stage)clearing.getScene().getWindow()).close();
        }else
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Wystąpił błąd");
            alert.setContentText("Nie wszystkie podane dane są poprawne");
            alert.showAndWait();
        }

    }

    @FXML
    void clear(ActionEvent event) {
        ((Stage)clearing.getScene().getWindow()).close();
    }

}
