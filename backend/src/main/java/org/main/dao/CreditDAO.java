package org.main.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.main.dto.AccountData;
import org.main.dto.CreditData;
import org.main.entity.Account;
import org.main.entity.Credit;
import org.main.hibernateconfig.HibernateUtils;
import validation.ValidationUtilsBackend;

import java.util.List;

public class CreditDAO {
    private static final SessionFactory sessionFactory= HibernateUtils.getSessionFactory();
    private static final ValidationUtilsBackend validation=new ValidationUtilsBackend();

    public static List<CreditData> getAll() {
        Transaction transaction = null;
        List<Credit> accountList;
        List<CreditData> daoList =null;
        try (Session session = sessionFactory.openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // get all
            accountList = session.createQuery("from Credit ").list();
            daoList = accountList.stream().map(account -> new CreditData().toDTO(account)).toList();
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
//    public static AccountData getByData(AccountData data){
//        Transaction transaction = null;
//        Account account = null;
//        AccountData dao = null;
//        try (Session session = sessionFactory.openSession()) {
//            // start a transaction
//            transaction = session.beginTransaction();
//            // get all
//            account = (Account) session.createQuery("select a from Account a WHERE a.id=:uuid").setParameter("uuid",data.getUuid()).getSingleResult();
//            dao =new AccountData().toDTO(account);
//            System.out.println(account);
//            // commit transaction
//            transaction.commit();
//        } catch (Exception e) {
//            if (transaction != null) {
//                transaction.rollback();
//            }
//            e.printStackTrace();
//        }
//        return dao;
//    }
}
