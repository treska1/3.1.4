package ru.kata.spring.boot_security.demo.controller;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<User> getAllUser() {
        return userService.getAllUsers();
    }

    @GetMapping("users/{id}")
    public User getUser(@PathVariable("id") long id) {
        return userService.getUserById(id);
    }

    @PostMapping("/users")
    public List<User> addUser(@RequestBody User user){
        userService.saveUser(user, user.getRoles());
        return userService.getAllUsers();
    }

    @PutMapping("/users")
    public List<User> updateUser(@RequestBody User user) {
        userService.updateUser(user);
        return userService.getAllUsers();
    }

    @DeleteMapping("users/{id}")
    public List<User> deleteUser(@PathVariable("id") long id) {
        userService.removeUser(id);
        return userService.getAllUsers();
    }
}
