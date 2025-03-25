package it.ecubit.gameshop.controller;

import it.ecubit.gameshop.dto.GenreDTO;
import it.ecubit.gameshop.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/genre")
public class GenreController {

    @Autowired
    private GenreService genreService;

    @GetMapping
    public List<GenreDTO> getAll() {
        return this.genreService.readAll();
    }

    @PostMapping
    public GenreDTO create(@RequestBody GenreDTO toSave) {
        return this.genreService.save(toSave);
    }

    @PutMapping
    public GenreDTO update(@RequestBody GenreDTO toSave) {
        return this.genreService.save(toSave);
    }

    @DeleteMapping
    public void delete(@RequestBody GenreDTO toDelete) {
        this.genreService.delete(toDelete);
    }

    @DeleteMapping("/all")
    public void deleteAll() {
        this.genreService.deleteAll();
    }
}
