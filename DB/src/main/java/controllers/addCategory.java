package controllers;

import entity.Ksiazki;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.hibernate.procedure.ProcedureCall;

import javax.persistence.ParameterMode;

public class addCategory {

        @FXML
        private Button close;

        @FXML
        private TextField category;

        @FXML
        void add(ActionEvent event) {
            boolean validData = true;

            if(category.getText().isEmpty())
                validData = false;
            if(Character.getType(category.getText().charAt(0))!=Character.UPPERCASE_LETTER)
                validData = false;
            if(!validData)
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Błąd");
                alert.setHeaderText("Błąd nazwy");
                alert.setContentText("Nazwa musi zaczynać się z dużej litery.");
                alert.showAndWait();

                return;
            }

            if(!db.session.getTransaction().isActive())
                db.session.beginTransaction();

            ProcedureCall call = db.session.createStoredProcedureCall("ADDKATEGORIA");
            call.registerParameter(1, String.class, ParameterMode.IN);
            call.setParameter(1,category.getText());
            boolean er=false;
            try {
                call.executeUpdate();
            }catch (Exception e)
            {
                er=true;
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Błąd");
                alert.setHeaderText("Błąd podczas dodawania");
                alert.setContentText(e.getMessage()+"\n"+e.getStackTrace());
                alert.showAndWait();
                close(event);
            }

            db.session.getTransaction().commit();


            if(!er)
                close(event);
        }

        @FXML
        void close(ActionEvent event) {
            ((Stage)close.getScene().getWindow()).close();
        }
    }
