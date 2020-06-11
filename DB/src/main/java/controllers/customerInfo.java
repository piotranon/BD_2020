package controllers;

        import java.net.URL;
        import java.util.*;

        import entity.Klienci;
        import entity.Ksiazki;
        import entity.Wypozyczenia;
        import javafx.collections.FXCollections;
        import javafx.collections.ObservableList;
        import javafx.event.ActionEvent;
        import javafx.fxml.FXML;
        import javafx.scene.control.*;
        import javafx.scene.control.cell.PropertyValueFactory;
        import javafx.stage.Stage;

public class customerInfo {
    public Klienci klient;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button close;

    @FXML
    private Label imie;

    @FXML
    private Label nazwisko;

    @FXML
    private Label ulica;

    @FXML
    private Label nrDomu;

    @FXML
    private Label miejscowosc;

    @FXML
    private TableView<Wypozyczenia> tableview;

    @FXML
    private TableColumn<?, ?> title;

    @FXML
    private TableColumn<?, ?> rentDate;

    @FXML
    private TableView<Wypozyczenia> tableview2;

    @FXML
    private TableColumn<?, ?> title2;

    @FXML
    private TableColumn<?, ?> rentDate2;

    @FXML
    private TableColumn<?, ?> returnDate2;

    @FXML
    void close(ActionEvent event) {
        ((Stage)close.getScene().getWindow()).close();
    }

    public void reload(){
        if(!db.session.getTransaction().isActive())
            db.session.beginTransaction();
        klient=db.session.load(Klienci.class,klient.getId_klienta());

        imie.setText(klient.getImie());
        nazwisko.setText(klient.getNazwisko());
        miejscowosc.setText(klient.getAdres().getMiejscowosc());
        ulica.setText(klient.getAdres().getUlica());
        nrDomu.setText(String.valueOf(klient.getAdres().getNumer_Budynku()));

        List<Wypozyczenia> listaWypozyczonych = new ArrayList<>();
        List<Wypozyczenia> listaDoZwrotu = new ArrayList<>();

        for(Wypozyczenia w:klient.getWypozyczenia())
        {
            if(w.getData_zwrotu()!=null)
                listaWypozyczonych.add(w);
            else
                listaDoZwrotu.add(w);
        }
        System.out.println("wypozyczone: "+listaWypozyczonych.toString());
        System.out.println("do zwrotu: "+listaDoZwrotu.toString());

        tableview.setItems((ObservableList<Wypozyczenia>) FXCollections.observableList(listaDoZwrotu));
        tableview2.setItems((ObservableList<Wypozyczenia>) FXCollections.observableList(listaWypozyczonych));
        tableview.refresh();
        tableview2.refresh();
    }

    @FXML
    void returnBook(ActionEvent event) {
        if (tableview.getSelectionModel().getSelectedItem() != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Zwrot książki");
            alert.setHeaderText("Klikajac ok zwrócisz książkę.");
            alert.setContentText("Czy na pewno tego chcesz?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                // ... user chose OK
                System.out.println("1;");

                java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
                tableview.getSelectionModel().getSelectedItem().setData_zwrotu(date);

                Ksiazki k = tableview.getSelectionModel().getSelectedItem().getKsiazka();
                k.setIlosc(k.getIlosc() + 1);
                k.getWypozyczenia().add(tableview.getSelectionModel().getSelectedItem());

                if (!db.session.getTransaction().isActive())
                    db.session.beginTransaction();
                db.session.saveOrUpdate(k);
                db.session.getTransaction().commit();

                if (!db.session.getTransaction().isActive())
                    db.session.beginTransaction();
                db.session.saveOrUpdate(tableview.getSelectionModel().getSelectedItem());
                db.session.getTransaction().commit();

//                db.session.delete(db.session.get(Ksiazki.class,tableview.getSelectionModel().getSelectedItem().getId_ksiazki()));
//                db.tx.commit();
                System.out.println("2;");
                reload();
            } else {
                Alert alert2 = new Alert(Alert.AlertType.ERROR);
                alert2.setTitle("Wystąpił błąd");
                alert2.setHeaderText("Dane nie poprawne.");
                alert2.setContentText("Nie wybrałeś książki z listy.");
                alert2.showAndWait();
            }
        }
    }

    @FXML
    void initialize() {
        title.setCellValueFactory(new PropertyValueFactory("tytul"));
        title2.setCellValueFactory(new PropertyValueFactory("tytul"));
        rentDate.setCellValueFactory(new PropertyValueFactory("Data_wypozyczenia"));
        rentDate2.setCellValueFactory(new PropertyValueFactory("Data_wypozyczenia"));
        returnDate2.setCellValueFactory(new PropertyValueFactory("Data_zwrotu"));
    }
}
