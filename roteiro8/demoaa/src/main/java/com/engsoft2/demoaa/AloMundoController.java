package com.engsoft2.demoaa;

import java.util.Map;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.auth0.spring.boot.Auth0AuthenticationToken;

@RestController
@CrossOrigin(origins = "*" )
@RequestMapping("/api")
public class AloMundoController {

    @GetMapping("/public")
    public String ola() {
        return "Olá, Mundo!";
    }

    @GetMapping("/protected")
    public String alo(Authentication authentication) {
        Auth0AuthenticationToken auth0Token = (Auth0AuthenticationToken) authentication;
        var name = authentication.getName();
        Map<String, Object> claims = auth0Token.getClaims();
        var audience = claims.get("aud");    
        var scopes = auth0Token.getAuthorities();
        var listAuthorities = scopes.stream().map(a -> a.getAuthority()).toList();
        return "Alô, " + name + " (" + audience + ")!" + " Você tem os seguintes escopos: " + listAuthorities;
    }

}
