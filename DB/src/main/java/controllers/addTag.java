package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.hibernate.procedure.ProcedureCall;

import javax.persistence.ParameterMode;

public class addTag {

    @FXML
    private Button close;

    @FXML
    private TextField tag;

    @FXML
    void add(ActionEvent event) {
        boolean validData = true;

        if(tag.getText().isEmpty())
            validData = false;

        if(!validData)
            return;

        ProcedureCall call = db.session.createStoredProcedureCall("ADDTAG");
        call.registerParameter(1, String.class, ParameterMode.IN);
        call.setParameter(1,tag.getText());

        boolean er=false;
        try {
            call.executeUpdate();
        }catch (Exception e)
        {
            er=true;
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Błąd");
            alert.setHeaderText("Błąd podczas dodawania");
            alert.setContentText("Wystąpił błąd podczas dodawania.");
            alert.showAndWait();
            close(event);
        }
        if(!er)
            close(event);
    }

    @FXML
    void close(ActionEvent event) {
        ((Stage)close.getScene().getWindow()).close();
    }

}
