package controllers;

/**
 * Sample Skeleton for 'login.fxml' Controller Class
 */

import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URL;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.*;

import entity.Adres;
import entity.Ksiazki;
import entity.Pracownicy;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.hibernate.Session;
import org.hibernate.procedure.ProcedureCall;
import org.hibernate.result.Output;
import org.hibernate.result.ResultSetOutput;

import javax.persistence.*;

public class login {

    public Pracownicy zalogowany=new Pracownicy();

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="login"
    private TextField login; // Value injected by FXMLLoader

    @FXML // fx:id="hasło"
    private TextField haslo; // Value injected by FXMLLoader

    @FXML // fx:id="error"
    private Label error; // Value injected by FXMLLoader

    @FXML
    void clearTextFields(ActionEvent event) {
        login.setText("");
        haslo.setText("");
        String[] dane=login.getText().split(" ");
    }

    @FXML
    void loginToSystem(ActionEvent event) {
        error.setText("");





//        List<Ksiazki> lista = query.getResultList();
//        System.out.println("size:"+lista.size());
//        System.out.println("ksiazka: "+(Ksiazki)lista.get(0));

//
//
//

//        EntityManagerFactory entityManagerFactory = dbSession.session.getEntityManagerFactory();
//        EntityManager entityManager = entityManagerFactory.createEntityManager();
//
//        StoredProcedureQuery query = entityManager
//                .createStoredProcedureQuery("GETALLKSIAZKI")
//                .registerStoredProcedureParameter(1,Object.class,ParameterMode.REF_CURSOR);
//        query.execute();
//
//        List<Object> result = (List<Object>) query.getResultList();
//        Iterator itr = result.iterator();
//
//        while(itr.hasNext()){
//            Object[] obj = (Object[]) itr.next();
//
//            System.out.println("id_ksiazki: "+obj[0]);
//            System.out.println("tytul: "+obj[1]);
//            System.out.println("data_wydania: "+obj[2]);
//            System.out.println("ilosc: "+obj[3]);
//            System.out.println("id_wydawnictwa: "+obj[4]);
//
//
//
//            System.out.println("id_kategorii: "+obj[5]);
//            System.out.println("------------------------------------------------");
//        }

//
//
//

        ProcedureCall call = dbSession.session.createStoredProcedureCall("GETPRACOWNIK");
        call.registerParameter(1,String.class,ParameterMode.IN).bindValue(login.getText());
        call.registerParameter(2,String.class,ParameterMode.IN).bindValue(haslo.getText());
        call.registerParameter(3, Class.class,ParameterMode.REF_CURSOR);

        Output output = call.getOutputs().getCurrent();

        if (output.isResultSet()) {
            List<Object[]> resultData = ((ResultSetOutput) output).getResultList();
            if(!resultData.isEmpty())
            {
                zalogowany.setId_pracownika(((BigDecimal)resultData.get(0)[0]).intValue());
                zalogowany.setImie((String)resultData.get(0)[1]);
                zalogowany.setNazwisko((String)resultData.get(0)[2]);
                zalogowany.setPesel((String)resultData.get(0)[3]);
                zalogowany.setData_urodzenia(new Date(((Timestamp)resultData.get(0)[4]).getTime()));
                zalogowany.setLogin((String)resultData.get(0)[6]);
                zalogowany.setHaslo((String)resultData.get(0)[7]);

                Adres adres=new Adres();

                ProcedureCall call1 =dbSession.session.createStoredProcedureCall("GETADRES");
                call1.registerParameter(1,Integer.class,ParameterMode.IN).bindValue(((BigDecimal)resultData.get(0)[5]).intValue());
                call1.registerParameter(2, Class.class,ParameterMode.REF_CURSOR);

                Output output1 = call1.getOutputs().getCurrent();

                if(output1.isResultSet()){
                    List<Object[]> resultData1 = ((ResultSetOutput) output1).getResultList();
                    if(!resultData.isEmpty())
                    {
                        for(int i=0;i<resultData1.get(0).length;i++)
                            System.out.println(":"+resultData1.get(0)[i]);

                        adres.setId_adresu(((BigDecimal)resultData1.get(0)[0]).intValue());
                        adres.setMiejscowosc((String)resultData1.get(0)[1]);
                        adres.setKod_Pocztowy((String)resultData1.get(0)[2]);
                        adres.setUlica((String)resultData1.get(0)[3]);
                        adres.setNumer_Budynku(((BigDecimal)resultData1.get(0)[4]).intValue());
                    }
                }
                zalogowany.setAdres(adres);

                System.out.println("ZALOGOWANO: "+zalogowany.toString());
            }else
            {
                error.setText("NIE POPRAWNE DANE LOGOWANIA");
            }

        }


//        zalogowany.setId_pracownika((BigDecimal) pracownik2[0]);
//        zalogowany.setImie((String)pracownik2[1]);
//        zalogowany.setNazwisko((String)pracownik2[2]);
//        zalogowany.setPesel((String)pracownik2[3]);
//        zalogowany.setData_urodzenia((Date)pracownik2[4]);
//        System.out.println("xd");

//        EntityManagerFactory entityManagerFactory = dbSession.session.getEntityManagerFactory();
//        EntityManager entityManager = entityManagerFactory.createEntityManager();

//        Object xd=entityManager.createQuery("CALL GETPRACOWNIK(:login,:haslo,:out)",Pracownicy.class).setParameter(1,login.getText()).setParameter(2,haslo.getText()).getSingleResult();

//        Object xd2=dbSession.session.createNamedStoredProcedureQuery("GETPRACOWNIK")
//                .registerStoredProcedureParameter(1,String.class,ParameterMode.IN)
//                .setParameter(1,login.getText())
//                .registerStoredProcedureParameter(2,String.class,ParameterMode.IN)
//                .setParameter(2,haslo.getText())
//                .registerStoredProcedureParameter(3,void.class,ParameterMode.REF_CURSOR).getOutputParameterValue(3);

//        entityManager.getTransaction().begin();
//        StoredProcedureQuery query = entityManager
//                .createStoredProcedureQuery("GETPRACOWNIK")
//                .registerStoredProcedureParameter(1,String.class,ParameterMode.IN)
//                .setParameter(1,login.getText())
//                .registerStoredProcedureParameter(2,String.class,ParameterMode.IN)
//                .setParameter(2,haslo.getText())
//                .registerStoredProcedureParameter(3,void.class,ParameterMode.REF_CURSOR);
//        query.execute();


//        List result2 = query.getResultList();

//        Query query2= dbSession.session.getNamedQuery("GETPRACOWNIK")
//                .setParameter("login",login.getText())
//                .setParameter("haslo",haslo.getText())
//                .setParameter("data", Cursor.class);
//        List result = query2.getResultList();

//        Object dane= query.getResultList();
//
//        entityManager.getTransaction().commit();
//        entityManager.close();
//        System.out.println(dane);
//
//        List<Object[]> objectList=query.getResultList();
//        Pracownicy prac=null;
//        BigDecimal bd=new BigDecimal(""+objectList.get(0)[0].toString());
//        prac.setId_pracownika(bd);
//        prac.setImie((String)objectList.get(0)[1]);
//        prac.setNazwisko((String)objectList.get(0)[2]);
//        prac.setPesel((String)objectList.get(0)[3]);
//        prac.setData_urodzenia((Date)objectList.get(0)[4]);



//        System.out.println(pracownik2[0].getClass().getName());

//        zalogowany.setId_pracownika((BigDecimal) pracownik2[0]);
//        zalogowany.setImie((String)pracownik2[1]);
//        zalogowany.setNazwisko((String)pracownik2[2]);
//        zalogowany.setPesel((String)pracownik2[3]);
//        zalogowany.setData_urodzenia((Date)pracownik2[4]);

//        List<Object> result = (List<Object>) query.getResultList();
//        Iterator itr = result.iterator();
//
//        if(result.size()<1) {
//            error.setText("NIE POPRAWNE DANE LOGOWANIA");
//        }else {
//            error.setText("ZALOGOWANO");
//            while(itr.hasNext()){
//                Object[] obj = (Object[]) itr.next();
//
//                for(int i=0;i<obj.length;i++)
//                {
//                    System.out.println("- "+obj[i]);
//                }
//
//                System.out.println("XD:"+obj[0].getClass().getName());
//                BigDecimal bd=new BigDecimal((BigDecimal)obj[0]);
//

//                Integer c=((BigDecimal)obj[0]).intValue();
//                Object b=c;
//                System.out.println("wartosc: "+c+" : "+b.getClass().getName());

//                zalogowany.setId_pracownika((BigDecimal)obj[0]);
//                zalogowany.setImie(new String((char[])obj[1]));
//                zalogowany.setNazwisko((String)obj[2]);
//                zalogowany.setPesel((String)obj[3]);
//                zalogowany.setData_urodzenia((Date)obj[4]);

//                Adres adres=new Adres();
//                adres.setId_adresu((int)obj[5]);

//                StoredProcedureQuery query2 = entityManager
//                        .createStoredProcedureQuery("GETADRES")
//                        .registerStoredProcedureParameter(1,Integer.class,ParameterMode.IN)
//                        .setParameter(1,(int)obj[5])
//                        .registerStoredProcedureParameter(2,Object.class,ParameterMode.REF_CURSOR);
//                query2.execute();
//
//                List<Object> result2 = (List<Object>) query.getResultList();
//                Iterator itr2 = result.iterator();
//                while(itr2.hasNext()){
//                    Object[] obje = (Object[]) itr2.next();
//                    adres.setPracownicy(Arrays.asList(zalogowany));
//                    adres.setKod_Pocztowy((String)obje[2]);
//                    adres.setMiejscowosc((String)obje[1]);
//                    adres.setUlica((String)obje[3]);
//                    adres.setNumer_Budynku((Integer)obje[4]);
//                }
//                zalogowany.setAdres(adres);


//                zalogowany.setLogin((String)obj[6]);
//                zalogowany.setHaslo((String)obj[7]);

//                System.out.println(zalogowany.toString());

//            System.out.println("id_ksiazki: "+obj[0]);
//            System.out.println("tytul: "+obj[1]);
//            System.out.println("data_wydania: "+obj[2]);
//            System.out.println("ilosc: "+obj[3]);
//            System.out.println("id_wydawnictwa: "+obj[4]);
//
//
//
//            System.out.println("id_kategorii: "+obj[5]);
//            System.out.println("------------------------------------------------");
//            }
//        }







//        List books=query.getResultList();
//
//        Query query = dbSession.session.createSQLQuery("CALL GET_PRACOWNIK_DATA(:login,:haslo,:danepracownika)")
//                .addEntity(Pracownicy.class)
//                .setParameter("login",login.getText())
//                .setParameter("haslo",login.getText());
//        System.out.println(query.list().toString());
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert login != null : "fx:id=\"login\" was not injected: check your FXML file 'login.fxml'.";
        assert haslo != null : "fx:id=\"hasło\" was not injected: check your FXML file 'login.fxml'.";
        assert error != null : "fx:id=\"error\" was not injected: check your FXML file 'login.fxml'.";

    }

}
