package controllers;

/**
 * Sample Skeleton for 'login.fxml' Controller Class
 */

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class login {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="login"
    private TextField login; // Value injected by FXMLLoader

    @FXML // fx:id="hasło"
    private TextField hasło; // Value injected by FXMLLoader

    @FXML // fx:id="error"
    private Label error; // Value injected by FXMLLoader

    @FXML
    void clearTextFields(ActionEvent event) {
        login.setText("");
        hasło.setText("");
    }

    @FXML
    void loginToSystem(ActionEvent event) {

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert login != null : "fx:id=\"login\" was not injected: check your FXML file 'login.fxml'.";
        assert hasło != null : "fx:id=\"hasło\" was not injected: check your FXML file 'login.fxml'.";
        assert error != null : "fx:id=\"error\" was not injected: check your FXML file 'login.fxml'.";

    }

}
