package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private UserService userService;
    private final RoleService roleService;
    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;

    }

//    @GetMapping
//    public String startPage(){
//        return "admin";
//    }
    @GetMapping
    public String getAllUser(@AuthenticationPrincipal User user, Model model) {
        List<User> list = userService.getAllUsers();
        model.addAttribute("allUsers", list);
        model.addAttribute("userInfo", user);
        model.addAttribute("roles",user.getRoles());

        return "adminpanel";
    }
    @GetMapping("/admin")
    public String createUserForm(Model model, User user) {
        model.addAttribute("user",user);
        return "adminpanel";
    }

    @PostMapping
    public String createUser(User user, @RequestParam("roleSelect")long[] roleId) {
        Set<Role> roleSet = new HashSet<>();
        for (long role: roleId) {
            roleSet.add(roleService.getRoleById(role));
        }
        user.setRoles(roleSet);
        userService.saveUser(user);
        return "redirect:/admin";
    }



    @DeleteMapping("/{id}")
    public String removeUser(@PathVariable("id") long id) {
        userService.removeUser(id);
        return "redirect:/admin";
    }


    @GetMapping("/{id}")
    public String updateUserForm(@PathVariable("id") long id, Model model) {
        return "adminpanel";
    }

    @PostMapping("/{id}")
    public String userUpdate(@ModelAttribute("user") User user,@RequestParam("roleSelect") long []rolesId) {
        Set<Role> roleSet = new HashSet<>();
        for (long role: rolesId) {
            roleSet.add(roleService.getRoleById(role));
        }
        user.setRoles(roleSet);
        userService.updateUser(user);
        return "redirect:/admin";

    }
}
