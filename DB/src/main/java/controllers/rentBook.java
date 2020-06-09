package controllers;


import java.net.URL;
import java.util.*;
import java.util.function.Predicate;

import entity.Klienci;
import entity.Ksiazki;
import entity.Wypozyczenia;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
        import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class rentBook {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<Klienci> tableview;

    @FXML
    private TableColumn<?, ?> imie;

    @FXML
    private TableColumn<?, ?> nazwisko;

    @FXML
    private TableColumn<?, ?> miejscowosc;

    @FXML
    private TableColumn<?, ?> ulica;

    @FXML
    private TableColumn<?, ?> nrdomu;

    @FXML
    private TextField search;

    @FXML
    private Button cancel;

    public Ksiazki book=new Ksiazki();

    @FXML
    void cancel(ActionEvent event) {
        ((Stage)cancel.getScene().getWindow()).close();
    }

    @FXML
    void rent(ActionEvent event) {
        if(tableview.getSelectionModel().getSelectedItem()!=null)
        {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Czy jesteś pewien.");
            alert.setHeaderText("Czy na pewno dany klient chce wypożyczyć książkę?");
            alert.setContentText("Klient: "+tableview.getSelectionModel().getSelectedItem().getImie()+" "+tableview.getSelectionModel().getSelectedItem().getNazwisko()+"\nKsiażka: "+book.getTytul());

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                // ... user chose OK

                Wypozyczenia w=new Wypozyczenia();
                w.setKlient(tableview.getSelectionModel().getSelectedItem());
                java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
                w.setData_wypozyczenia(date);
                w.setPracownik(login.zalogowany);
                book.setIlosc(book.getIlosc()-1);
                w.setKsiazka(book);

                if(!db.session.getTransaction().isActive())
                    db.session.beginTransaction();
                db.session.saveOrUpdate(book);
                db.session.getTransaction().commit();
                if(!db.session.getTransaction().isActive())
                    db.session.beginTransaction();
                db.session.saveOrUpdate(w);
                db.session.getTransaction().commit();

                cancel(event);
            }
        }else
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Wystąpił błąd");
            alert.setHeaderText("Dane nie poprawne.");
            alert.setContentText("Nie wybrałeś książki z listy.");
            alert.showAndWait();
        }



    }

    private List<Klienci> klienciList=new ArrayList<>();

    @FXML
    void limitList(KeyEvent event) {
        sortedList(klienciList);
    }

    void sortedList(List<Klienci> booksList) {
        ObservableList<Klienci> booksxml = (ObservableList<Klienci>) FXCollections.observableList(booksList);
        FilteredList<Klienci> filteredList = new FilteredList<Klienci>(booksxml);

        Predicate predicate = new Predicate() {
            public boolean test(Object o) {
                Klienci k = new Klienci((Klienci) o);

                if(k.getImie().toLowerCase().contains(search.getText().toLowerCase()))
                    return true;
                else if(k.getNazwisko().toLowerCase().contains(search.getText().toLowerCase()))
                    return true;
                else if((String.valueOf(k.getAdres().getNumer_Budynku())).toLowerCase().contains(search.getText().toLowerCase()))
                    return true;
                else if(k.getAdres().getUlica().toLowerCase().contains(search.getText().toLowerCase()))
                    return true;
                else if(k.getAdres().getMiejscowosc().toLowerCase().contains(search.getText().toLowerCase()))
                    return true;

                return false;
            }
        };

        filteredList.setPredicate(predicate);

        SortedList<Klienci> sortedData = new SortedList<Klienci>(filteredList);
        sortedData.comparatorProperty().bind(tableview.comparatorProperty());

        tableview.setItems(sortedData);
    }

    public void reload() {
        if(!db.session.getTransaction().isActive())
            db.session.beginTransaction();
        klienciList = db.loadAllData(Klienci.class);
        db.session.getTransaction().commit();

        System.out.println("klienci");
        for(int i=0;i<klienciList.size();i++)
            System.out.println(klienciList.get(i).toString());

        search.setText("");
        sortedList(klienciList);
    }

    @FXML
    void initialize() {
        imie.setCellValueFactory(new PropertyValueFactory("imie"));
        nazwisko.setCellValueFactory(new PropertyValueFactory("nazwisko"));
        miejscowosc.setCellValueFactory(new PropertyValueFactory("miejscowosc"));
        ulica.setCellValueFactory(new PropertyValueFactory("ulica"));
        nrdomu.setCellValueFactory(new PropertyValueFactory("NrBudynku"));
        reload();
    }
}
