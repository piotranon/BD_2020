package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
            if(category.getText().length()<1)
                return;

            ProcedureCall call = db.session.createStoredProcedureCall("ADDKATEGORIA");
            call.registerParameter(1, String.class, ParameterMode.IN);
            call.setParameter(1,category.getText());
            call.execute();
        }

        @FXML
        void close(ActionEvent event) {
            ((Stage)close.getScene().getWindow()).close();
        }

    }
