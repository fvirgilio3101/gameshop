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

    @GetMapping("/filter")
    public List<VideogameDocument> getFilteredVideogames(
            @RequestParam(value = "titleVideogame", required = false) String title,
            @RequestParam(value = "priceVideogame", required = false) Double maxPrice,
            @RequestParam(value = "releaseDateVideogame", required = false) String releaseAfter) {

        Date releaseDate = null;
        if (releaseAfter != null && !releaseAfter.isEmpty()) {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                releaseDate = sdf.parse(releaseAfter);
            } catch (ParseException e) {
                System.out.println("Errore nel parsing della data: " + releaseAfter);
            }
        }

        return indexService.search(title, maxPrice, releaseDate);
    }
}
