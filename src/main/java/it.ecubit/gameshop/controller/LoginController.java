package it.ecubit.gameshop.controller;

import it.ecubit.gameshop.service.JwtService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController

public class LoginController {

    @Autowired

    private AuthenticationManager authManager;

    @Autowired

    private JwtService jwtService;

    @PostMapping("/login")

    public void login(@RequestBody String username, String password, HttpServletResponse response) throws IOException {

        Authentication authentication = authManager.authenticate(

                new UsernamePasswordAuthenticationToken(username, password)

        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtService.generateToken(username);

        Cookie cookie = new Cookie("AUTH_TOKEN", jwt);

        cookie.setHttpOnly(true);

        cookie.setSecure(false);

        cookie.setPath("/");

        cookie.setMaxAge(86400);

        response.addCookie(cookie);

        response.setContentType("application/json");

        response.getWriter().write("{\"message\":\"Login successful\"}");

    }

    @GetMapping("/api/auth/check")
    public ResponseEntity<?> checkAuth() {
        return ResponseEntity.ok().body("{\"message\":\"Authenticated\"}");
    }

    @GetMapping("/logout")
    public ResponseEntity<?> logout(HttpServletResponse response) {
        Cookie cookie = new Cookie("AUTH_TOKEN", "");
        cookie.setPath("/");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return ResponseEntity.ok().body("{\"message\":\"Logged out\"}");
    }

}

