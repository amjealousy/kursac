package org.main;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.main.entity.Credential;
import org.main.entity.Degree;

import java.time.LocalDate;

public class Starter {
    private static SessionFactory factory;

    public Credential getCredential() {
        Transaction transaction = null;
        Credential credential = null;
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the student object
            credential = session.get(Credential.class, 1);
            System.out.println(credential.toString());
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return credential;
    }
    public void persistEntity(Credential ent) {
        Transaction transaction = null;
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            // start a transaction
//            LocalDate birthday =LocalDate.of(2003,11,11);
            transaction = session.beginTransaction();
//            Credential credentialNew=Credential.builder().inn("333").full_name("Kirill")
//                    .address("Tula").date_of_bday(birthday).job("java programmer")
//                    .passport_details("2322423").phone("+7912423459").degree(Degree.Non1)
//                    .build();
////            Credential credential = session.get(Credential.class, 1);
            session.persist(ent);
            System.out.println(ent.toString());
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }


    }
}
