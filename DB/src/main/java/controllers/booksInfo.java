package controllers;

import java.math.BigDecimal;
import java.net.URL;
import java.sql.CallableStatement;
import java.sql.Types;
import java.util.List;
import java.util.Queue;
import java.util.ResourceBundle;

import entity.Adres;
import entity.Ksiazki;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.hibernate.procedure.ProcedureCall;
import org.hibernate.query.Query;
import org.hibernate.result.Output;
import org.hibernate.result.ResultSetOutput;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

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
    private LineChart<?, ?> wykres;

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

//        int[] popularnosc=new int[12];

//        StoredProcedureQuery storedProcedureQuery=db.session.createNamedStoredProcedureQuery("wykresPopularnosci");
//        storedProcedureQuery.registerStoredProcedureParameter(1,int[].class,ParameterMode.OUT);
//        storedProcedureQuery.registerStoredProcedureParameter(2,int.class,ParameterMode.IN);
//        storedProcedureQuery.setParameter(2,book.getId_ksiazki());
//
//        storedProcedureQuery.execute();

//        ProcedureCall call1 = db.session.createStoredProcedureCall("GETWYPOZYCZENIADLAKSIAZKI");
//        call1.registerParameter(1, Integer.class, ParameterMode.IN).bindValue(book.getId_ksiazki());
//        call1.registerParameter(2, Integer[].class, ParameterMode.OUT);
//
//        Output output1 = call1.getOutputs().getCurrent();
//        if (output1.isResultSet()) {
//            List<Object[]> resultData1 = ((ResultSetOutput) output1).getResultList();
//            if (!resultData1.isEmpty()) {
//                for(int i=0;i<12;i++)
//                    System.out.println(i+". "+resultData1.get(0)[i]);
//            }
//        }
//
//        StoredProcedureQuery procedureQuery = db.session
//                .createStoredProcedureQuery("wykresPopularnosci");
//        procedureQuery.registerStoredProcedureParameter("id_k", Integer.class, ParameterMode.IN);
//        procedureQuery.setParameter("id_k", book.getId_ksiazki());
//        procedureQuery.execute();
//        Object singleResult = procedureQuery.getSingleResult();
//        System.out.println("sum: " + singleResult);
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
