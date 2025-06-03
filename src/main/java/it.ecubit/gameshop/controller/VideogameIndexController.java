package it.ecubit.gameshop.controller;

import it.ecubit.gameshop.document.VideogameDocument;
import it.ecubit.gameshop.service.VideogameIndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/index")
@CrossOrigin(origins = "http://localhost:4200")
public class VideogameIndexController {

    @Autowired
    private VideogameIndexService indexService;


    @GetMapping("/videogames")
    public List<VideogameDocument> findAll(){
        return this.indexService.findAll();
    }


    @GetMapping("/trendings")
    public List<VideogameDocument> getTrendingsVideogame(){
        return this.indexService.findDiscountedGames();
    }

    @GetMapping("/best-sellers")
    public List<VideogameDocument> getBestSellingGames() {
        return indexService.findBestSellingGames();

    }


    @GetMapping("/filter")
    public List<VideogameDocument> getFilteredVideogames(
            @RequestParam(value = "titleVideogame", required = false) String title,
            @RequestParam(value = "priceVideogame", required = false) Double maxPrice,
            @RequestParam(value = "releaseDateVideogame", required = false) String releaseAfter,
            @RequestParam(value = "platforms", required = false) String platformName,
            @RequestParam(value = "genres", required = false)String genre) {



        return indexService.search(title, maxPrice, releaseAfter,platformName,genre);
    }

}
