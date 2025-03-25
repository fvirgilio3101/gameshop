package it.ecubit.gameshop.controller;

import it.ecubit.gameshop.dto.UserDTO;
import it.ecubit.gameshop.entity.User;
import it.ecubit.gameshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/user")
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping
    public List<UserDTO> readAll(){
       return this.service.readAll();
    }

    @PostMapping()
    public UserDTO create(@RequestBody UserDTO toSave){
        return this.service.save(toSave);
    }

    @PutMapping()
    public UserDTO save(@RequestBody UserDTO toSave){
        return this.service.save(toSave);
    }

    @DeleteMapping
    public void delete(@RequestBody UserDTO toDelete){
         this.service.deleteUser(toDelete);
    }
}
