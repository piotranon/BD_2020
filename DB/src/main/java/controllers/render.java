package controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.Method;

public class render {
    public static Stage stage;

    public static void login() throws IOException {
        System.out.println(MethodHandles.lookup().lookupClass().getResource("/scenes/login.fxml"));
        FXMLLoader loader = new FXMLLoader(MethodHandles.lookup().lookupClass().getResource("/scenes/login.fxml"));
        Parent root = loader.load();
        login controller = (login) loader.getController();
        stage.setTitle("BD 2020 Długosz Piotr");
        stage.setScene(new Scene(root));
        stage.show();
    }
    public static void menu(){
        FXMLLoader loader = new FXMLLoader(render.class.getClassLoader().getClass().getResource("/scenes/menu.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        menu controller = (menu) loader.getController();
        stage.setTitle("BD 2020 Długosz Piotr");
        stage.setScene(new Scene(root));
        stage.show();
    }

    public static void books() {
        FXMLLoader loader = new FXMLLoader(render.class.getClassLoader().getClass().getResource("/scenes/books.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        books controller = (books) loader.getController();
        stage.setTitle("BD 2020 Długosz Piotr");
        stage.setScene(new Scene(root));
        stage.show();
    }
}
