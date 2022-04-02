package ru.kata.spring.boot_security.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

@Controller
@RequestMapping
public class UsersController {

    private UserService userService;

    @Autowired
    public UsersController(UserService userService) {
        this.userService = userService;
    }

//    @GetMapping
//    public String userPage (@AuthenticationPrincipal User user  , Model model){
//        model.addAttribute("userInfo", user);
//        model.addAttribute("roles",user.getRoles());
//        return "user";
//    }
@GetMapping
public String userPage (){

    return "index";
}

}
