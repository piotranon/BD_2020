package controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class stageRender {
    public static Stage stage;

    public static void renderlogin()
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/scenes/login.fxml"));
        Parent root = loader.load();
        login controller = (login) loader.getController();
        controller.stage=stage;
        stage.setTitle("BD 2020 Długosz Piotr");
        stage.setScene(new Scene(root));
        stage.show();
    }
}
