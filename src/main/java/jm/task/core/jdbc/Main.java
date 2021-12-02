package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Dmitriy", "Berdnikov", (byte) 28);
        userService.saveUser("Kirill", "Kolomok", (byte) 28);
        userService.saveUser("Ivan", "Ivanov", (byte) 42);
        userService.saveUser("Anastasia", "Novikova", (byte) 24);
        userService.getAllUsers();
        userService.removeUserById(1);
        userService.dropUsersTable();
    }
}
