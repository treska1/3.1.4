package ru.kata.spring.boot_security.demo.oauth2;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Map;

public class OAuth2User implements org.springframework.security.oauth2.core.user.OAuth2User {

    private final org.springframework.security.oauth2.core.user.OAuth2User oAuth2User;

    public OAuth2User(org.springframework.security.oauth2.core.user.OAuth2User oAuth2User) {
        this.oAuth2User = oAuth2User;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return oAuth2User.getAttributes();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return oAuth2User.getAuthorities();
    }

    @Override
    public String getName() {
        return oAuth2User.getAttribute("given_name");
    }

    public String getSurname(){
        return oAuth2User.getAttribute("family_name");
    }

    public String getEmail(){
        return oAuth2User.getAttribute("email");
    }
}
