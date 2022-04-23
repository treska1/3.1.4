package ru.kata.spring.boot_security.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.oauth2.CustomOAuth2User;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;
import java.util.logging.Logger;

@RestController
@RequestMapping("api/user")
public class UserRestController {
    private Logger LOGGER = Logger.getLogger("<--- Logging in the User Controller --->");
    private final UserService userService;

    @Autowired
    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<User> getUserByEmail(Principal principal, Authentication authentication) {

        if (principal.getClass().isInstance(User.class)){
            LOGGER.info(" aut principal USER.class :" + principal.getName());
            return ResponseEntity.ok(userService.getUserByEmail(principal.getName()));
        } else {
//            CustomOAuth2User oAuth2User = (CustomOAuth2User) authentication.getPrincipal();
            LOGGER.info(" aut principal OAuth2User.class :" + principal.getName());
            return ResponseEntity.ok(userService.getUserByEmail(principal.getName()));
        }
    }
}
