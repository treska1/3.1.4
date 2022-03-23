package ru.kata.spring.boot_security.demo.userDAO;


import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserDAO {

    List getAllUsers();

    void saveUser(User user);

    User getUserById(long id);

    void removeUser(long id);

    void updateUser(User user);

    User getUserByEmail(String username);

}
