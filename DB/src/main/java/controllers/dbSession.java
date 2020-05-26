package controllers;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class dbSession {
    public static SessionFactory sessionFactory=null;
    public static Session session=null;

    public void sessionStart(){
        if(sessionFactory==null)
        {
            sessionFactory = new Configuration().configure().buildSessionFactory();
            session = sessionFactory.openSession();
            System.out.println("rozpoczeto sesje");
        }
        else
            System.out.println("sesja juz istnieje");
    }

    public void sessionClose(){
        sessionFactory.close();
        sessionFactory=null;
        session=null;
        System.out.println("sesja zakonczona");
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    @Override
    public String toString() {
        return "Session{" +
                "Sessionfactory=" + sessionFactory +
                ", session=" + session +
                '}';
    }
}
