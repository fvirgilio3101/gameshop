package it.ecubit.gameshop.service;

import it.ecubit.gameshop.dto.RatingDTO;

public interface RatingService {

    RatingDTO rateVideogame(Long videogameId, Long userId, Double value);
}
