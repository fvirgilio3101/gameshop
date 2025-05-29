package it.ecubit.gameshop.service;

import it.ecubit.gameshop.dto.RatingDTO;
import it.ecubit.gameshop.entity.Rating;
import it.ecubit.gameshop.entity.User;
import it.ecubit.gameshop.entity.Videogame;
import it.ecubit.gameshop.mappers.RatingMapper;
import it.ecubit.gameshop.repository.RatingRepository;
import it.ecubit.gameshop.repository.UserRepository;
import it.ecubit.gameshop.repository.VideogameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RatingServiceImpl implements RatingService{

    @Autowired
    private RatingRepository ratingRepository;

    @Autowired
    private VideogameRepository videogameRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RatingMapper ratingMapper;


    @Override
    public RatingDTO rateVideogame(Long videogameId, Long userId, Double value, String text) {

        Videogame videogame = videogameRepository.getReferenceById(videogameId);

        User user = userRepository.getReferenceById(userId);

        Rating rating = ratingRepository.findByVideogameAndUser(videogame, user);

        if(rating == null){
            rating = new Rating();
        }

        rating.setVideogame(videogame);
        rating.setUser(user);
        rating.setValue(value);
        rating.setText(text);

        this.ratingRepository.save(rating);

        return this.ratingMapper.ratingToRatingDTO(rating);
    }
}
