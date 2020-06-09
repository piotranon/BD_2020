package controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.resource.transaction.spi.TransactionStatus;

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
    void editBook(ActionEvent event) throws IOException {
        if(tableview.getSelectionModel().getSelectedItem()!=null)
        {
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
            Ksiazki editedOne=tableview.getSelectionModel().getSelectedItem();
            controller.edited=editedOne;
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

            reload();
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
    void bookNew(ActionEvent event) throws IOException {
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
//        localBooksList.add(controller.nowa);
//        tableview.getItems().clear();
        reload();

    }
    @FXML
    void bookDetails(ActionEvent event)
    {
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
            stage.initOwner(button.getScene().getWindow());
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
    void bookRemove(ActionEvent event) {
        if(tableview.getSelectionModel().getSelectedItem()!=null)
        {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Potwierdzenie usuwania");
            alert.setHeaderText("Usuwanie książki");
            alert.setContentText("Jesteś pewien ,że chcesz to zrobić? Nie będzie możliwości przywrócenia tego.");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                // ... user chose OK

                if(!db.session.getTransaction().isActive())
                    db.session.beginTransaction();
                db.session.delete(tableview.getSelectionModel().getSelectedItem());
                db.session.getTransaction().commit();
//                db.session.delete(db.session.get(Ksiazki.class,tableview.getSelectionModel().getSelectedItem().getId_ksiazki()));
//                db.tx.commit();
//                db.tx.commit();
                reload();
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

    @FXML
    void menu(ActionEvent event) throws IOException {
        render.menu();
    }

    @FXML
    void bookRent(ActionEvent event) {
        if(tableview.getSelectionModel().getSelectedItem()!=null)
        {
            if(tableview.getSelectionModel().getSelectedItem().getIlosc()<=0)
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Wystąpił błąd");
                alert.setHeaderText("Błąd wypozyczenia.");
                alert.setContentText("Nie można wypożyczyć książki której nie mamy na stanie.");
                alert.showAndWait();
            }else {

                FXMLLoader loader = new FXMLLoader(render.class.getClassLoader().getClass().getResource("/scenes/rentBook.fxml"));
                Parent root = null;
                try {
                    root = loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                rentBook controller = (rentBook) loader.getController();
                controller.book = tableview.getSelectionModel().getSelectedItem();
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
                reload();
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

    @FXML
    void limitList(KeyEvent event) {
        sortedList(localBooksList);
    }

    @FXML
    void logout(ActionEvent event) throws IOException {
        render.books();
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
        tableview.refresh();
    }

    public void reload() {
        localBooksList.removeAll(localBooksList);

        if(!db.session.getTransaction().isActive())
            db.session.beginTransaction();
        localBooksList = db.loadAllData(Ksiazki.class);
        db.session.getTransaction().commit();

        System.out.println("books:");
        for(int i=0;i<localBooksList.size();i++)
            System.out.println(localBooksList.get(i).toString());

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
