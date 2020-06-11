package controllers;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.time.ZoneId;
import java.util.*;

import entity.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.procedure.ProcedureCall;
import org.hibernate.result.Output;
import org.hibernate.result.ResultSetOutput;

import javax.persistence.ParameterMode;

public class addBook {
    public Ksiazki nowa = new Ksiazki();
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="name"
    private TextField name; // Value injected by FXMLLoader

    @FXML // fx:id="amount"
    private TextField amount; // Value injected by FXMLLoader

    @FXML // fx:id="data"
    private DatePicker data; // Value injected by FXMLLoader

    @FXML // fx:id="kategoria"
    private ComboBox<ComboboxItem> kategoria; // Value injected by FXMLLoader

    @FXML // fx:id="wydawnictwo"
    private ComboBox<ComboboxItem> wydawnictwo; // Value injected by FXMLLoader

    @FXML // fx:id="autorzy"
    private Label autorzy; // Value injected by FXMLLoader

    @FXML // fx:id="autorzyLista"
    private ComboBox<ComboboxItem> autorzyLista; // Value injected by FXMLLoader

    @FXML // fx:id="tagi"
    private Label tagi; // Value injected by FXMLLoader
    private List<Integer> tags;

    @FXML // fx:id="tagiLista"
    private ComboBox<ComboboxItem> tagiLista; // Value injected by FXMLLoader
    @FXML
    private ComboBox<ComboboxItem> tagiListaAktualne;

    @FXML
    private ComboBox<ComboboxItem> autorzyListaAktualne;

