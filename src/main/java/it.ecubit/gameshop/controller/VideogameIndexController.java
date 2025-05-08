package it.ecubit.gameshop.controller;

import it.ecubit.gameshop.document.VideogameDocument;
import it.ecubit.gameshop.service.VideogameIndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/index")
@CrossOrigin(origins = "http://localhost:4200")
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

    @GetMapping("/filter")
    public List<VideogameDocument> getFilteredVideogames(
            @RequestParam(value = "keyword",required = false) String title,
            @RequestParam(value= "price", required = false) Double maxPrice,
            @RequestParam(value = "releaseAfter",required = false) String releaseAfter) {

        LocalDate releaseAfterDate = null;
        if (releaseAfter != null) {
            releaseAfterDate = LocalDate.parse(releaseAfter);
        }

        return indexService.search(title, maxPrice, releaseAfterDate);
    }
}
