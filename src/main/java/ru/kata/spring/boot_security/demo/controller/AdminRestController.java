package ru.kata.spring.boot_security.demo.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;
import java.util.List;





@RestController
@RequestMapping("/api/admin")
public class AdminRestController {

    private UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminRestController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;

    }


    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUser() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("users/{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PostMapping("/users")
    public ResponseEntity<List<User>> addUser(@RequestBody User user){
        if (userService.isExistByEmail(user.getEmail())){
            throw new IllegalArgumentException(String.format("User with email: '%s' already exists", user.getEmail()));
        }
        userService.saveUser(user, user.getRoles());
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PutMapping("/users")
    public ResponseEntity<List<User>> updateUser(@RequestBody User user) {
        userService.updateUser(user);
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @DeleteMapping("users/{id}")
    public ResponseEntity<List<User>> deleteUser(@PathVariable("id") long id) {
        userService.removeUser(id);
        return ResponseEntity.ok(userService.getAllUsers());
    }
}
