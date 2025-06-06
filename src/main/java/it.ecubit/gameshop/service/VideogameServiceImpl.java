package it.ecubit.gameshop.service;

import it.ecubit.gameshop.document.VideogameDocument;
import it.ecubit.gameshop.dto.VideogameDTO;
import it.ecubit.gameshop.entity.Genre;
import it.ecubit.gameshop.entity.Videogame;
import it.ecubit.gameshop.mappers.VideogameMapper;
import it.ecubit.gameshop.repository.GenreRepository;
import it.ecubit.gameshop.repository.VideogameDocumentRepository;
import it.ecubit.gameshop.repository.VideogameRepository;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VideogameServiceImpl implements VideogameService {
    private static final Logger log = LoggerFactory.getLogger(VideogameServiceImpl.class);

    @Autowired
    private VideogameRepository videogameRepository;

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private VideogameMapper videogameMapper;


    @Autowired
    private VideogameDocumentRepository documentRepository;

    @Override
    public List<VideogameDTO> readAll() {
        log.info("Avvio lettura di tutti i videogiochi");
        try {
            List<Videogame> videogames = this.videogameRepository.findAll();
            log.info("Lettura completata, trovati {} videogiochi", videogames.size());
            List<VideogameDTO> dtos = videogames.stream()
                    .map(videogameMapper::videogameToVideogameDTO)
                    .collect(Collectors.toList());
            return dtos;
        } catch (Exception e) {
            log.error("Errore durante la lettura dei videogiochi", e);
            throw new RuntimeException("Errore durante il caricamento dei videogiochi");
        }
    }

    @Override
    public VideogameDTO read(Long id) {
        try {
            Videogame foundVideogame = this.videogameRepository.getReferenceById(id);
            return this.videogameMapper.videogameToVideogameDTO(foundVideogame);
        } catch (Exception e) {
            log.error("Errore durante la lettura dei videogiochi", e);
            throw new EntityNotFoundException("Videogame non trovato");
        }
    }


    @Override
    public List<VideogameDTO> readByFilter(VideogameDTO dto) {
        log.info("Avvio lettura dei videogiochi con filtro: {}", dto);
        try {
            List<Videogame> filteredVideogames = this.videogameRepository.findAll(Example.of(this.videogameMapper.videogameDTOToVideogame(dto)));
            log.info("Lettura completata, trovati {} videogiochi con il filtro", filteredVideogames.size());
            return filteredVideogames.stream()
                    .map(videogameMapper::videogameToVideogameDTO)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            log.error("Errore durante la lettura dei videogiochi con filtro", e);
            throw new RuntimeException("Errore durante la lettura dei videogiochi con filtro");
        }
    }

    public List<Videogame> getVideogameFromDTO(VideogameDTO dto){
        return this.videogameRepository.findAll(Example.of(this.videogameMapper.videogameDTOToVideogame(dto)));
    }


    @Override
    public VideogameDTO save(VideogameDTO dto) {
        log.info("Avvio salvataggio del videogioco con id {}", dto.getIdVideogame());
        try {
            Videogame videogame = this.videogameMapper.videogameDTOToVideogame(dto);

            if (dto.getGenres() != null) {
                List<Genre> managedGenres = dto.getGenres().stream()
                        .map(genreDTO -> genreRepository.findById(genreDTO.getIdGenre())
                                .orElseThrow(() -> new EntityNotFoundException("Genre not found with id: " + genreDTO.getIdGenre())))
                        .collect(Collectors.toList());
                videogame.setGenres(managedGenres);
            }

            Videogame savedVideogame = this.videogameRepository.save(videogame);

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


            this.documentRepository.save(doc);

            log.info("Videogame con id {} salvato correttamente", savedVideogame.getIdVideogame());
            return this.videogameMapper.videogameToVideogameDTO(savedVideogame);
        } catch (Exception e) {
            log.error("Errore durante il salvataggio del videogioco", e);
            throw new RuntimeException("Errore durante il salvataggio del videogioco", e);
        }
    }

    @Override
    public VideogameDTO addGenre(List<Long> genreIds, Long id) {
        Videogame videogame = this.videogameRepository.getReferenceById(id);
        List<Genre> genres = this.genreRepository.findAllById(genreIds);
        videogame.getGenres().addAll(genres);
        this.videogameRepository.save(videogame);
        List<String> genresDoc = genres.stream()
                .map(Genre::getName)
                .collect(Collectors.toList());
        VideogameDocument doc = this.documentRepository.findById(id).get();
        doc.setGenres(genresDoc);
        this.documentRepository.save(doc);
        return this.videogameMapper.videogameToVideogameDTO(videogame);

    }

    @Override
    public List<VideogameDTO> getTopGamesByGenre(String genre) {
        List<VideogameDTO> dtos = this.videogameRepository.findTopGamesByGenre(genre)
                .stream()
                .map(videogameMapper::videogameToVideogameDTO)
                .collect(Collectors.toList());
        return dtos;
    }

    @Override
    public List<VideogameDTO> getBestSellingGamesFromDb() {
        log.info("Avvio ricerca dei videogiochi più venduti da DB");
        try {
            List<Videogame> videogames = videogameRepository.findBySalesGreaterThan(10);
            return videogames.stream()
                    .map(videogameMapper::videogameToVideogameDTO)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            log.error("Errore durante il recupero dei videogiochi più venduti", e);
            throw new RuntimeException("Errore durante il recupero dei videogiochi più venduti");
        }
    }

    @Override
    public void deleteVideogame(VideogameDTO dtos) {
        log.info("Avvio cancellazione del videogioco con id {}", dtos.getIdVideogame());
        try {
            VideogameDocument doc = this.documentRepository.findById(dtos.getIdVideogame()).get();
            this.documentRepository.delete(doc);

            this.videogameRepository.delete(this.videogameMapper.videogameDTOToVideogame(dtos));
            log.info("Videogame con id {} cancellato correttamente", dtos.getIdVideogame());
        } catch (Exception e) {
            log.error("Errore durante la cancellazione del videogioco con id {}", dtos.getIdVideogame(), e);
            throw new RuntimeException("Errore durante la cancellazione del videogioco");
        }
    }


    @Override
    public void deleteAll() {
        log.info("Avvio cancellazione di tutti i videogiochi");
        try {
            this.videogameRepository.deleteAll();
            log.info("Tutti i videogiochi sono stati cancellati correttamente");
        } catch (Exception e) {
            log.error("Errore durante la cancellazione di tutti i videogiochi", e);
            throw new RuntimeException("Errore durante la cancellazione di tutti i videogiochi", e);
        }
    }



}
