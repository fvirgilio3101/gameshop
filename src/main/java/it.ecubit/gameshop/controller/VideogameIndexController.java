package it.ecubit.gameshop.controller;

import it.ecubit.gameshop.service.VideogameIndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/index")
public class VideogameIndexController {
    @Autowired
    private VideogameIndexService indexService;

    @PostMapping("/videogames")
    public String indexVideogames(){
        indexService.indexAll();
        return "Indicizzazione completata";
    }
}
