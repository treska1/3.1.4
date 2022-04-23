package ru.kata.spring.boot_security.demo.securityConfig;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.model.AuthProvider;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.oauth2.CustomOAuth2User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class OAuth2UserHandler extends SimpleUrlAuthenticationSuccessHandler {
    private Logger LOGGER = Logger.getLogger("<-----Start logging Oauth2 handler ----->");
    private final UserService userService;
    private final RoleService roleService;


    public OAuth2UserHandler(UserService userService,
                             RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        CustomOAuth2User oAuth2User = (CustomOAuth2User) authentication.getPrincipal();
        LOGGER.log(Level.INFO, oAuth2User.getEmail());

        if (!userService.isExistByEmail(oAuth2User.getEmail())){
            User user = new User();
            user.setEmail(oAuth2User.getEmail());
            user.setName(oAuth2User.getName());
            user.setSurname(oAuth2User.getSurname());
            user.setAge((byte) 0);
            user.setPassword("0000");
            Set<Role> giveUserRole = new HashSet<>();
            if (user.getRoles().contains("ADMIN")){
                giveUserRole.add(roleService.getRoleById(1));
            } if (user.getRoles().contains("USER")){
                giveUserRole.add(roleService.getRoleById(2));
            }
            user.setRoles(giveUserRole);
            user.setAuthProvider(AuthProvider.GOOGLE);
            userService.saveUser(user);
        } else {
            userService.getUserByEmail(oAuth2User.getEmail());
        }
        Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
        if (roles.contains("ROLE_ADMIN")) {
            response.sendRedirect("/admin");
        } else {
            response.sendRedirect("/user");
        }
    }

}
