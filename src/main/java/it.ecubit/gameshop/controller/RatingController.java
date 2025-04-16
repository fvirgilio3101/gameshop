package it.ecubit.gameshop.controller;

import it.ecubit.gameshop.dto.RatingDTO;
import it.ecubit.gameshop.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/rating")
@CrossOrigin(origins = "http://localhost:4200")
public class RatingController {

    @Autowired
    private RatingService ratingService;

    @PostMapping("/{videogameId}/rate")
    public RatingDTO rateVideogame(
            @PathVariable Long videogameId,
            @RequestParam Long userId,
            @RequestParam Double value
    ) {
        return ratingService.rateVideogame(videogameId, userId, value);
    }
}
