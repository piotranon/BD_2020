package org.example;

import entity.Adres;
import entity.Autorzy;
import javafx.application.Application;
import static javafx.application.Application.launch;

import javafx.stage.Stage;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class MainApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        Adres adres= new Adres();
        adres.setMiejscowosc("Rzeszów");
        adres.setUlica("Dębicka2");
        adres.setNumer_Budynku(2);

        Autorzy autor =new Autorzy();

        autor.setImie("Piotr");
        autor.setNazwisko("Długiosz");

        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(adres);
        session.getTransaction().commit();

//        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Scene.fxml"));
//
//        Scene scene = new Scene(root);
//        scene.getStylesheets().add("/styles/Styles.css");
//
//        stage.setTitle("JavaFX and Maven");
//        stage.setScene(scene);
//        stage.show();
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