    @FXML
    private Button cancel;
    private SessionFactory sessionFactory;
    private Session session;
    @FXML
    void Add(ActionEvent event) {
        boolean validData = true;
        Wydawnictwa wydaw = new Wydawnictwa();
        Kategorie kat = new Kategorie();
        if (name.getText().length() < 1) {
            System.out.println("1");
            validData = false;
        }
        if (amount.getText().length() < 1) {
            System.out.println("2");
            validData = false;
        }
        if (autorzyListaAktualne.getItems().size() < 1) {
            System.out.println("3");
            validData = false;
        }
        if (tagiListaAktualne.getItems().size() < 1) {
            System.out.println("4");
            validData = false;
        }
        if (data.getValue()==null) {
            System.out.println("5");
            validData = false;
        }
        if (wydawnictwo.getSelectionModel().getSelectedItem()==null) {
            System.out.println("6");
            validData = false;
        }
        if (kategoria.getSelectionModel().getSelectedItem()==null) {
            System.out.println("7");
            validData = false;
        }

        if (validData) {

            wydaw.setId_wydawnictwa(wydawnictwo.getSelectionModel().getSelectedItem().Value);
            wydaw.setNazwa(wydawnictwo.getSelectionModel().getSelectedItem().Text);
//            wydaw=session.get(Wydawnictwa.class, wydawnictwo.getSelectionModel().getSelectedItem().Value);

            if(!db.session.getTransaction().isActive())
                db.session.beginTransaction();

            ProcedureCall call = db.session.createStoredProcedureCall("Pobranie.Wydawnictwo");
            call.registerParameter(1, Integer.class, ParameterMode.IN).bindValue(wydaw.getId_wydawnictwa());
            call.registerParameter(2, Class.class, ParameterMode.REF_CURSOR);

            Output output = call.getOutputs().getCurrent();

            if (output.isResultSet()) {
                List<Object[]> resultData = ((ResultSetOutput) output).getResultList();
                if (!resultData.isEmpty()) {
                    ProcedureCall call1 = db.session.createStoredProcedureCall("Pobranie.Adres");
                    call1.registerParameter(1, Integer.class, ParameterMode.IN).bindValue(((BigDecimal) resultData.get(0)[2]).intValue());
                    call1.registerParameter(2, Class.class, ParameterMode.REF_CURSOR);

                    Output output1 = call1.getOutputs().getCurrent();
                    Adres adres=new Adres();
                    if (output1.isResultSet()) {
                        List<Object[]> resultData1 = ((ResultSetOutput) output1).getResultList();
                        if (!resultData1.isEmpty()) {
                            adres.setId_adresu(((BigDecimal) resultData1.get(0)[0]).intValue());
                            adres.setMiejscowosc((String) resultData1.get(0)[1]);
                            adres.setKod_Pocztowy((String) resultData1.get(0)[2]);
                            adres.setUlica((String) resultData1.get(0)[3]);
                            adres.setNumer_Budynku(((BigDecimal) resultData1.get(0)[4]).intValue());
                        }
                    }
                    wydaw.setAdres(adres);
                }
            }

            System.out.println("idw:"+wydaw.toString());

            kat.setId_kategorii(kategoria.getSelectionModel().getSelectedItem().Value);
            kat.setNazwa(kategoria.getSelectionModel().getSelectedItem().Text);
            List<Autorzy> autorzy = new ArrayList<>();
            for (int i = 0; i < autorzyListaAktualne.getItems().size(); i++) {
                Autorzy a = new Autorzy();
                a.setId_autora(autorzyListaAktualne.getItems().get(i).Value);
                autorzy.add(a);
            }

            List<Tag> tagi = new ArrayList<>();
            for (int i = 0; i < tagiListaAktualne.getItems().size(); i++) {
                Tag tag = new Tag();
                tag.setId_tagu(tagiListaAktualne.getItems().get(i).Value);
                tag.setNazwa(tagiListaAktualne.getItems().get(i).Text);
                tagi.add(tag);
            }

            nowa.setTytul(name.getText());
            nowa.setIlosc(Integer.parseInt(amount.getText()));
            java.util.Date date =
                    java.util.Date.from(data.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());
            nowa.setData_wydania(sqlDate);
            nowa.setWydawnictwo(wydaw);
            nowa.setKategoria(kat);
            nowa.setAutorzy(autorzy);
            nowa.setTags(tagi);

//            db.sessionStart();


//            db.session.getTransaction().commit();

            if(!db.session.getTransaction().isActive())
                db.session.beginTransaction();
            db.session.save(nowa);
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
    void addCategory(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(render.class.getClassLoader().getClass().getResource("/scenes/addCategory.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        addCategory controller = (addCategory) loader.getController();
        Stage stage = new Stage();
        stage.setTitle("BD 2020 Długosz Piotr");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(cancel.getScene().getWindow());
        stage.setScene(new Scene(root));
        stage.showAndWait();
        renderCategory();
    }

    @FXML
    void addPublish(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(render.class.getClassLoader().getClass().getResource("/scenes/addPublish.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        addPublish controller = (addPublish) loader.getController();
        Stage stage = new Stage();
        stage.setTitle("BD 2020 Długosz Piotr");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(cancel.getScene().getWindow());
        stage.setScene(new Scene(root));
        stage.showAndWait();
        renderPublish();
    }

    @FXML
    void newAuthor(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(render.class.getClassLoader().getClass().getResource("/scenes/addAuthor.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        addAuthor controller = (addAuthor) loader.getController();
        Stage stage = new Stage();
        stage.setTitle("BD 2020 Długosz Piotr");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(cancel.getScene().getWindow());
        stage.setScene(new Scene(root));
        stage.showAndWait();
        renderAuthors();
    }

    @FXML
    void newTag(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(render.class.getClassLoader().getClass().getResource("/scenes/addTag.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        addTag controller = (addTag) loader.getController();
        Stage stage = new Stage();
        stage.setTitle("BD 2020 Długosz Piotr");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(cancel.getScene().getWindow());
        stage.setScene(new Scene(root));
        stage.showAndWait();
        renderTags();
    }

    @FXML
    void addAutor(ActionEvent event) {
        autorzyListaAktualne.getItems().add(autorzyLista.getSelectionModel().getSelectedItem());
        autorzyListaAktualne.getSelectionModel().select(autorzyLista.getSelectionModel().getSelectedItem());
        autorzyLista.getItems().remove(autorzyLista.getSelectionModel().getSelectedItem());
        autorzyLista.getSelectionModel().clearSelection();
    }

    @FXML
    void addTag(ActionEvent event) {
        tagiListaAktualne.getItems().add(tagiLista.getSelectionModel().getSelectedItem());
        tagiListaAktualne.getSelectionModel().select(tagiLista.getSelectionModel().getSelectedItem());
        tagiLista.getItems().remove(tagiLista.getSelectionModel().getSelectedItem());
        tagiLista.getSelectionModel().clearSelection();
    }


    @FXML
    void removeAutor(ActionEvent event) {
        ComboboxItem d=autorzyListaAktualne.getSelectionModel().getSelectedItem();
        autorzyLista.getItems().add(d);
        autorzyListaAktualne.getItems().remove(d);
    }

    @FXML
    void removeTag(ActionEvent event) {
        ComboboxItem d=tagiListaAktualne.getSelectionModel().getSelectedItem();
        tagiLista.getItems().add(d);
        tagiListaAktualne.getItems().remove(d);
    }

    @FXML
    void cancel(ActionEvent event) {
        ((Stage) cancel.getScene().getWindow()).close();
    }

    void renderTags() {
        tagiLista.getItems().clear();
        ProcedureCall call = db.session.createStoredProcedureCall("Pobranie.Tagi");
        call.registerParameter(1, Class.class, ParameterMode.REF_CURSOR);
        Output output = call.getOutputs().getCurrent();
        if (output.isResultSet()) {
            List<Object[]> resultData = ((ResultSetOutput) output).getResultList();
            if (!resultData.isEmpty()) {
                for (int i = 0; i < resultData.size(); i++) {
                    ComboboxItem item = new ComboboxItem();
                    item.Text = (String) resultData.get(i)[1];
                    item.Value = ((BigDecimal) resultData.get(i)[0]).intValue();
                    tagiLista.getItems().add(item);
                }
            }
        }
    }

    void renderAuthors() {
        autorzyLista.getItems().clear();
        ProcedureCall call = db.session.createStoredProcedureCall("Pobranie.Autorzy");
        call.registerParameter(1, Class.class, ParameterMode.REF_CURSOR);
        Output output = call.getOutputs().getCurrent();
        if (output.isResultSet()) {
            List<Object[]> resultData = ((ResultSetOutput) output).getResultList();
            if (!resultData.isEmpty()) {
                for (int i = 0; i < resultData.size(); i++) {
                    ComboboxItem item = new ComboboxItem();
                    item.Text = (String) (resultData.get(i)[1] + " " + resultData.get(i)[2]);
                    item.Value = ((BigDecimal) resultData.get(i)[0]).intValue();
                    autorzyLista.getItems().add(item);
                }
            }
        }
    }

    void renderCategory() {
        kategoria.getItems().clear();
        ProcedureCall call = db.session.createStoredProcedureCall("Pobranie.Kategorie");
        call.registerParameter(1, Class.class, ParameterMode.REF_CURSOR);
        Output output = call.getOutputs().getCurrent();
        if (output.isResultSet()) {
            List<Object[]> resultData = ((ResultSetOutput) output).getResultList();
            if (!resultData.isEmpty()) {
                for (int i = 0; i < resultData.size(); i++) {
                    ComboboxItem item = new ComboboxItem();
                    item.Text = (String) (resultData.get(i)[1]);
                    item.Value = ((BigDecimal) resultData.get(i)[0]).intValue();
                    kategoria.getItems().add(item);
                }
            }
        }
    }

    void renderPublish() {
        wydawnictwo.getItems().clear();
        ProcedureCall call = db.session.createStoredProcedureCall("Pobranie.Wydawnictwa");
        call.registerParameter(1, Class.class, ParameterMode.REF_CURSOR);
        Output output = call.getOutputs().getCurrent();
        if (output.isResultSet()) {
            List<Object[]> resultData = ((ResultSetOutput) output).getResultList();
            if (!resultData.isEmpty()) {
                for (int i = 0; i < resultData.size(); i++) {
                    ComboboxItem item = new ComboboxItem();
                    item.Text = (String) (resultData.get(i)[1]);
                    item.Value = ((BigDecimal) resultData.get(i)[0]).intValue();
                    wydawnictwo.getItems().add(item);
                }
            }
        }
    }

    @FXML
        // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
//        assert name != null : "fx:id=\"name\" was not injected: check your FXML file 'addBook.fxml'.";
//        assert amount != null : "fx:id=\"amount\" was not injected: check your FXML file 'addBook.fxml'.";
//        assert data != null : "fx:id=\"data\" was not injected: check your FXML file 'addBook.fxml'.";
//        assert kategoria != null : "fx:id=\"kategoria\" was not injected: check your FXML file 'addBook.fxml'.";
//        assert wydawnictwo != null : "fx:id=\"wydawnictwo\" was not injected: check your FXML file 'addBook.fxml'.";
//        assert autorzy != null : "fx:id=\"autorzy\" was not injected: check your FXML file 'addBook.fxml'.";
//        assert autorzyLista != null : "fx:id=\"autorzyLista\" was not injected: check your FXML file 'addBook.fxml'.";
//        assert tagi != null : "fx:id=\"tagi\" was not injected: check your FXML file 'addBook.fxml'.";
//        assert tagiLista != null : "fx:id=\"tagiLista\" was not injected: check your FXML file 'addBook.fxml'.";

        renderTags();
        renderAuthors();
        renderCategory();
        renderPublish();
    }
}
