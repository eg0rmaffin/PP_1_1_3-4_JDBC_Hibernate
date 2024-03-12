package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserServiceImpl userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Ivan", "Ivanov", (byte) 18);
        userService.saveUser("Petr", "Petrov", (byte) 23);
        userService.saveUser("Vasya", "Pupkin", (byte) 33);
        userService.saveUser("John", "Smith", (byte) 30);
        List<User> allUsers = userService.getAllUsers();
        for (User user:allUsers) {
            System.out.println(user);
        }
        userService.cleanUsersTable();
        userService.dropUsersTable();

    }
}
