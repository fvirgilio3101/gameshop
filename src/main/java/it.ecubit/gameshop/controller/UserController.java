package it.ecubit.gameshop.controller;

import it.ecubit.gameshop.dto.UserDTO;
import it.ecubit.gameshop.entity.User;
import it.ecubit.gameshop.mappers.UserMapper;
import it.ecubit.gameshop.repository.UserRepository;
import it.ecubit.gameshop.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("api/user")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    @Autowired
    private UserService service;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public List<UserDTO> readAll() {
        return this.service.readAll();
    }

    @PostMapping("/register")
    public UserDTO register(@RequestBody UserDTO toSave) {
        try {
            return this.service.registerUser(toSave);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PostMapping
    public UserDTO create(@RequestBody UserDTO toSave) {
        return this.service.save(toSave);
    }

    @PutMapping
    public UserDTO save(@RequestBody UserDTO toSave) {
        return this.service.save(toSave);
    }

    @DeleteMapping
    public void delete(@RequestBody UserDTO toDelete) {
        this.service.deleteUser(toDelete);
    }
    @GetMapping("/me")
    public ResponseEntity<UserDTO> getCurrentUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        if (username == null || username.isEmpty() || username.equals("anonymousUser")) {
            throw new EntityNotFoundException("Utente non autenticato");
        }

        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new EntityNotFoundException("Utente non trovato");
        }

        UserDTO dto = new UserDTO();
        dto.setIdUser(user.getIdUser());

        return ResponseEntity.ok(service.read(dto));
    }



}
