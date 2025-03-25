package it.ecubit.gameshop.controller;

import it.ecubit.gameshop.dto.VideogameDTO;
import it.ecubit.gameshop.entity.Videogame;
import it.ecubit.gameshop.service.VideogameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/videogame")
public class VideogameController {
    @Autowired
    private VideogameService service;
    @GetMapping
    public List<VideogameDTO> readAll(){
        return this.service.readAll();
    }
    @PostMapping()
    public VideogameDTO create(@RequestBody VideogameDTO toSave){
        return this.service.save(toSave);
    }
    @PutMapping()
    public VideogameDTO save(@RequestBody VideogameDTO toSave){
        return this.service.save(toSave);
    }
    @DeleteMapping
    public void delete(@RequestBody VideogameDTO toDelete){
        this.service.deleteVideogame(toDelete);
    }
}
