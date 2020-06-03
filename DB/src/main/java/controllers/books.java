package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;

import entity.*;
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
import javafx.scene.control.Button;
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
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class books {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="search"
    private TextField search; // Value injected by FXMLLoader

    @FXML // fx:id="tableview"
    private TableView<Ksiazki> tableview; // Value injected by FXMLLoader

    @FXML // fx:id="name"
    private TableColumn<?, ?> name; // Value injected by FXMLLoader

    @FXML // fx:id="surname"
    private TableColumn<?, ?> amount; // Value injected by FXMLLoader

    @FXML // fx:id="pin"
    private TableColumn<?, ?> category; // Value injected by FXMLLoader

    @FXML // fx:id="join_date"
    private TableColumn<?, ?> wydawnictwo; // Value injected by FXMLLoader

    @FXML // fx:id="join_date1"
    private TableColumn<?, ?> tags; // Value injected by FXMLLoader

    @FXML
    private TableColumn<?, ?> autorzy;

    @FXML // fx:id="join_date2"
    private TableColumn<?, ?> popularnosc; // Value injected by FXMLLoader

    @FXML
    private Button button;

    private List<Ksiazki> localBooksList = new ArrayList<>();
    private double xOffset = 0;
    private double yOffset = 0;

    @FXML
    void bookDetails(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(render.class.getClassLoader().getClass().getResource("/scenes/editBook.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        editBook controller = (editBook) loader.getController();
        Stage stage = new Stage();
        stage.setTitle("BD 2020 Długosz Piotr");
        stage.initModality(Modality.WINDOW_MODAL);
        xOffset = 0;
        yOffset = 0;
        //move window easly
        controller.edited=tableview.getSelectionModel().getSelectedItem();
        controller.reload();
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
        stage.initOwner(button.getScene().getWindow());
        stage.setScene(new Scene(root));
        stage.showAndWait();
//        localBooksList.add(controller.nowa);
        reload();
    }

    @FXML
    void bookNew(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(render.class.getClassLoader().getClass().getResource("/scenes/addBook.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        addBook controller = (addBook) loader.getController();
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
        stage.initOwner(button.getScene().getWindow());
        stage.setScene(new Scene(root));
        stage.showAndWait();
        localBooksList.add(controller.nowa);
        reload();
    }

    @FXML
    void bookRemove(ActionEvent event) {

    }

    @FXML
    void bookRent(ActionEvent event) {

    }

    @FXML
    void limitList(KeyEvent event) {
        sortedList(localBooksList);
    }

    @FXML
    void logout(ActionEvent event) throws IOException {
        render.books();
    }

    @FXML
    void reloadDataToView(ActionEvent event) {
        reload();
    }

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
        sortedData.comparatorProperty().bind(tableview.comparatorProperty());

        tableview.setItems(sortedData);
    }

    private SessionFactory sessionFactory;
    private Session session;

    public void reload() {
        localBooksList = db.loadAllData(Ksiazki.class);
        System.out.println(localBooksList.toString());
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

        reload();
    }
}
