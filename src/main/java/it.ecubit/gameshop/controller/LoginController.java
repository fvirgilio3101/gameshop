package it.ecubit.gameshop.controller;

import it.ecubit.gameshop.dto.LoginDTO;
import it.ecubit.gameshop.service.JwtService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class LoginController {

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/login")
    public void login(@RequestBody LoginDTO loginDto, HttpServletResponse response) throws IOException {
        Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtService.generateToken(loginDto.getUsername());
        String cookie = String.format("AUTH_TOKEN=%s; Max-Age=86400; Path=/; HttpOnly; SameSite=Lax", jwt);
        response.setHeader("Set-Cookie", cookie);


        response.setContentType("application/json");
        response.getWriter().write("{\"message\":\"Login successful\"}");
    }


    @GetMapping("/auth/check")
    public ResponseEntity<?> checkAuth() {
        return ResponseEntity.ok().body(Map.of("status", "authenticated"));
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

