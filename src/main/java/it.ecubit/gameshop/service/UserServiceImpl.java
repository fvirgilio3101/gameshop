package it.ecubit.gameshop.service;

import it.ecubit.gameshop.entity.User;
import it.ecubit.gameshop.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> readAll() {
        log.info("Avvio lettura di tutti gli utenti...");
        try {
            List<User> users = this.userRepository.findAll();
            log.info("Lettura completata. Trovati {} utenti.", users.size());
            return users;
        } catch (Exception e) {
            log.error("Errore durante la lettura di tutti gli utenti", e);
            throw new RuntimeException("Errore durante il recupero degli utenti", e);
        }
    }

    @Override
    public User read(User user) {
        log.info("Avvio lettura utente con ID {}", user.getIdUser());
        try {
            User foundUser = this.userRepository.getReferenceById(user.getIdUser());
            log.info("Utente con ID {} trovato: {}", user.getIdUser(), foundUser);
            return foundUser;
        } catch (Exception e) {
            log.error("Errore durante la lettura dell'utente con ID {}", user.getIdUser(), e);
            throw new EntityNotFoundException("Utente con ID " + user.getIdUser() + " non trovato",e);
        }
    }

    @Override
    public User save(User user) {
        log.info("Avvio salvataggio utente: {}", user);
        try {
            User savedUser = this.userRepository.save(user);
            log.info("Utente salvato con successo: {}", savedUser);
            return savedUser;
        } catch (DataIntegrityViolationException e) {
            log.error("Violazione dei vincoli di integrità per l'utente: {}", user, e);
            throw new IllegalArgumentException("Dati non validi per l'utente", e);
        } catch (Exception e) {
            log.error("Errore durante il salvataggio dell'utente: {}", user, e);
            throw new RuntimeException("Errore durante il salvataggio dell'utente", e);
        }
    }

    @Override
    public void deleteUser(User user) {
        log.info("Avvio eliminazione utente con ID {}", user.getIdUser());
        try {
            this.userRepository.delete(user);
            log.info("Utente con ID {} eliminato con successo", user.getIdUser());
        } catch (EmptyResultDataAccessException e) {
            log.warn("Tentativo di eliminare un utente non esistente con ID {}", user.getIdUser());
            throw new EntityNotFoundException("L'utente con ID " + user.getIdUser() + " non esiste");
        } catch (Exception e) {
            log.error("Errore durante l'eliminazione dell'utente con ID {}", user.getIdUser(), e);
            throw new RuntimeException("Errore durante l'eliminazione dell'utente", e);
        }
    }

    @Override
    public void deleteAll(List<User> users) {
        log.info("Avvio eliminazione di {} utenti...", users != null ? users.size() : 0);
        try {
            if (users == null || users.isEmpty()) {
                log.warn("La lista degli utenti da eliminare è vuota o nulla");
                throw new IllegalArgumentException("La lista degli utenti da eliminare è vuota o nulla");
            }
            this.userRepository.deleteAll(users);
            log.info("Eliminazione completata. {} utenti rimossi.", users.size());
        } catch (Exception e) {
            log.error("Errore durante l'eliminazione degli utenti", e);
            throw new RuntimeException("Errore durante l'eliminazione di tutti gli utenti", e);
        }
    }
}
