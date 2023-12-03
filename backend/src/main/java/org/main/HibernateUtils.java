package org.main;


import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.cfg.JdbcSettings;
import org.hibernate.service.ServiceRegistry;
import org.main.entity.Credential;

import java.util.Properties;

public class HibernateUtils {
    private static SessionFactory sessionFactory;
    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Properties settings = new Properties();

                settings.put(JdbcSettings.JAKARTA_JDBC_DRIVER, "com.mysql.cj.jdbc.Driver");
                settings.put(JdbcSettings.JAKARTA_JDBC_URL, "jdbc:mysql://localhost:3306/bank");
                settings.put(JdbcSettings.JAKARTA_JDBC_USER, "test_user");
                settings.put(JdbcSettings.JAKARTA_JDBC_PASSWORD, "password");
                settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQLDialect");
                settings.put(Environment.SHOW_SQL, "true");
                settings.put(Environment.FORMAT_SQL, "true");
                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
                settings.put(Environment.HBM2DDL_AUTO, "update"); // or create


                Configuration configuration = new Configuration();

                configuration.setProperties(settings);

                configuration.addAnnotatedClass(Credential.class);
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
