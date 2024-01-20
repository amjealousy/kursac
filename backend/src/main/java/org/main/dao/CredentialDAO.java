package org.main.dao;


import jakarta.persistence.NoResultException;
import jakarta.persistence.Tuple;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.main.entity.Account;
import org.main.entity.Card;
import org.main.entity.Client;
import org.main.entity.Credential;
import org.main.hibernateconfig.HibernateUtils;
import validation.ValidationUtilsBackend;

import java.util.List;
import java.util.Optional;

public class CredentialDAO {
    private static final SessionFactory sessionFactory= HibernateUtils.getSessionFactory();

    private static final ValidationUtilsBackend validation=new ValidationUtilsBackend();
    public static Optional<Credential> getCredential(String login) {
        Transaction transaction = null;
        Credential credential = null;

        try (Session session = sessionFactory.openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the student object
            credential = session.createQuery("select c from Credential c where c.login=:login",Credential.class)
                    .setParameter("login", login).getSingleResult() ;
            System.out.println("credential.toString() present " + credential);



            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            System.out.println("(getCredential) with trouble");
            return Optional.empty();

        }
        return Optional.of(credential);


    }
    public static Optional<Credential> get(Credential credential1) {
        Transaction transaction = null;
        Credential credential = null;

        try (Session session = sessionFactory.openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the student object
            credential = session.createQuery("select c from Credential c where c.login=:login and c.password=:password",Credential.class)
                    .setParameter("login", credential1.getLogin())
                    .setParameter("password",credential1.getPassword())
                    .getSingleResult() ;
            System.out.println("credential.toString() present " + credential);



            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            System.out.println("(getCredential) with trouble");
            return Optional.empty();

        }
        return Optional.of(credential);


    }
    public static Credential persistEntity(Credential ent) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {

            transaction = session.beginTransaction();

            session.persist(ent);

            System.out.println(ent.toString());
            session.flush();

            // commit transaction
            transaction.commit();
            Transaction newTransaction = session.beginTransaction();
            Optional<Credential> credential = CredentialDAO.getCredential(ent.getLogin());
            if (credential.isPresent()){
                return credential.get();
            }
            newTransaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return null;
    }
    public static SimpleMap checkHQL() {
        Transaction transaction = null;
        Tuple t = null;
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            // start a transaction
//            LocalDate birthday =LocalDate.of(2003,11,11);
            transaction = session.beginTransaction();
//
            t = session.createSelectionQuery("""
                    select  u.balance,u.account.client.first_name from Card u where u.account.client.first_name=:first_name
                    """, Tuple.class).setParameter("first_name", "Настя").getSingleResult();
//            List<Client> v =session.createQuery("from Client ").getResultList();
            System.out.println(t.toString());


            transaction.commit();

        } catch (Exception e) {

            e.printStackTrace();
        }
        return new SimpleMap( t.get(0,Double.class), t.get(1,String.class));
    }

    public static Object getFullInfo(Credential login) {
        Transaction transaction = null;
        Tuple t = null;
        try (Session session = sessionFactory.openSession()) {

            transaction = session.beginTransaction();

            t = session.createSelectionQuery("""
                    select  cl,c from Client cl,Account ac ,Card c where cl.credential.id=:id
                    """, Tuple.class).setParameter("id", login.getId()).getSingleResult();
//            List<Client> v =session.createQuery("from Client ").getResultList();
            System.out.println("Tuple obj "+t.toString());

            // commit transaction
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return (Object)t;
    }
}
