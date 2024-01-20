package org.main.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.main.dto.AccountData;
import org.main.entity.Account;
import org.main.entity.Client;
import org.main.entity.Credential;
import org.main.hibernateconfig.HibernateUtils;
import validation.ValidationUtilsBackend;

import java.util.List;

public class ClientDAO {
    private static final SessionFactory sessionFactory= HibernateUtils.getSessionFactory();
    private static final ValidationUtilsBackend validation=new ValidationUtilsBackend();

    public static List<Client> getAll() {
        Transaction transaction = null;
        List<Client> clientList = null;
        try (Session session = sessionFactory.openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // get all
            clientList = session.createQuery("from Client c where c.deleted=false").list();

            System.out.println(clientList.toString());
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return clientList;
    }
    public static Client getByPassport(String passport) {
        Transaction transaction = null;
        Client clientList = null;
        try (Session session = sessionFactory.openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // get all
            clientList = session.createQuery("select c from Client c where c.deleted=false and c.passport_details=:passport",Client.class)
                    .setParameter("passport",passport).getSingleResult();

            System.out.println(clientList.toString());
            // commit transaction
            transaction.commit();
        } catch (Exception e) {

            throw new RuntimeException("No such client by this passport :"+passport);
        }
        return clientList;
    }


}
