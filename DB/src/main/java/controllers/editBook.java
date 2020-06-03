package controllers;

        import entity.ComboboxItem;
        import entity.Ksiazki;
        import javafx.event.ActionEvent;
        import javafx.fxml.FXML;
        import javafx.fxml.FXMLLoader;
        import javafx.scene.Parent;
        import javafx.scene.Scene;
        import javafx.scene.control.Button;
        import javafx.scene.control.ComboBox;
        import javafx.scene.control.DatePicker;
        import javafx.scene.control.TextField;
        import javafx.stage.Modality;
        import javafx.stage.Stage;
        import org.hibernate.procedure.ProcedureCall;
        import org.hibernate.result.Output;
        import org.hibernate.result.ResultSetOutput;

        import javax.persistence.ParameterMode;
        import java.io.IOException;
        import java.math.BigDecimal;
        import java.time.ZoneId;
        import java.util.List;

public class editBook {
    public Ksiazki edited=null;
    @FXML
    private TextField name;

    @FXML
    private TextField amount;

    @FXML
    private DatePicker data;

    @FXML
    private Button cancel;

    @FXML
    private ComboBox<ComboboxItem> kategoria;

    @FXML
    private ComboBox<ComboboxItem> wydawnictwo;

    @FXML
    private ComboBox<ComboboxItem> autorzyLista;

    @FXML
    private ComboBox<ComboboxItem> tagiLista;

    @FXML
    private ComboBox<ComboboxItem> tagiListaAktualne;

    @FXML
    private ComboBox<ComboboxItem> autorzyListaAktualne;

    @FXML
    void addAutor(ActionEvent event) {
        autorzyListaAktualne.getItems().add(autorzyLista.getSelectionModel().getSelectedItem());
        autorzyListaAktualne.getSelectionModel().select(autorzyLista.getSelectionModel().getSelectedItem());
        autorzyLista.getItems().remove(autorzyLista.getSelectionModel().getSelectedItem());
        autorzyLista.getSelectionModel().clearSelection();
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
    void addTag(ActionEvent event) {
        tagiListaAktualne.getItems().add(tagiLista.getSelectionModel().getSelectedItem());
        tagiListaAktualne.getSelectionModel().select(tagiLista.getSelectionModel().getSelectedItem());
        tagiLista.getItems().remove(tagiLista.getSelectionModel().getSelectedItem());
        tagiLista.getSelectionModel().clearSelection();
    }

    @FXML
    void edit(ActionEvent event) {
        //update in database

        edited=null;
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
    void removeAutor(ActionEvent event) {
        ComboboxItem xd = autorzyLista.getItems().get(autorzyLista.getItems().size() - 1);
        autorzyLista.getItems().remove(xd);
        autorzyLista.getItems().add(autorzyListaAktualne.getSelectionModel().getSelectedItem());
        autorzyLista.getItems().add(xd);

        autorzyListaAktualne.getItems().remove(autorzyListaAktualne.getSelectionModel().getSelectedItem());
    }

    @FXML
    void removeTag(ActionEvent event) {
        ComboboxItem xd = tagiLista.getItems().get(tagiLista.getItems().size() - 1);
        tagiLista.getItems().remove(xd);
        tagiLista.getItems().add(tagiListaAktualne.getSelectionModel().getSelectedItem());
        tagiLista.getItems().add(xd);

        tagiListaAktualne.getItems().remove(tagiListaAktualne.getSelectionModel().getSelectedItem());
    }

    @FXML
    void cancel(ActionEvent event) {
        ((Stage) cancel.getScene().getWindow()).close();
    }

    void renderTags() {
        tagiLista.getItems().clear();

        for(int i=0;i<edited.getTags().size();i++)
        {
            ComboboxItem item = new ComboboxItem();
            item.Text = edited.getTags().get(i).getNazwa();
            item.Value = edited.getTags().get(i).getId_tagu();
            tagiListaAktualne.getItems().add(item);
        }

        ProcedureCall call = db.session.createStoredProcedureCall("GETTAGI");
        call.registerParameter(1, Class.class, ParameterMode.REF_CURSOR);
        Output output = call.getOutputs().getCurrent();

        if (output.isResultSet()) {
            List<Object[]> resultData = ((ResultSetOutput) output).getResultList();
            if (!resultData.isEmpty()) {
                for (int i = 0; i < resultData.size(); i++) {

                    ComboboxItem item = new ComboboxItem();
                    item.Text = (String) resultData.get(i)[1];
                    item.Value = ((BigDecimal) resultData.get(i)[0]).intValue();

                    if(!tagiListaAktualne.getItems().contains(item))
                        tagiLista.getItems().add(item);
                }
            }
        }
    }

    void renderAuthors() {
        autorzyLista.getItems().clear();

        for(int i=0;i<edited.getAutorzy().size();i++)
        {
            ComboboxItem item = new ComboboxItem();
            item.Text = edited.getAutorzy().get(i).getImie() + " " + edited.getAutorzy().get(i).getNazwisko();
            item.Value = edited.getAutorzy().get(i).getId_autora();
            autorzyListaAktualne.getItems().add(item);
        }

        ProcedureCall call = db.session.createStoredProcedureCall("GETAUTORZY");
        call.registerParameter(1, Class.class, ParameterMode.REF_CURSOR);
        Output output = call.getOutputs().getCurrent();

        if (output.isResultSet()) {
            List<Object[]> resultData = ((ResultSetOutput) output).getResultList();
            if (!resultData.isEmpty()) {
                for (int i = 0; i < resultData.size(); i++) {

                    ComboboxItem item = new ComboboxItem();
                    item.Text = (String) (resultData.get(i)[1] + " " + resultData.get(i)[2]);
                    item.Value = ((BigDecimal) resultData.get(i)[0]).intValue();

                    if(!autorzyListaAktualne.getItems().contains(item))
                        autorzyLista.getItems().add(item);
                }
            }
        }
    }

    void renderCategory() {
        kategoria.getItems().clear();

        ProcedureCall call = db.session.createStoredProcedureCall("GETKATEGORIE");
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
        ComboboxItem item = new ComboboxItem();
        item.Text = edited.getKategoria().getNazwa();
        item.Value = edited.getKategoria().getId_kategorii();

        kategoria.getSelectionModel().select(item);
    }

    void renderPublish() {
        wydawnictwo.getItems().clear();
        ProcedureCall call = db.session.createStoredProcedureCall("GETWYDAWNICTWA");
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
        ComboboxItem item = new ComboboxItem();
        item.Text = edited.getWydawnictwo().getNazwa();
        item.Value = edited.getWydawnictwo().getId_wydawnictwa();

        wydawnictwo.getSelectionModel().select(item);

    }
    void reload(){
        System.out.println("edytowanie: "+edited.toString());
        name.setText(edited.getTytul());
        amount.setText(String.valueOf(edited.getIlosc()));
        data.setValue(((edited.getData_wydania()).toInstant()).atZone(ZoneId.systemDefault()).toLocalDate());

        renderTags();
        renderAuthors();
        renderCategory();
        renderPublish();
    }

    @FXML
    void initialize() {
    }

}
