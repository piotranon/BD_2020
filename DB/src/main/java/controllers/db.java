package controllers;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class db {
    public static SessionFactory sessionFactory = null;
    public static Session session = null;

    public void sessionStart() {
        if (sessionFactory == null) {
            sessionFactory = new Configuration().configure().buildSessionFactory();
            session = sessionFactory.openSession();
            session.beginTransaction();
            System.out.println("rozpoczeto sesje");
        } else
            System.out.println("sesja juz istnieje");
    }

    public static <T> List<T> loadAllData(Class<T> type) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> criteria = builder.createQuery(type);
        criteria.from(type);
        List<T> data = session.createQuery(criteria).getResultList();
        return data;
    }

    @Override
    public String toString() {
        return "Session{" +
                "Sessionfactory=" + sessionFactory +
                ", session=" + session +
                '}';
    }
}
