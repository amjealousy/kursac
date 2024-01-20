package org.main.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.main.dto.CardData;
import org.main.entity.Card;
import org.main.entity.Client;
import org.main.hibernateconfig.HibernateUtils;
import validation.ValidationUtilsBackend;

import java.util.List;

public class DefaultDAO {
    private static final SessionFactory sessionFactory= HibernateUtils.getSessionFactory();
    private static final ValidationUtilsBackend validation=new ValidationUtilsBackend();

    public static <T> T insertOrUpdate(T data) {
        Transaction transaction = null;
        T client=null;
        try (Session session = sessionFactory.openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // get all
            client = session.merge(data);

            System.out.println(client.toString());
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        System.out.println("in executor method (insertOrUpdate)");
        return client;
    }
    public static Client rcheckMatches(CardData cd,Client c){
        Transaction transaction = null;
        Card client=null;
        try (Session session = sessionFactory.openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // get all


            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        System.out.println("in executor method (insertOrUpdate)");
        return null;
    }
}
