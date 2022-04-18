package ru.kata.spring.boot_security.demo.userDAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.User;

@Repository
public interface UserRepository extends JpaRepository <User, Long> {
    User findUserByEmail(String email);
    User findUserById(long id);
    boolean existsByEmail(String email);
}
