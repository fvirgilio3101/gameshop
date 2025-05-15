package it.ecubit.gameshop.service;

import it.ecubit.gameshop.document.PlatformDocument;
import it.ecubit.gameshop.document.VideogameDocument;
import it.ecubit.gameshop.dto.GenreDTO;
import it.ecubit.gameshop.dto.VideogameDTO;
import it.ecubit.gameshop.entity.Genre;
import it.ecubit.gameshop.entity.Platform;
import it.ecubit.gameshop.entity.Videogame;
import it.ecubit.gameshop.mappers.VideogameMapper;
import it.ecubit.gameshop.repository.*;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
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
    private PlatformRepository platformRepository;

    @Autowired
    private PlatformDocumentRepository platformDocumentRepository;

    @Autowired
    private VideogameDocumentRepository documentRepository;

    @Override
    public List<VideogameDTO> readAll() {
        log.info("Avvio lettura di tutti i videogiochi");
        try {
            List<Videogame> videogames = this.videogameRepository.findAll();
            log.info("Lettura completata, trovati {} videogiochi", videogames.size());
            List <VideogameDTO> dtos = videogames.stream()
                    .map(videogameMapper::videogameToVideogameDTO)
                    .collect(Collectors.toList());
            return dtos;
        } catch (Exception e) {
            log.error("Errore durante la lettura dei videogiochi", e);
            throw new RuntimeException("Errore durante il caricamento dei videogiochi");
        }
    }

    @Override
    public VideogameDTO read(VideogameDTO dto) {
        log.info("Avvio lettura del videogioco con id {}", dto.getIdVideogame());
        try {
            Videogame foundVideogame = this.videogameRepository.getReferenceById(dto.getIdVideogame());
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
                    .toList();
        } catch (Exception e) {
            log.error("Errore durante la lettura dei videogiochi con filtro", e);
            throw new RuntimeException("Errore durante la lettura dei videogiochi con filtro");
        }
    }


    @Override
    public VideogameDTO save(VideogameDTO dto) {
        log.info("Avvio salvataggio del videogioco con id {}", dto.getIdVideogame());
        try {
            Videogame videogame = this.videogameMapper.videogameDTOToVideogame(dto);
            if (videogame.getPlatforms() != null) {
                videogame.getPlatforms().forEach(platform -> platform.setVideogame(videogame));
            }
            Videogame savedVideogame = this.videogameRepository.save(videogame);

            VideogameDocument doc = new VideogameDocument();

            doc.setIdVideogame(savedVideogame.getIdVideogame());
            doc.setTitleVideogame(savedVideogame.getTitleVideogame());
            doc.setDescVideogame(savedVideogame.getDescVideogame());
            doc.setPriceVideogame(savedVideogame.getPriceVideogame());
            doc.setRating(savedVideogame.getAverageRating());
            doc.setReleaseDateVideogame(savedVideogame.getReleaseDateVideogame());
            List<PlatformDocument> platformDocuments = savedVideogame.getPlatforms()
                    .stream()
                    .map(platform -> {
                        PlatformDocument pd = new PlatformDocument();
                        pd.setIdPlatform(platform.getIdPlatform());
                        pd.setName(platform.getName());
                        pd.setAbbreviation(platform.getAbbreviation());
                        return pd;
                    })
                    .toList();

            doc.setPlatforms(platformDocuments);


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
        return this.videogameMapper.videogameToVideogameDTO(videogame);

    }

    @Override
    public VideogameDTO addPlatforms(List<Long> platformIds, Long id) {
        Videogame videogame = this.videogameRepository.getReferenceById(id);
        List<Platform> platforms = this.platformRepository.findAllById(platformIds);
        videogame.getPlatforms().addAll(platforms);

        this.videogameRepository.save(videogame);

        VideogameDocument doc = this.documentRepository.findById(id).get();
        List<PlatformDocument> platformDocuments = (List<PlatformDocument>) this.platformDocumentRepository.findAllById(platformIds);
        doc.getPlatforms().addAll(platformDocuments);

        this.documentRepository.save(doc);

        return this.videogameMapper.videogameToVideogameDTO(videogame);

    }

    @Override
    public List<VideogameDTO> getTopGamesByGenre(String genre) {
        List<VideogameDTO> dtos = this.videogameRepository.findTopGamesByGenre(genre)
                .stream()
                .map(videogameMapper::videogameToVideogameDTO)
                .toList();
        return dtos;
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

