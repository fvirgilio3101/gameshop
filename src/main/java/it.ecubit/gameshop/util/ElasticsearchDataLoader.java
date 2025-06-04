package it.ecubit.gameshop.util;

import it.ecubit.gameshop.document.VideogameDocument;
import it.ecubit.gameshop.dto.VideogameDTO;
import it.ecubit.gameshop.entity.Genre;
import it.ecubit.gameshop.entity.Videogame;
import it.ecubit.gameshop.mappers.VideogameMapper;
import it.ecubit.gameshop.service.GenreService;
import it.ecubit.gameshop.service.VideogameIndexService;
import it.ecubit.gameshop.service.VideogameService;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ElasticsearchDataLoader {

    private final VideogameIndexService indexService;

    private final VideogameService service;

    private final VideogameMapper videogameMapper;

    private final GenreService genreService;

    public ElasticsearchDataLoader(VideogameIndexService indexService, VideogameService service, VideogameMapper videogameMapper, GenreService genreService) {
        this.indexService = indexService;
        this.service = service;
        this.videogameMapper = videogameMapper;
        this.genreService = genreService;
    }


    @PostConstruct
    public void dataLoading(){

        System.out.println(indexService.findAll());
        if(this.indexService.count() == 0){
            System.out.println("Importing");
            List<VideogameDTO> entities = service.readAll();

            for(VideogameDTO dto : entities){

                Videogame videogame = this.videogameMapper.videogameDTOToVideogame(dto);

                if (dto.getGenres() != null) {
                    List<Genre> managedGenres = genreService.returnGenreListFromDTO(dto);
                    videogame.getGenres().addAll(managedGenres);
                }

                List<Videogame> videogames = this.service.getVideogameFromDTO(dto);

                if(!videogames.isEmpty()){
                    Videogame savedVideogame = videogames.get(0);

                    VideogameDocument doc = new VideogameDocument();

                    doc.setIdVideogame(savedVideogame.getIdVideogame());
                    doc.setTitleVideogame(savedVideogame.getTitleVideogame());
                    List<String> genresDoc = savedVideogame.getGenres().stream()
                            .map(Genre::getName)
                            .collect(Collectors.toList());
                    doc.setGenres(genresDoc);
                    doc.setDescVideogame(savedVideogame.getDescVideogame());
                    doc.setPriceVideogame(savedVideogame.getPriceVideogame());
                    doc.setRating(savedVideogame.getAverageRating());
                    doc.setReleaseDateVideogame(savedVideogame.getReleaseDateVideogame().getTime());
                    doc.setPlatforms(savedVideogame.getPlatform());
                    doc.setDiscount(savedVideogame.getDiscount());
                    doc.setDiscountedPrice(savedVideogame.getDiscountedPrice());
                    doc.setCoverImage(savedVideogame.getCoverImage());
                    doc.setBackgroundImage(savedVideogame.getBackgroundImage());
                    doc.setSales(savedVideogame.getSales());


                    this.indexService.save(doc);
                }


            }
        }
    }
}
