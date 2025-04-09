package it.ecubit.gameshop.controller;

import it.ecubit.gameshop.dto.GenreDTO;
import it.ecubit.gameshop.dto.VideogameDTO;
import it.ecubit.gameshop.entity.Videogame;
import it.ecubit.gameshop.service.VideogameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/videogame")
@CrossOrigin(origins = "http://localhost:4200")
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

    @PutMapping(value="/{videogameId}/genre")
    public VideogameDTO addGenre(@RequestBody List<Long> genreIds,@PathVariable("videogameId") Long id){
        return this.service.addGenre(genreIds,id);
    }

    @GetMapping(value="/genre/{genre_name}")
    public List<VideogameDTO> getTopGamesByGenre(@PathVariable("genre_name") String genre){
        return this.service.getTopGamesByGenre(genre);
    }
}
