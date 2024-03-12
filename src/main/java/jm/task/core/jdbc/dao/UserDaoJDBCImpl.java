package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    private Connection connection;
    public UserDaoJDBCImpl() {

        connection = Util.getConnection();
    }

    public void createUsersTable() {

        String query = "CREATE TABLE IF NOT EXISTS users (" +
                "id BIGINT AUTO_INCREMENT PRIMARY KEY," +
                "name VARCHAR(255) NOT NULL," +
                "lastName VARCHAR(255) NOT NULL," +
                "age TINYINT UNSIGNED NOT NULL" +
                ")";

        // Выполнение SQL-запроса с использованием соединения из класса Util
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(query);
            System.out.println("created");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

    }

    public void dropUsersTable() {
        String query = "DROP TABLE IF EXISTS users";

        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(query);
            System.out.println("dropped");
        } catch (SQLException e) {
            System.err.println( e.getMessage());
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String query = "INSERT INTO users (name, lastName, age) VALUES ('" + name + "', '" + lastName + "', " + age + ")";

        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(query);
            System.out.println("saved " + name);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public void removeUserById(long id) {
        String query = "DELETE FROM users WHERE id = " + id;

        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(query);
            System.out.println("removed " + id);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String query = "SELECT * FROM users";

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                String lastName = resultSet.getString("lastName");
                byte age = resultSet.getByte("age");
                users.add(new User(id, name, lastName, age));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return users;
    }

    public void cleanUsersTable() {
        String query = "TRUNCATE TABLE users";

        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(query);
            System.out.println("cleaned");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}
