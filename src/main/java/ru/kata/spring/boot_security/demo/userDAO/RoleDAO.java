package ru.kata.spring.boot_security.demo.userDAO;

import ru.kata.spring.boot_security.demo.model.Role;


import java.util.List;

public interface RoleDAO {
    List<Role> getAllRoles();

    Role getRoleById(long id);

    Role getRoleByName(String name);

}
