package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class Util {
    private String url = "jdbc:mysql://localhost/store?serverTimezone=Europe/Moscow&useSSL=false";
    private String username = "root";
    private String password = "PASSFORJAZZDATAMANAge__1213455}[";
    private String driver = "com.mysql.cj.jdbc.Driver";

    public Connection getConnection() {
        Connection conn = null;
        try {

            Class.forName(driver).getDeclaredConstructor().newInstance();
            conn = DriverManager.getConnection(url, username, password);

            System.out.println("Connection to Store DB succesfull!");

        } catch (Exception ex) {
            System.out.println("Connection failed...");


            System.out.println(ex);

        }
        return conn;
    }

    public SessionFactory getHibernateConnection() {

       /* Configuration configuration = new Configuration()
                .addAnnotatedClass(User.class)
               // .addClass(org.hibernate.auction.Item.class)
                //.addClass(org.hibernate.auction.Bid.class)
                .setProperty("hibernate.connection.url",
                        "jdbc:mysql://localhost/store?serverTimezone=Europe/Moscow&useSSL=false")
                .setProperty("dialect", "org.hibernate.dialect.MySQLDialect")
                .setProperty("hibernate.connection.username", "root")
                .setProperty("hibernate.connection.password", "PASSFORJAZZDATAMANAge__1213455}[")
                .setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver")
                .setProperty("hbm2ddl.auto", "create")
                .setProperty("show_sql", "true");
        StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties()).build();
        SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
       */

        Properties prop= new Properties();

        prop.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/store?serverTimezone=Europe/Moscow&useSSL=false");

        prop.setProperty("dialect", "org.hibernate.dialect.MySQL8Dialect");

        prop.setProperty("hibernate.connection.username", "root");
        prop.setProperty("hibernate.connection.password", "PASSFORJAZZDATAMANAge__1213455}[");
        prop.setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
        prop.setProperty("hbm2ddl.auto", "create");
        prop.setProperty("show_sql", "true");
        prop.setProperty("hibernate.hbm2ddl.auto", "create-drop");
        prop.setProperty("hibernate.hbm2ddl.auto", "update");


        SessionFactory sessionFactory = new Configuration()
               .addProperties(prop).addAnnotatedClass(User.class)
                .buildSessionFactory();

        //Session session = sessionFactory.openSession();
        //Transaction transaction = session.beginTransaction();
        //session.reconnect(connection);

        return sessionFactory;
        /*



               Configuration configuration = new Configuration().configure();
               configuration.addProperties(prop).addAnnotatedClass(User.class);
               StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
               SessionFactory sessionFactory = configuration.buildSessionFactory(builder.build());



       return sessionFactory; */
   }

}

