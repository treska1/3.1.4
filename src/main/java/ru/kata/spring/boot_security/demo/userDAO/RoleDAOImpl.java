package ru.kata.spring.boot_security.demo.userDAO;

import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
@Repository
public class RoleDAOImpl implements RoleDAO{

    @PersistenceContext
    private EntityManager manager;



    @Override
    public List<Role> getAllRoles() {
        return manager.createQuery("FROM * Role",Role.class).getResultList();
    }

    @Override
    public Role getRoleById(long id) {
        return manager.find(Role.class,id);
    }

    @Override
    public Role getRoleByName(String name) {
        TypedQuery<Role> query = manager.createQuery("select r from Role r where r.name = :name", Role.class)
                .setParameter("name", name);
        return  query.getSingleResult();
    }
}
