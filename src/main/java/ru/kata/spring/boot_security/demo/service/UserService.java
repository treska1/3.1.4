package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;
import java.util.Set;

public interface UserService extends UserDetailsService {

    List getAllUsers();

    void saveUser(User user, Set<Role> roleSet);

    User getUserById(long id);

    void removeUser(long id);

    void updateUser(User user);

    User getUserByEmail(String username);

    boolean isExistByEmail(String email);
}
