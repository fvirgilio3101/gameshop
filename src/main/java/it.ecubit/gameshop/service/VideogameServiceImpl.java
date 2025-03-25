package it.ecubit.gameshop.service;

import it.ecubit.gameshop.dto.VideogameDTO;
import it.ecubit.gameshop.entity.Videogame;
import it.ecubit.gameshop.mappers.UserMapper;
import it.ecubit.gameshop.mappers.VideogameMapper;
import it.ecubit.gameshop.repository.VideogameRepository;
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

    private VideogameMapper videogameMapper;

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
            Videogame savedVideogame = this.videogameRepository.save(this.videogameMapper.videogameDTOToVideogame(dto));
            log.info("Videogame con id {} salvato correttamente", savedVideogame.getIdVideogame());
            return this.videogameMapper.videogameToVideogameDTO(savedVideogame);
        } catch (Exception e) {
            log.error("Errore durante il salvataggio del videogioco", e);
            throw new RuntimeException("Errore durante il salvataggio del videogioco", e);
        }
    }


    @Override
    public void deleteVideogame(VideogameDTO dtos) {
        log.info("Avvio cancellazione del videogioco con id {}", dtos.getIdVideogame());
        try {
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

