package it.ecubit.gameshop.service;

import it.ecubit.gameshop.dto.UserDTO;
import it.ecubit.gameshop.entity.User;
import it.ecubit.gameshop.mappers.UserMapper;
import it.ecubit.gameshop.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
/*import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;*/
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public List<UserDTO> readAll() {
        log.info("Avvio lettura di tutti gli utenti...");
        try {
            List<User> users = this.userRepository.findAll();
            log.info("Lettura completata. Trovati {} utenti.", users.size());
            List<UserDTO> dtos = users.stream()
                    .map(userMapper::userToUserDTO)
                    .collect(Collectors.toList());

            return dtos;
        } catch (Exception e) {
            log.error("Errore durante la lettura di tutti gli utenti", e);
            throw new RuntimeException("Errore durante il recupero degli utenti", e);
        }
    }

    @Override
    public UserDTO read(UserDTO dto) {
        log.info("Avvio lettura utente con ID {}", dto.getIdUser());
        try {
            User user = this.userMapper.userDTOToUser(dto);
            User foundUser = this.userRepository.getReferenceById(user.getIdUser());
            log.info("Utente con ID {} trovato: {}", user.getIdUser(), foundUser);
            return this.userMapper.userToUserDTO(foundUser);
        } catch (Exception e) {
            log.error("Errore durante la lettura dell'utente con ID {}", dto.getIdUser(), e);
            throw new EntityNotFoundException("Utente con ID " + dto.getIdUser() + " non trovato",e);
        }
    }

    @Override
    public UserDTO save(UserDTO dto) {
        log.info("Avvio salvataggio utente: {}", dto);
        try {

            User savedUser = this.userRepository.save(this.userMapper.userDTOToUser(dto));
            log.info("Utente salvato con successo: {}", savedUser);

            return this.userMapper.userToUserDTO(savedUser);
        } catch (DataIntegrityViolationException e) {
            log.error("Violazione dei vincoli di integrità per l'utente: {}", dto, e);
            throw new IllegalArgumentException("Dati non validi per l'utente", e);
        } catch (Exception e) {
            log.error("Errore durante il salvataggio dell'utente: {}", dto, e);
            throw new RuntimeException("Errore durante il salvataggio dell'utente", e);
        }
    }

    @Override
    public void deleteUser(UserDTO dto) {
        log.info("Avvio eliminazione utente con ID {}", dto.getIdUser());
        try {
            User user = this.userMapper.userDTOToUser(dto);
            this.userRepository.delete(user);
            log.info("Utente con ID {} eliminato con successo", dto.getIdUser());
        } catch (EmptyResultDataAccessException e) {
            log.warn("Tentativo di eliminare un utente non esistente con ID {}", dto.getIdUser());
            throw new EntityNotFoundException("L'utente con ID " + dto.getIdUser() + " non esiste");
        } catch (Exception e) {
            log.error("Errore durante l'eliminazione dell'utente con ID {}", dto.getIdUser(), e);
            throw new RuntimeException("Errore durante l'eliminazione dell'utente", e);
        }
    }

    @Override
    public void deleteAll(List<UserDTO> dtos) {
        log.info("Avvio eliminazione di {} utenti...", dtos != null ? dtos.size() : 0);
        try {
            List<User> users = dtos.stream()
                    .map(userMapper::userDTOToUser)
                    .collect(Collectors.toList());
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

    @Override
    public UserDTO registerUser(UserDTO dto) {
        log.info("Avvio registrazione utente: {}", dto);

        if (userRepository.existsByUsername(dto.getUsername())) {
            log.error("Nome utente già esistente: {}", dto.getUsername());
            throw new IllegalArgumentException("Nome utente già in uso");
        }

        if (userRepository.existsByEmail(dto.getEmail())) {
            log.error("Email già in uso: {}", dto.getEmail());
            throw new IllegalArgumentException("Email già in uso");
        }

        String encryptedPassword = passwordEncoder.encode(dto.getPassword());
        dto.setPassword(encryptedPassword);


        try {
            if(dto.getRole() == null) {
                dto.setRole("ROLE_USER");
            }
            User savedUser = this.userRepository.save(this.userMapper.userDTOToUser(dto));
            log.info("Utente registrato con successo: {}", savedUser);

            /*UserDetails user = org.springframework.security.core.userdetails.User.builder()
                    .username(savedUser.getUsername())
                .password(passwordEncoder.encode(savedUser.getPassword()))
                    .roles("USER")
                    .build();
            new InMemoryUserDetailsManager(user);*/
            return this.userMapper.userToUserDTO(savedUser);
        } catch (Exception e) {
            log.error("Errore durante la registrazione dell'utente: {}", dto, e);
            throw new RuntimeException("Errore durante la registrazione dell'utente", e);
        }
    }


}
