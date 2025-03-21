package it.ecubit.gameshop.service;

import it.ecubit.gameshop.entity.Videogame;
import it.ecubit.gameshop.repository.VideogameRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

@Service
public class VideogameServiceImpl implements VideogameService {
    private static final Logger log = LoggerFactory.getLogger(VideogameServiceImpl.class);

    @Autowired
    private VideogameRepository videogameRepository;

    @Override
    public List<Videogame> readAll() {
        log.info("Avvio lettura di tutti i videogiochi");
        try {
            List<Videogame> videogames = this.videogameRepository.findAll();
            log.info("Lettura completata, trovati {} videogiochi", videogames.size());
            return videogames;
        } catch (Exception e) {
            log.error("Errore durante la lettura dei videogiochi", e);
            throw new RuntimeException("Errore durante il caricamento dei videogiochi");
        }
    }

    @Override
    public Videogame read(Videogame videogame) {
        log.info("Avvio lettura del videogioco con id {}", videogame.getIdVideogame());
        try {
            Videogame foundVideogame = this.videogameRepository.getReferenceById(videogame.getIdVideogame());
            return foundVideogame;
        } catch (Exception e) {
            log.error("Errore durante la lettura dei videogiochi", e);
            throw new EntityNotFoundException("Videogame con id" + videogame.getIdVideogame() + "non trovato");
        }
    }


    @Override
    public List<Videogame> readByFilter(Videogame videogame) {
        log.info("Avvio lettura dei videogiochi con filtro: {}", videogame);
        try {
            List<Videogame> filteredVideogames = this.videogameRepository.findAll(Example.of(videogame));
            log.info("Lettura completata, trovati {} videogiochi con il filtro", filteredVideogames.size());
            return filteredVideogames;
        } catch (Exception e) {
            log.error("Errore durante la lettura dei videogiochi con filtro", e);
            throw new RuntimeException("Errore durante la lettura dei videogiochi con filtro");
        }
    }


    @Override
    public Videogame save(Videogame videogame) {
        log.info("Avvio salvataggio del videogioco con id {}", videogame.getIdVideogame());
        try {
            Videogame savedVideogame = this.videogameRepository.save(videogame);
            log.info("Videogame con id {} salvato correttamente", savedVideogame.getIdVideogame());
            return savedVideogame;
        } catch (Exception e) {
            log.error("Errore durante il salvataggio del videogioco", e);
            throw new RuntimeException("Errore durante il salvataggio del videogioco", e);
        }
    }


    @Override
    public void deleteVideogame(Videogame videogame) {
        log.info("Avvio cancellazione del videogioco con id {}", videogame.getIdVideogame());
        try {
            this.videogameRepository.delete(videogame);
            log.info("Videogame con id {} cancellato correttamente", videogame.getIdVideogame());
        } catch (Exception e) {
            log.error("Errore durante la cancellazione del videogioco con id {}", videogame.getIdVideogame(), e);
            throw new RuntimeException("Errore durante la cancellazione del videogioco con id " + videogame.getIdVideogame(), e);
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

