package com.practice.jwtdemo.controller;

import com.practice.jwtdemo.model.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class UserController {

    @PostMapping("user")
    public User login(@RequestParam("user") String username, @RequestParam("password") String password )
    {
        String token = getJwtToken(username);
        User user = new User();
        user.setUser(username);
        user.setPwd(password);
        user.setToken(token);
        return user;
    }

    private String getJwtToken(String username) {
        String SecretKey = "mySecretKey";
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList("ROLE_UER");
        String token = Jwts
                        .builder()
                        .setId("amritajwt")
                        .claim("authorities",
                                grantedAuthorities.stream()
                                        .map(GrantedAuthority::getAuthority)
                                        .collect(Collectors.toList()))
                        .setIssuedAt(new Date(System.currentTimeMillis()))
                        .setExpiration(new Date(System.currentTimeMillis() + 600000))
                        .signWith(SignatureAlgorithm.HS512, SecretKey.getBytes())
                        .compact();
        return "Bearer " + token;
    }
}
