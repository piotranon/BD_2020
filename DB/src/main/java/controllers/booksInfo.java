package controllers;

import java.math.BigDecimal;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import entity.Adres;
import entity.Ksiazki;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.hibernate.procedure.ProcedureCall;
import org.hibernate.result.Output;
import org.hibernate.result.ResultSetOutput;

import javax.persistence.ParameterMode;

public class booksInfo {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button close;

    @FXML
    private Label title;

    @FXML
    private Label publish;

    @FXML
    private Label category;

    @FXML
    private Label tags;

    @FXML
    private Label dataWydania;

    @FXML
    private Label autorzy;

    @FXML
    private Label amount;
    @FXML
    private Label publish1;
    public Ksiazki book=new Ksiazki();
    @FXML
    void close(ActionEvent event) {
        ((Stage) close.getScene().getWindow()).close();
    }

    void render(){
        title.setText(book.getTytul());
        category.setText(book.getKategoria().getNazwa());
        publish.setText(book.getWydawnictwo().getNazwa());
        Adres a;
        a=book.getWydawnictwo().getAdres();

//        try {
            publish1.setText(a.getKod_Pocztowy()+" "+a.getMiejscowosc()+" ul."+a.getUlica()+" "+a.getNumer_Budynku());
//        }catch (Exception e)
//        {
//            System.out.println("ex "+e.getMessage()+"\n"+e.getStackTrace());
//        }

        dataWydania.setText(book.getData_wydania().toString());
        tags.setText(book.getTagsHash());
        amount.setText(String.valueOf(book.getIlosc()));
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<book.getAutorzy().size();i++)
            sb.append(book.getAutorzy().get(i).getImie()+" "+book.getAutorzy().get(i).getNazwisko()+", ");
        sb.deleteCharAt(sb.length()-2);
        autorzy.setText(sb.toString());
    }

    @FXML
    void initialize() {
        assert close != null : "fx:id=\"close\" was not injected: check your FXML file 'booksInfo.fxml'.";
        assert title != null : "fx:id=\"title\" was not injected: check your FXML file 'booksInfo.fxml'.";
        assert publish != null : "fx:id=\"publish\" was not injected: check your FXML file 'booksInfo.fxml'.";
        assert category != null : "fx:id=\"category\" was not injected: check your FXML file 'booksInfo.fxml'.";
        assert tags != null : "fx:id=\"tags\" was not injected: check your FXML file 'booksInfo.fxml'.";
        assert dataWydania != null : "fx:id=\"dataWydania\" was not injected: check your FXML file 'booksInfo.fxml'.";
        assert autorzy != null : "fx:id=\"autorzy\" was not injected: check your FXML file 'booksInfo.fxml'.";

    }
}
