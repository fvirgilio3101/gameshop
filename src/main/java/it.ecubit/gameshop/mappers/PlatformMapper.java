package it.ecubit.gameshop.mappers;

import it.ecubit.gameshop.dto.PlatformDTO;
import it.ecubit.gameshop.entity.Platform;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PlatformMapper {

    @Mapping(source = "videogame.idVideogame", target = "videogameId")
    PlatformDTO platformToPlatformDTO(Platform platform);

    @Mapping(source = "videogameId", target = "videogame.idVideogame")
    Platform platformDTOToPlatform(PlatformDTO dto);
}
