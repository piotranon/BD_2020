package controllers;


import java.net.URL;
        import java.util.ResourceBundle;

import entity.Adres;
import entity.Klienci;
import javafx.event.ActionEvent;
        import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
        import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class addCustomer {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField imie;

    @FXML
    private TextField nazwisko;

    @FXML
    private TextField miejscowosc;

    @FXML
    private TextField kodpocztowy;

    @FXML
    private TextField ulica;

    @FXML
    private TextField nrBudynku;

    @FXML
    private Button cancel;

    @FXML
    void add(ActionEvent event) {
        boolean validData=true;

        if(imie.getText().length()<1)
            validData=false;
        if(nazwisko.getText().length()<1)
            validData=false;
        if(miejscowosc.getText().length()<1)
            validData=false;
        if(kodpocztowy.getText().length()<1)
            validData=false;
        if(ulica.getText().length()<1)
            validData=false;
        if(nrBudynku.getText().length()<1)
            validData=false;

        if(validData)
        {
            Klienci nowy=new Klienci();
            nowy.setImie(imie.getText());
            nowy.setNazwisko(nazwisko.getText());
            Adres a=new Adres();
            a.setMiejscowosc(miejscowosc.getText());
            a.setNumer_Budynku(Integer.valueOf(nrBudynku.getText()));
            a.setKod_Pocztowy(kodpocztowy.getText());
            a.setUlica(ulica.getText());
            nowy.setAdres(a);

            if(!db.session.getTransaction().isActive())
                db.session.beginTransaction();
            db.session.saveOrUpdate(a);
            db.session.getTransaction().commit();

            if(!db.session.getTransaction().isActive())
                db.session.beginTransaction();
            db.session.saveOrUpdate(nowy);
            db.session.getTransaction().commit();

            cancel(event);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Wystąpił błąd");
            alert.setContentText("Nie wszystkie podane dane są poprawne");
            alert.showAndWait();
        }
    }

    @FXML
    void cancel(ActionEvent event) {
        ((Stage) cancel.getScene().getWindow()).close();
    }

    @FXML
    void initialize() {

    }
}
