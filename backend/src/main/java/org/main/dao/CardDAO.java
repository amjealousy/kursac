package org.main.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.main.dto.AccountData;
import org.main.dto.CardData;
import org.main.entity.Account;
import org.main.entity.Card;
import org.main.hibernateconfig.HibernateUtils;
import validation.ValidationUtilsBackend;

import java.util.List;

public class CardDAO {
    private static final SessionFactory sessionFactory= HibernateUtils.getSessionFactory();
    private static final ValidationUtilsBackend validation=new ValidationUtilsBackend();

    public static List<CardData> getAll() {
        Transaction transaction = null;
        List<Card> accountList;
        List<CardData> daoList =null;
        try (Session session = sessionFactory.openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // get all
            accountList = session.createQuery("from Card ").list();
            daoList = accountList.stream().map(card -> new CardData().toDTO(card)).toList();
            System.out.println(daoList);
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return daoList;
    }

    public static CardData getByData(CardData data) {
        Transaction transaction = null;
        Card card = null;
        CardData dao = null;
        try (Session session = sessionFactory.openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // get all
            card = (Card) session.createQuery("select a from Card a WHERE a.number=:number").setParameter("number",data.getNumber()).getSingleResult();
            dao =new CardData().toDTO(card);
            System.out.println(card);
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
//            if (transaction != null) {
//                transaction.rollback();
//            }
            throw new RuntimeException("No such card number by this card number "+data.getNumber());
        }
        return dao;
    }
}
