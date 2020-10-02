package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    User user = new User();
    Util util1 = new Util();
    Connection conn = util1.getConnection();


    public void createUsersTable() {
        try {

            Statement statement = conn.createStatement();
            // создание таблицы
            statement.executeUpdate("CREATE TABLE user (Id INT PRIMARY KEY AUTO_INCREMENT, nameUser VARCHAR(20), lastNameUser VARCHAR(20), ageUser INT)");

            System.out.println("userTable has been created!");
        } catch (Exception ex) {


        }
    }


    public void dropUsersTable() {
        try {

            Statement statement = conn.createStatement();
            statement.executeUpdate("DROP TABLE user ");

            System.out.println("userTable has been droped!");
        } catch (Exception ex) {

            System.out.println(ex);
        }

    }

    public void saveUser(String name, String lastName, byte age) {
        try {
            Statement statement = conn.createStatement();
            statement.executeUpdate("INSERT user (nameUser, lastNameUser, ageUser) " +
                    "VALUES (" + "'" + name + "'" + ", " + "'" + lastName + "'" + "," + age + ")");
            System.out.println("User с именем –" + name + " добавлен в базу данных");

        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public void removeUserById(long id) {
        try {
            Statement statement = conn.createStatement();
            statement.executeUpdate("DELETE FROM user WHERE Id =" + id);

        } catch (Exception ex) {
            System.out.println(ex);
        }

    }

    public List<User> getAllUsers() {
        List<User> allUsers = new ArrayList<>();

        try {
            Statement statement = conn.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT * FROM user");
            while (resultSet.next()) {
                allUsers.add(new User(resultSet.getString(2), resultSet.getString(3), resultSet.getByte(4)));
            }


        } catch (Exception ex) {
            System.out.println(ex);
        }
        return allUsers;
    }


    public void cleanUsersTable() {
        try {
            Statement statement = conn.createStatement();
            statement.executeUpdate("DELETE from user");


        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
}
