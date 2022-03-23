package ru.kata.spring.boot_security.demo.userDAO;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class UserDAOImpl implements UserDAO {
    @PersistenceContext
    private EntityManager manager;

    private final PasswordEncoder encoder;
    private final RoleDAO roleDAO;

    @Autowired
    public UserDAOImpl(PasswordEncoder encoder, RoleDAO roleDAO) {
        this.encoder = encoder;
        this.roleDAO = roleDAO;
    }

    @Override
    public void updateUser(User user) {
        manager.merge(user);
    }

    @Override
    public List<User> getAllUsers() {
        return manager.createQuery("FROM User", User.class).getResultList();
    }

    @Override
    public void saveUser(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        manager.persist(user);
    }

    @Override
    public User getUserById(long id) {
        return manager.find(User.class, id);
    }

    @Override
    public void removeUser(long id) {
        manager.remove(manager.find(User.class, id));
    }

    @Override
    public User getUserByEmail(String email) {
        TypedQuery<User> query = manager.createQuery("select u from User u where u.email = :email", User.class)
                .setParameter("email", email);
        return  query.getSingleResult();
    }
}
