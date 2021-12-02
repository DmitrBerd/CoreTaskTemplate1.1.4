package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    Util util = new Util();

    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {
        try (Statement statement = util.getConnection().createStatement()) {
            statement.execute("CREATE TABLE  if not exists Users (\n" +
                    "  `id` BIGINT NOT NULL AUTO_INCREMENT,\n" +
                    "  `name` VARCHAR(15) NOT NULL,\n" +
                    "  `lastName` VARCHAR(45) NOT NULL,\n" +
                    "  `age`  TINYINT NULL,\n" +
                    "  PRIMARY KEY (`id`),\n" +
                    "  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE)\n");
            System.out.println("таблица создана");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    public void dropUsersTable() {
        try (Statement statement = util.getConnection().createStatement()) {
            statement.execute("DROP TABLE  if exists Users ");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (PreparedStatement st = util.getConnection().
                prepareStatement("INSERT INTO Users (name, lastName, age) VALUES (?, ?, ?)")) {
            st.setString(1, name);
            st.setString(2, lastName);
            st.setByte(3, age);
            st.executeUpdate();
            System.out.println("User с именем – " + name + " добавлен в базу данных");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try (PreparedStatement st = util.getConnection().
                prepareStatement("DELETE FROM Users WHERE ID = ?;")) {
            st.setLong(1, id);
            st.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        try (Statement statement = util.getConnection().createStatement()) {
            ResultSet rs = statement.executeQuery("select * from Users ");
            while (rs.next()) {
                long id = rs.getInt("id");
                String name = rs.getString("name");
                String lastName = rs.getString("lastName");
                byte age = rs.getByte("age");
                User user = new User(name, lastName, age);
                user.setId(id);
                list.add(user);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }

    public void cleanUsersTable() {
        try (Statement statement = util.getConnection().createStatement()) {
            statement.execute("TRUNCATE TABLE Users");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
