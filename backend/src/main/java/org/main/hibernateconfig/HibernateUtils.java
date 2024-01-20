package org.main.hibernateconfig;


import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.cfg.JdbcSettings;
import org.hibernate.service.ServiceRegistry;
import org.main.entity.*;

import java.util.Properties;

public class HibernateUtils {
    private static SessionFactory sessionFactory = getSessionFactory();
    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Properties settings = new Properties();

                settings.put(JdbcSettings.JAKARTA_JDBC_DRIVER, "org.postgresql.Driver");
                settings.put(JdbcSettings.JAKARTA_JDBC_URL, "jdbc:postgresql://localhost:5432/bank");
                settings.put(JdbcSettings.JAKARTA_JDBC_USER, "test_user");
                settings.put(JdbcSettings.JAKARTA_JDBC_PASSWORD, "password");
                settings.put(Environment.DIALECT, "org.hibernate.dialect.PostgreSQLDialect");
                settings.put(Environment.SHOW_SQL, "true");
                settings.put(Environment.FORMAT_SQL, "true");
                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
                settings.put(Environment.HBM2DDL_AUTO, "update"); // or create


                Configuration configuration = new Configuration();

                configuration.setProperties(settings);

                configuration.addAnnotatedClass(Credential.class);
                configuration.addAnnotatedClass(Client.class);
                configuration.addAnnotatedClass(Account.class);
                configuration.addAnnotatedClass(Card.class);
                configuration.addAnnotatedClass(Credit.class);
                configuration.addAnnotatedClass(TypeCredit.class);
                configuration.configure();

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();

                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }
}
