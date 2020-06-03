package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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

        call.execute();
    }

    @FXML
    void close(ActionEvent event) {
        ((Stage)close.getScene().getWindow()).close();
    }

}
