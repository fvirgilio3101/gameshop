package it.ecubit.gameshop.mappers;

import it.ecubit.gameshop.dto.RatingDTO;

import it.ecubit.gameshop.entity.Rating;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RatingMapper {

    RatingDTO ratingToRatingDTO(Rating rating);

    Rating ratingDTOToRating(RatingDTO dto);
}
