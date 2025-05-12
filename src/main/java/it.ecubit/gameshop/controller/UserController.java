package it.ecubit.gameshop.controller;

import it.ecubit.gameshop.dto.UserDTO;
import it.ecubit.gameshop.entity.User;
import it.ecubit.gameshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("api/user")
public class UserController {

    @Autowired
    private UserService service;

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
}
