package controllers;

        import java.math.BigDecimal;
        import java.net.URL;
        import java.sql.Date;
        import java.sql.Timestamp;
        import java.util.ArrayList;
        import java.util.List;
        import java.util.Optional;
        import java.util.ResourceBundle;

        import entity.*;
        import javafx.collections.FXCollections;
        import javafx.collections.ObservableList;
        import javafx.event.ActionEvent;
        import javafx.fxml.FXML;
        import javafx.scene.control.*;
        import org.hibernate.procedure.ProcedureCall;
        import org.hibernate.result.Output;
        import org.hibernate.result.ResultSetOutput;

        import javax.persistence.ParameterMode;

public class addBook {

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
    private ComboBox<?> autorzyLista; // Value injected by FXMLLoader

    @FXML // fx:id="tagi"
    private Label tagi; // Value injected by FXMLLoader
    private List<Integer> tags;
    @FXML // fx:id="tagiLista"
    private ComboBox<ComboboxItem> tagiLista; // Value injected by FXMLLoader

    @FXML
    void Add(ActionEvent event) {

    }

    @FXML
    void addAutor(ActionEvent event) {

    }

    @FXML
    void addTag(ActionEvent event) {
        if(tagiLista.getSelectionModel().getSelectedItem().Value==0)
        {
            //new tag
            TextInputDialog dialog = new TextInputDialog("Tag");

            dialog.setTitle("Dodawanie nowego Tagu");
            dialog.setHeaderText("Podaj nazwe Tagu:");
            dialog.setContentText("Nazwa:");

            Optional<String> result = dialog.showAndWait();

            result.ifPresent(name -> {
//                this.label.setText(name);
                //insert in to database new tag
            });

            renderTags();
        }
        else
        {
            tagi.setText(tagi.getText()+tagiLista.getSelectionModel().getSelectedItem().Text+", ");
            tags.add(tagiLista.getSelectionModel().getSelectedItem().Value);
            tagiLista.getItems().remove(tagiLista.getSelectionModel().getSelectedItem());
            tagiLista.getSelectionModel().clearSelection();
        }

    }

    @FXML
    void cancel(ActionEvent event) {

    }

    void renderTags()
    {
        ProcedureCall call = dbSession.session.createStoredProcedureCall("GETTAGI");
        call.registerParameter(1, Class.class,ParameterMode.REF_CURSOR);
        Output output = call.getOutputs().getCurrent();
        if (output.isResultSet()) {
            List<Object[]> resultData = ((ResultSetOutput) output).getResultList();
            if (!resultData.isEmpty()) {
                List<Tag> tagi = new ArrayList<>();

                for(int i=0;i<resultData.size();i++)
                {
                    ComboboxItem item =new ComboboxItem();
                    item.Text=(String)resultData.get(i)[1];
                    item.Value=((BigDecimal)resultData.get(i)[0]).intValue();
                    tagiLista.getItems().add(item);
                }
                ComboboxItem item =new ComboboxItem();
                item.Text="Nowy Tag";
                item.Value=0;
                tagiLista.getItems().add(item);
            }
        }
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
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

    }
}
