package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

public class Main {

    public static void main(String[] args) {

       UserServiceImpl userService = new UserServiceImpl();

        userService.createUsersTable();
        User[] users = new User[]{
                new User("Ivan", "Alexeev", (byte) 15),
                new User("Christopher", "Nolan", (byte) 65),
                new User("Martin", "Scorsese", (byte) 88),
                new User("Vasya", "Pupkin", (byte) 34)};
        for (User user : users) {
            userService.saveUser(user.getName(),  user.getLastName(), user.getAge());
        }
        userService.getAllUsers().forEach(System.out::println);
        userService.cleanUsersTable();
        userService.dropUsersTable();

    }
}
