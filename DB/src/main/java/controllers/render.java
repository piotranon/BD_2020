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
    public static void booksLogged() throws IOException {
        FXMLLoader loader = new FXMLLoader(render.class.getClassLoader().getClass().getResource("/scenes/books.fxml"));
        Parent root = null;
        root = loader.load();
        books controller = (books) loader.getController();
        controller.reload();
        stage.setTitle("BD 2020 Długosz Piotr");
        stage.setScene(new Scene(root));
        stage.show();
    }
    public static void books() throws IOException {
        FXMLLoader loader = new FXMLLoader(render.class.getClassLoader().getClass().getResource("/scenes/booksUser.fxml"));
        Parent root = null;
        root = loader.load();
        booksUser controller = (booksUser) loader.getController();
        controller.reload();
        stage.setTitle("BD 2020 Długosz Piotr");
        stage.setScene(new Scene(root));
        stage.show();
    }
}
