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

public class editCustomer {

    public Klienci klient=null;

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
    void edit(ActionEvent event) {
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
            klient.setId_klienta(klient.getId_klienta());
            klient.setImie(imie.getText());
            klient.setNazwisko(nazwisko.getText());

            klient.getAdres().setId_adresu(klient.getAdres().getId_adresu());
            klient.getAdres().setMiejscowosc(miejscowosc.getText());
            klient.getAdres().setNumer_Budynku(Integer.valueOf(nrBudynku.getText()));
            klient.getAdres().setKod_Pocztowy(kodpocztowy.getText());
            klient.getAdres().setUlica(ulica.getText());

//            if(!db.session.getTransaction().isActive())
//                db.session.beginTransaction();
//            db.session.saveOrUpdate(a);
//            db.session.getTransaction().commit();

            if(!db.session.getTransaction().isActive())
                db.session.beginTransaction();
            db.session.saveOrUpdate(klient);
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
    public void reload(){
        imie.setText(klient.getImie());
        nazwisko.setText(klient.getNazwisko());
        miejscowosc.setText(klient.getAdres().getMiejscowosc());
        kodpocztowy.setText(klient.getAdres().getKod_Pocztowy());
        ulica.setText(klient.getAdres().getUlica());
        nrBudynku.setText(String.valueOf(klient.getAdres().getNumer_Budynku()));
    }
    @FXML
    void initialize() {

    }
}
