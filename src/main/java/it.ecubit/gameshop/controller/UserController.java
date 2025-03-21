package it.ecubit.gameshop.controller;

import it.ecubit.gameshop.entity.User;
import it.ecubit.gameshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/user")
public class UserController {
    @Autowired
    private UserService service;
    @GetMapping
    public List<User> readAll(){
       return this.service.readAll();
    }
    @PostMapping()
    public User create(@RequestBody User toSave){
        return this.service.save(toSave);
    }
    @PutMapping()
    public User save(@RequestBody User toSave){
        return this.service.save(toSave);
    }
    @DeleteMapping
    public void delete(@RequestBody User toDelete){
         this.service.deleteUser(toDelete);
    }
}
