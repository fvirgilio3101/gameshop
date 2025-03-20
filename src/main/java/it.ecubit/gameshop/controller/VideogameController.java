package it.ecubit.gameshop.controller;

import it.ecubit.gameshop.entity.User;
import it.ecubit.gameshop.entity.Videogame;
import it.ecubit.gameshop.service.UserService;
import it.ecubit.gameshop.service.VideogameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/videogame")
public class VideogameController {
    @Autowired
    private VideogameService service;
    @GetMapping
    public List<Videogame> readAll(){
        return this.service.readAll();
    }
    @PostMapping()
    public Videogame create(@RequestBody Videogame toSave){
        return this.service.save(toSave);
    }
    @PutMapping()
    public Videogame save(@RequestBody Videogame toSave){
        return this.service.save(toSave);
    }
    @DeleteMapping
    public void delete(@RequestBody Videogame toDelete){
        this.service.deleteVideogame(toDelete);
    }
}
