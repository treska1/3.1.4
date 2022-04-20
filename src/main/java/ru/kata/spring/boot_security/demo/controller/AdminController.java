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


    @GetMapping
    public String getAllUser(@AuthenticationPrincipal User user, Model model) {
        return "admin";
    }
    @GetMapping("/users")
    public String createUserForm(Model model, User user) {

        return "admin";
    }

    @PostMapping("/users")
    public String createUser(User user, @RequestParam("roleSelect")long[] roleId) {
        return "redirect:/admin";
    }



    @PostMapping("/delete/{id}")
    public String removeUser(@PathVariable("id") long id, Model model) {
        return "redirect:/admin";
    }


    @GetMapping("/{id}")
    public String updateUserForm(@PathVariable("id") long id, Model model) {
        return "admin";
    }

    @PostMapping("/{id}")
    public String userUpdate(@ModelAttribute("user") User user,@RequestParam("roleSelect") long []rolesId) {
        return "redirect:/admin";

    }
}