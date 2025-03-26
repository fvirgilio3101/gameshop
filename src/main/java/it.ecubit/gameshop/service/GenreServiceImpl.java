package it.ecubit.gameshop.service;

import it.ecubit.gameshop.dto.GenreDTO;
import it.ecubit.gameshop.entity.Genre;
import it.ecubit.gameshop.entity.Order;
import it.ecubit.gameshop.mappers.GenreMapper;
import it.ecubit.gameshop.repository.GenreRepository;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreServiceImpl implements GenreService{

    private static final Logger log = LoggerFactory.getLogger(GenreServiceImpl.class);

    @Autowired
    private GenreMapper genreMapper;

    @Autowired
    private GenreRepository genreRepository;

    @Override
    public List<GenreDTO> readAll() {
        log.info("Avvio raccolta di tutti i generi di videogame ...");
        try {
            List<GenreDTO> genres = this.genreRepository.findAll().stream()
                    .map(genreMapper::genreToGenreDTO)
                    .toList();
            log.info("Recuperati correttamente tutti i generi");
            return genres;
        }catch (Exception e){
            log.error("Errore durante la lettura di tutti i generi di videogame", e);
            throw new RuntimeException("Errore durante la lettura di tutti i generi di videogame", e);
        }

    }

    @Override
    public GenreDTO read(GenreDTO dto) {
        log.info("Avvio lettura del genere con ID: " + dto.getIdGenre());
        try{
            Genre entity = this.genreRepository.getReferenceById(dto.getIdGenre());
            log.info("Genere trovato correttamente" );
            return this.genreMapper.genreToGenreDTO(entity);
        }catch (Exception e){
            throw new EntityNotFoundException("Genere non trovato");
        }
    }

    @Override
    public GenreDTO save(GenreDTO dto) {
        try{
            Genre genreToSave = this.genreMapper.genreDTOtoGenre(dto);
            log.info("Prima del salvataggio: {}", genreToSave);
            Genre entity = this.genreRepository.save(genreToSave);
            log.info("Dopo il salvataggio: {}", entity);
            return this.genreMapper.genreToGenreDTO(entity);
        }catch (
        DataIntegrityViolationException e) {
            log.error("Violazione dei vincoli di integrità per il genere: {} ", dto, e);
            throw new IllegalArgumentException("Dati non validi per il genere", e);
        } catch (Exception e) {
            log.error("Errore durante il salvataggio del genere: {}", dto, e);
            throw new RuntimeException("Errore durante il salvataggio del genere", e);
        }
    }

    @Override
    public void delete(GenreDTO dto) {
        log.info("Avvio eliminazione del genere");
        try{
            this.genreRepository.delete(this.genreMapper.genreDTOtoGenre(dto));
            log.info("Genere eliminato correttamente");
        }catch (EmptyResultDataAccessException e) {
            log.warn("Tentativo di eliminare un genere non esistente");
            throw new EntityNotFoundException("Si è verificato un problema durante l'eliminazione del genere");
        } catch (Exception e) {
            log.error("Errore durante l'eliminazione del genere", e);
            throw new RuntimeException("Errore durante l'eliminazione del genere", e);
        }
    }

    @Override
    public void deleteAll() {
        log.info("Avvio eliminazione di tutti i generi");
        try{
            this.genreRepository.deleteAll();
        }catch(Exception e){
            log.error("Errore durante l'eliminazione dei generi", e);
            throw new RuntimeException("Errore durante l'eliminazione di tutti i generi", e);
        }
    }
}
