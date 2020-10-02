package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.hibernate.type.ByteType;
import org.hibernate.type.IntegerType;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;

import java.io.ByteArrayInputStream;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    Util util = new Util();
    SessionFactory sessionFactory1 = util.getHibernateConnection();
    public UserDaoHibernateImpl() {
    }

    @Override
    public void createUsersTable() {
        try {
            Session session = sessionFactory1.openSession();
            Transaction transaction = session.beginTransaction();
            session.createSQLQuery("CREATE TABLE IF NOT EXISTS user1" +
                    "(Id INT PRIMARY KEY AUTO_INCREMENT, nameUser VARCHAR(20), " +
                    "lastNameUser VARCHAR(20), ageUser INT)").executeUpdate();

            transaction.commit();
            session.close();

        } catch (Exception ex) {
            System.out.println(ex);

        }
    }

    @Override
    public void dropUsersTable() {
        try {
            Session session = sessionFactory1.openSession();
            Transaction transaction = session.beginTransaction();
            session.createSQLQuery("DROP TABLE IF EXISTS user1").executeUpdate();

            transaction.commit();
            session.close();

        } catch (Exception ex) {
            System.out.println(ex);

        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try {
            Session session = sessionFactory1.openSession();
            Transaction transaction = session.beginTransaction();
            session.createSQLQuery("INSERT user1 (nameUser, lastNameUser, ageUser)" +
                    "VALUES (" + "'" + name + "'" + ", " + "'" + lastName + "'" + "," + age + ")").executeUpdate();
            System.out.println("User с именем –" + name + " добавлен в базу данных");

            transaction.commit();
            session.close();

        } catch (Exception ex) {
            System.out.println(ex);

        }
    }

    @Override
    public void removeUserById(long id) {
        try {
            Session session = sessionFactory1.openSession();
            Transaction transaction = session.beginTransaction();
            session.createSQLQuery("DELETE FROM user1 WHERE Id =" + id).executeUpdate();

            transaction.commit();
            session.close();

        } catch (Exception ex) {
            System.out.println(ex);

        }

    }

    @Override
    public List<User> getAllUsers() {
       List<User> allUsers = new ArrayList<>();
        try {
            Session session = sessionFactory1.openSession();
            Transaction transaction = session.beginTransaction();
            List<Object[]> allUsers1 = session.createSQLQuery("SELECT * FROM user1")
                    .addScalar("id", LongType.INSTANCE)
                    .addScalar( "nameUser", StringType.INSTANCE )
                    .addScalar( "lastNameUser", StringType.INSTANCE )
                    .addScalar("ageUser", ByteType.INSTANCE)
                    .list();
        for (Object[] objects : allUsers1) {
            allUsers.add(new User((String)objects[1], (String)objects[2], (Byte)objects[3]));
        }
            transaction.commit();
            session.close();
        } catch (Exception ex) {

        System.out.println(ex);
    }
        return allUsers;
    }

    @Override
    public void cleanUsersTable() {
        try {
            Session session = sessionFactory1.openSession();
            Transaction transaction = session.beginTransaction();
            session.createSQLQuery("DELETE from user1").executeUpdate();

            transaction.commit();
            session.close();

        } catch (Exception ex) {
            System.out.println(ex);

        }

    }
}
