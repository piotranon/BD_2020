package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.function.Predicate;

import entity.Ksiazki;
import entity.Wypozyczenia;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class returnBook {

        @FXML
        private ResourceBundle resources;

        @FXML
        private URL location;

        @FXML
        private TableView<Wypozyczenia> tableview;

        @FXML
        private TableColumn<?, ?> tytuł;

        @FXML
        private TableColumn<?, ?> dataWypozyczenia;

        @FXML
        private TableColumn<?, ?> Imie;

        @FXML
        private TableColumn<?, ?> Nazwisko;

        @FXML
        private TextField search;

        @FXML
        void logout(ActionEvent event) throws IOException {
            render.books();
        }

        @FXML
        void menu(ActionEvent event) throws IOException {
            render.menu();
        }

        @FXML
        void returnBook(ActionEvent event) {
            if(tableview.getSelectionModel().getSelectedItem()!=null)
            {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Zwrot książki");
                alert.setHeaderText("Klikajac ok zwrócisz książkę.");
                alert.setContentText("Czy na pewno tego chcesz?");

                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK){
                    // ... user chose OK
                    System.out.println("1;");

                    java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
                    tableview.getSelectionModel().getSelectedItem().setData_zwrotu(date);

                    Ksiazki k=tableview.getSelectionModel().getSelectedItem().getKsiazka();
                    k.setIlosc(k.getIlosc()+1);
                    k.getWypozyczenia().add(tableview.getSelectionModel().getSelectedItem());

                    if(!db.session.getTransaction().isActive())
                        db.session.beginTransaction();
                    db.session.saveOrUpdate(k);
                    db.session.getTransaction().commit();

                    if(!db.session.getTransaction().isActive())
                        db.session.beginTransaction();
                    db.session.saveOrUpdate(tableview.getSelectionModel().getSelectedItem());
                    db.session.getTransaction().commit();

//                db.session.delete(db.session.get(Ksiazki.class,tableview.getSelectionModel().getSelectedItem().getId_ksiazki()));
//                db.tx.commit();
                    System.out.println("2;");
                    reload();
                }
            }else
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Wystąpił błąd");
                alert.setHeaderText("Dane nie poprawne.");
                alert.setContentText("Nie wybrałeś wypozyczenia które należy zakończyć.");
                alert.showAndWait();
            }
        }

        private List<Wypozyczenia> wypozyczeniaList = new ArrayList<>();
        @FXML
        void reloadDataToView(ActionEvent event) {
            reload();
        }

        void sortedList(List<Wypozyczenia> booksList) {
            ObservableList<Wypozyczenia> booksxml = (ObservableList<Wypozyczenia>) FXCollections.observableList(booksList);
            FilteredList<Wypozyczenia> filteredList = new FilteredList<Wypozyczenia>(booksxml);

            Predicate predicate = new Predicate() {
                public boolean test(Object o) {

                    Wypozyczenia w = new Wypozyczenia((Wypozyczenia) o);

                    if(w.getData_wypozyczenia().toString().toLowerCase().contains(search.getText().toLowerCase()))
                        return true;
                    else if(w.getKsiazka().getTytul().toLowerCase().contains(search.getText().toLowerCase()))
                        return true;
                    else if(w.getKlient().getImie().toLowerCase().contains(search.getText().toLowerCase()))
                        return true;
                    else if(w.getKlient().getNazwisko().toLowerCase().contains(search.getText().toLowerCase()))
                        return true;
                    return false;
                }
            };

            filteredList.setPredicate(predicate);

            SortedList<Wypozyczenia> sortedData = new SortedList<Wypozyczenia>(filteredList);
            sortedData.comparatorProperty().bind(tableview.comparatorProperty());
            tableview.setItems(sortedData);
        }

        public void reload() {
            if(!db.session.getTransaction().isActive())
                db.session.beginTransaction();
            wypozyczeniaList = db.loadAllData(Wypozyczenia.class);
            db.session.getTransaction().commit();

            Iterator it = wypozyczeniaList.iterator();
            while (it.hasNext())
            {
                Wypozyczenia w=(Wypozyczenia) it.next();
                if(w.getData_zwrotu()!=null)
                {
                    System.out.println("usunieto: "+w.toString());
                    it.remove();
                }
            }

            System.out.println("wypozyczenia:");
            for(int i=0;i<wypozyczeniaList.size();i++)
                System.out.println(wypozyczeniaList.get(i).toString());

            search.setText("");
            sortedList(wypozyczeniaList);
        }

        @FXML
        void initialize() {
            tytuł.setCellValueFactory(new PropertyValueFactory("tytul"));
            dataWypozyczenia.setCellValueFactory(new PropertyValueFactory("Data_wypozyczenia"));
            Imie.setCellValueFactory(new PropertyValueFactory("imie"));
            Nazwisko.setCellValueFactory(new PropertyValueFactory("nazwisko"));

            reload();
        }
    }