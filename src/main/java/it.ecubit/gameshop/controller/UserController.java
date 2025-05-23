package it.ecubit.gameshop.controller;

import it.ecubit.gameshop.dto.UserDTO;
import it.ecubit.gameshop.entity.User;
import it.ecubit.gameshop.mappers.UserMapper;
import it.ecubit.gameshop.repository.UserRepository;
import it.ecubit.gameshop.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("api/user")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    @Autowired
    private UserService service;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    private static final String UPLOAD_DIR = "uploads/profile_images";


    @GetMapping
    public List<UserDTO> readAll() {
        return this.service.readAll();
    }

    @PostMapping("/register")
    public UserDTO register(@RequestBody UserDTO toSave) {
        try {
            return this.service.registerUser(toSave);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PostMapping
    public UserDTO create(@RequestBody UserDTO toSave) {
        return this.service.save(toSave);
    }

    @PutMapping
    public UserDTO save(@RequestBody UserDTO toSave) {
        return this.service.save(toSave);
    }

    @DeleteMapping
    public void delete(@RequestBody UserDTO toDelete) {
        this.service.deleteUser(toDelete);
    }

    @GetMapping("/me")
    public ResponseEntity<UserDTO> getCurrentUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        if (username == null || username.isEmpty() || username.equals("anonymousUser")) {
            throw new EntityNotFoundException("Utente non autenticato");
        }

        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new EntityNotFoundException("Utente non trovato");
        }

        UserDTO dto = new UserDTO();
        dto.setIdUser(user.getIdUser());

        return ResponseEntity.ok(service.read(dto));
    }

    @PostMapping("/me/upload-profile-image")
    public ResponseEntity<?> uploadProfileImage(@RequestParam("file") MultipartFile file) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        if (username == null || username.equals("anonymousUser")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Utente non autenticato");
        }

        User user = userRepository.findByUsername(username);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Utente non trovato");
        }

        try {
            String originalFilename = file.getOriginalFilename();
            String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
            String fileName = "user_" + user.getIdUser() + extension;

            Path uploadPath = Paths.get(UPLOAD_DIR);
            System.out.println("Working dir: " + System.getProperty("user.dir"));
            System.out.println("Upload dir full path: " + uploadPath.toAbsolutePath());

            // crea la directory se non esiste
            Files.createDirectories(uploadPath);

            Path filePath = uploadPath.resolve(fileName);
            Files.write(filePath, file.getBytes());

            user.setProfileImage(fileName);
            userRepository.save(user);

            return ResponseEntity.ok("Immagine caricata con successo");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Errore durante l'upload");
        }
    }

    @GetMapping("/me/profile-image")
    public ResponseEntity<Resource> getProfileImage() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        if (username == null || username.equals("anonymousUser")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        User user = userRepository.findByUsername(username);
        if (user == null || user.getProfileImage() == null) {
            return ResponseEntity.notFound().build();
        }

        try {
            Path imagePath = Paths.get(UPLOAD_DIR).resolve(user.getProfileImage());
            Resource resource = new UrlResource(imagePath.toUri());

            if (!resource.exists()) {
                return ResponseEntity.notFound().build();
            }

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_TYPE, Files.probeContentType(imagePath))
                    .body(resource);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/me/update-credentials")
    public ResponseEntity<String> updateCredentials(@RequestBody UserDTO dto) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        if (username == null || username.isBlank() || username.equals("anonymousUser")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Utente non autenticato");
        }

        User user = userRepository.findByUsername(username);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Utente non trovato");
        }

        if (dto.getEmail() != null && !dto.getEmail().isBlank()) {
            user.setEmail(dto.getEmail());
        }

        if (dto.getPassword() != null && !dto.getPassword().isBlank()) {
            user.setPassword(passwordEncoder.encode(dto.getPassword()));
        }

        userRepository.save(user);
        return ResponseEntity.ok("Credenziali aggiornate");
    }

}
