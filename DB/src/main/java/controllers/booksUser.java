package controllers;

import entity.Ksiazki;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class booksUser {

    @FXML
    private TextField search;

    @FXML
    private TableView<Ksiazki> tableview;

    @FXML
    private TableColumn<?, ?> name;

    @FXML
    private TableColumn<?, ?> amount;

    @FXML
    private TableColumn<?, ?> category;

    @FXML
    private TableColumn<?, ?> wydawnictwo;

    @FXML
    private TableColumn<?, ?> autorzy;

    @FXML
    private TableColumn<?, ?> tags;

    @FXML
    private TableColumn<?, ?> popularnosc;

    private List<Ksiazki> localBooksList = new ArrayList<>();

    private double xOffset = 0;
    private double yOffset = 0;

    @FXML
    void bookDetails(ActionEvent event) {
        if(tableview.getSelectionModel().getSelectedItem()!=null)
        {
            FXMLLoader loader = new FXMLLoader(render.class.getClassLoader().getClass().getResource("/scenes/booksInfo.fxml"));
            Parent root = null;
            try {
                root = loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            booksInfo controller = (booksInfo) loader.getController();
            controller.book=tableview.getSelectionModel().getSelectedItem();
            controller.render();
            Stage stage = new Stage();
            stage.setTitle("BD 2020 Długosz Piotr");
            stage.initModality(Modality.WINDOW_MODAL);
            xOffset = 0;
            yOffset = 0;
            //move window easly

            root.setOnMousePressed(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    xOffset = event.getSceneX();
                    yOffset = event.getSceneY();
                }
            });

            root.setOnMouseDragged(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    stage.setX(event.getScreenX() - xOffset);
                    stage.setY(event.getScreenY() - yOffset);
                }
            });
            stage.initStyle(StageStyle.UNDECORATED);
            stage.initOwner(search.getScene().getWindow());
            stage.setScene(new Scene(root));
            stage.showAndWait();
        }else
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Wystąpił błąd");
            alert.setHeaderText("Dane nie poprawne.");
            alert.setContentText("Nie wybrałeś książki z listy.");
            alert.showAndWait();
        }
    }

    @FXML
    void limitList(KeyEvent event) {
        sortedList(localBooksList);
    }

    @FXML
    void login(ActionEvent event) throws IOException {
        render.login();
//        FXMLLoader loader = new FXMLLoader(render.class.getClassLoader().getClass().getResource("/scenes/login.fxml"));
//        Parent root = null;
//        try {
//            root = loader.load();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        login controller = (login) loader.getController();
//        Stage stage = new Stage();
//        stage.setTitle("BD 2020 Długosz Piotr");
//        stage.initModality(Modality.WINDOW_MODAL);
//        xOffset = 0;
//        yOffset = 0;
//        //move window easly
//
//        root.setOnMousePressed(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent event) {
//                xOffset = event.getSceneX();
//                yOffset = event.getSceneY();
//            }
//        });
//
//        root.setOnMouseDragged(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent event) {
//                stage.setX(event.getScreenX() - xOffset);
//                stage.setY(event.getScreenY() - yOffset);
//            }
//        });
//        stage.initStyle(StageStyle.UNDECORATED);
//        stage.initOwner(search.getScene().getWindow());
//        stage.setScene(new Scene(root));
//        stage.showAndWait();
    }

//    @FXML
//    void logout(ActionEvent event) throws IOException {
//        render.books();
//    }

//    @FXML
//    void menu(ActionEvent event) throws IOException {
//        render.menu();
//    }

    void sortedList(List<Ksiazki> booksList) {
        ObservableList<Ksiazki> booksxml = (ObservableList<Ksiazki>) FXCollections.observableList(booksList);
        FilteredList<Ksiazki> filteredList = new FilteredList<Ksiazki>(booksxml);

        Predicate predicate = new Predicate() {
            public boolean test(Object o) {

                Ksiazki c = new Ksiazki((Ksiazki) o);

                if (c.getTytul().toLowerCase().contains(search.getText().toLowerCase()))
                    return true;
                else if (c.getWydawnictwoName().toLowerCase().contains(search.getText().toLowerCase()))
                    return true;
                else if (c.getKategoriaName().toLowerCase().contains(search.getText().toLowerCase()))
                    return true;
                else if (String.valueOf(c.getPopularnosc()).toLowerCase().contains(search.getText().toLowerCase()))
                    return true;
                else if (c.getAutorzyNames().toLowerCase().contains(search.getText().toLowerCase()))
                    return true;
                else if (c.getTagsHash().toLowerCase().contains(search.getText().toLowerCase()))
                    return true;
                return false;
            }
        };

        filteredList.setPredicate(predicate);

        SortedList<Ksiazki> sortedData = new SortedList<Ksiazki>(filteredList);
        sortedData.comparatorProperty().bind((ObservableValue<? extends Comparator<? super Ksiazki>>) tableview.comparatorProperty());

        tableview.setItems(sortedData);
        tableview.refresh();
    }

//    private SessionFactory sessionFactory;
//    private Session session;

    public void reload() {
        if(!db.session.getTransaction().isActive())
            db.session.beginTransaction();
        localBooksList = db.loadAllData(Ksiazki.class);
        db.session.getTransaction().commit();

        search.setText("");
        sortedList(localBooksList);
    }

    @FXML
        // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        name.setCellValueFactory(new PropertyValueFactory("tytul"));
        amount.setCellValueFactory(new PropertyValueFactory("ilosc"));
        category.setCellValueFactory(new PropertyValueFactory("KategoriaName"));
        wydawnictwo.setCellValueFactory(new PropertyValueFactory("WydawnictwoName"));
        autorzy.setCellValueFactory(new PropertyValueFactory("AutorzyNames"));
        tags.setCellValueFactory(new PropertyValueFactory("TagsHash"));
        popularnosc.setCellValueFactory(new PropertyValueFactory("Popularnosc"));
    }

}
