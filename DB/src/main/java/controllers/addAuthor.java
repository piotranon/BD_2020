package controllers;

import entity.ComboboxItem;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.hibernate.procedure.ProcedureCall;
import org.hibernate.result.Output;
import org.hibernate.result.ResultSetOutput;

import javax.persistence.ParameterMode;
import java.math.BigDecimal;
import java.util.List;

public class addAuthor {

    @FXML
    private TextField name;

    @FXML
    private TextField surname;

    @FXML
    private Button cancel;

    @FXML
    void add(ActionEvent event) {
        boolean validData = true;
        if(name.getText().isEmpty())
            validData=false;
        if(surname.getText().isEmpty())
            validData=false;
        if(Character.getType(name.getText().charAt(0))!=Character.UPPERCASE_LETTER)
            validData = false;
        if(Character.getType(surname.getText().charAt(0))!=Character.UPPERCASE_LETTER)
            validData = false;
        if(validData)
        {
            ProcedureCall call = db.session.createStoredProcedureCall("ADDAUTOR");
            call.registerParameter(1, String.class, ParameterMode.IN);
            call.setParameter(1,name.getText());
            call.registerParameter(2,String.class,ParameterMode.IN);
            call.setParameter(2,surname.getText());

            call.execute();

            cancel(event);
            //save to db
        }else
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Wystąpił Błąd");
            alert.setHeaderText("Nie wszystkie podane dane są poprawne.");
            alert.setContentText("Imię i nazwisko autora musi się zaczynać z dużej litery");
            alert.showAndWait();
        }
    }

    @FXML
    void cancel(ActionEvent event) {
        ((Stage)cancel.getScene().getWindow()).close();
    }

}
