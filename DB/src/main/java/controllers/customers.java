package controllers;

        import java.io.IOException;
        import java.net.URL;
        import java.util.ArrayList;
        import java.util.List;
        import java.util.Optional;
        import java.util.ResourceBundle;
        import java.util.function.Predicate;

        import entity.Klienci;
        import entity.Ksiazki;
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

public class customers {
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

    private List<Klienci> klienciList=new ArrayList<>();
    private double xOffset = 0;
    private double yOffset = 0;

    @FXML
    void deleteClient(ActionEvent event) {
        if(tableview.getSelectionModel().getSelectedItem()!=null)
        {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Potwierdzenie usuwania");
            alert.setHeaderText("Usuwanie klienta");
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
            alert.setContentText("Nie wybrałeś klienta z listy.");
            alert.showAndWait();
        }
    }

    @FXML
    void editCustomer(ActionEvent event) {
        if(tableview.getSelectionModel().getSelectedItem()!=null)
        {
            FXMLLoader loader = new FXMLLoader(render.class.getClassLoader().getClass().getResource("/scenes/editCustomer.fxml"));
            Parent root = null;
            try {
                root = loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            editCustomer controller = (editCustomer) loader.getController();
            Stage stage = new Stage();
            stage.setTitle("BD 2020 Długosz Piotr");
            stage.initModality(Modality.WINDOW_MODAL);
            xOffset = 0;
            yOffset = 0;
            //move window easly
            Klienci editedOne=tableview.getSelectionModel().getSelectedItem();
            controller.klient=editedOne;
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
            stage.initOwner(search.getScene().getWindow());
            stage.setScene(new Scene(root));
            stage.showAndWait();

            reload();
        }else
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Wystąpił błąd");
            alert.setHeaderText("Dane nie poprawne.");
            alert.setContentText("Nie wybrałeś klienta z listy.");
            alert.showAndWait();
        }
    }

    @FXML
    void moreInfo(ActionEvent event) {
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
            controller.info=tableview.getSelectionModel().getSelectedItem();
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
    void newCustomer(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(render.class.getClassLoader().getClass().getResource("/scenes/addCustomer.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        addCustomer controller = (addCustomer) loader.getController();
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

        reload();
    }

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
    void logout(ActionEvent event) throws IOException {
        render.books();
    }

    @FXML
    void menu(ActionEvent event) throws IOException {
        render.menu();
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
