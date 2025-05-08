package it.ecubit.gameshop.controller;

import it.ecubit.gameshop.document.VideogameDocument;
import it.ecubit.gameshop.service.VideogameIndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/videogames")
    public List<VideogameDocument> searchVideogames(
            @RequestParam(value = "keyword", required = false) String keyword,
            @RequestParam(value = "price",required = false) Double price
    ) {
        return indexService.search(keyword, price);
    }
}
