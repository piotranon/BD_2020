package controllers;

        import java.io.IOException;
        import java.net.URL;
        import java.util.ArrayList;
        import java.util.List;
        import java.util.ResourceBundle;
        import java.util.function.Predicate;

        import entity.*;
        import javafx.beans.property.SimpleStringProperty;
        import javafx.beans.value.ObservableValue;
        import javafx.collections.FXCollections;
        import javafx.collections.ObservableList;
        import javafx.collections.transformation.FilteredList;
        import javafx.collections.transformation.SortedList;
        import javafx.event.ActionEvent;
        import javafx.fxml.FXML;
        import javafx.fxml.FXMLLoader;
        import javafx.scene.Parent;
        import javafx.scene.Scene;
        import javafx.scene.control.TableColumn;
        import javafx.scene.control.TableView;
        import javafx.scene.control.TextField;
        import javafx.scene.control.cell.PropertyValueFactory;
        import javafx.scene.input.KeyEvent;
        import javafx.stage.Stage;
        import javafx.util.Callback;

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

    private List<Ksiazki> localBooksList=new ArrayList<>();
    @FXML
    void bookDetails(ActionEvent event) {

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
        stage.setScene(new Scene(root));
        stage.show();
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
        render.login();
    }

    @FXML
    void menu(ActionEvent event) {
        render.menu();
    }

    @FXML
    void reloadDataToView(ActionEvent event) {
        reload();
    }

    void sortedList(List<Ksiazki> booksList)
    {
        ObservableList<Ksiazki> booksxml = (ObservableList<Ksiazki>) FXCollections.observableList(booksList);
        FilteredList<Ksiazki> filteredList=new FilteredList<Ksiazki>(booksxml);

        Predicate predicate=new Predicate() {
            public boolean test(Object o) {

                Ksiazki c =new Ksiazki((Ksiazki) o);

                if(c.getTytul().toLowerCase().contains(search.getText().toLowerCase()))
                    return true;
                else if(c.getWydawnictwoName().toLowerCase().contains(search.getText().toLowerCase()))
                    return true;
                else if(c.getKategoriaName().toLowerCase().contains(search.getText().toLowerCase()))
                    return true;
                else if(String.valueOf(c.getPopularnosc()).toLowerCase().contains(search.getText().toLowerCase()))
                    return true;
                else if(c.getAutorzyNames().toLowerCase().contains(search.getText().toLowerCase()))
                    return true;
                else if(c.getTagsWithHash().toLowerCase().contains(search.getText().toLowerCase()))
                    return true;
                return false;
            }
        };

        filteredList.setPredicate(predicate);

        SortedList<Ksiazki> sortedData = new SortedList<Ksiazki>(filteredList);
        sortedData.comparatorProperty().bind(tableview.comparatorProperty());

        tableview.setItems(sortedData);
    }

    public void reload(){
        localBooksList=dbSession.loadAllData(Ksiazki.class);
        search.setText("");
        sortedList(localBooksList);


//        ProcedureCall call = dbSession.session.createStoredProcedureCall("GETALLKSIAZKI2");
//        call.registerParameter(1, Class.class,ParameterMode.REF_CURSOR);
//
//        Output output = call.getOutputs().getCurrent();
//
//        if (output.isResultSet()) {
//            List<Object[]> resultData = ((ResultSetOutput) output).getResultList();
//
//            if (!resultData.isEmpty()) {
//                for(int i=0;i<resultData.size();i++)
//                {
////                    System.out.println(resultData.get(i).toString());
////                    Ksiazki ksiazka = (Ksiazki) resultData.get(i);
//                    Ksiazki ksiazka =new Ksiazki();
//                    ksiazka.setId_ksiazki(((BigDecimal)resultData.get(i)[0]).intValue());
//                    ksiazka.setTytul((String)resultData.get(i)[1]);
//                    ksiazka.setData_wydania(new Date(((Timestamp)resultData.get(i)[2]).getTime()));
//                    ksiazka.setIlosc(((BigDecimal)resultData.get(i)[3]).intValue());
//
//                    ProcedureCall call2 = dbSession.session.createStoredProcedureCall("GETAUTORZY");
//                    call2.registerParameter(1,Integer.class,ParameterMode.IN).bindValue(ksiazka.getId_ksiazki());
//                    call2.registerParameter(2,Class.class,ParameterMode.REF_CURSOR);
//                    Output output2 = call2.getOutputs().getCurrent();
//                    if(output2.isResultSet())
//                    {
//
//                    }
//
//
//                    System.out.println(ksiazka.toString());
//
//
//
////                    ksiazka.setAutorzy();
////                    ksiazka.setWydawnictwo();
//
//                    System.out.println(resultData.get(i)[0]);//id ksiazki
//                    System.out.println(resultData.get(i)[1]);//tytul
//                    System.out.println(resultData.get(i)[2]);//data wydania
//                    System.out.println(resultData.get(i)[3]);//ilosc
//                    System.out.println(resultData.get(i)[4]);//id wydawnictwa
//                    System.out.println(resultData.get(i)[5]);//id kategorii
//                }
//                System.out.println("=====================================");
//            }
//        }
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        name.setCellValueFactory(new PropertyValueFactory("tytul"));
        amount.setCellValueFactory(new PropertyValueFactory("ilosc"));
        category.setCellValueFactory(new PropertyValueFactory("KategoriaName"));
        wydawnictwo.setCellValueFactory(new PropertyValueFactory("WydawnictwoName"));
        autorzy.setCellValueFactory(new PropertyValueFactory("AutorzyNames"));
        tags.setCellValueFactory(new PropertyValueFactory("TagsWithHash"));
        popularnosc.setCellValueFactory(new PropertyValueFactory("Popularnosc"));

        reload();
    }
}
