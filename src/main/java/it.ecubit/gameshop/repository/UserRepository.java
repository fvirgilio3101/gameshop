package it.ecubit.gameshop.repository;

import it.ecubit.gameshop.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    boolean existsByUsername(String username);  // Verifica se l'username è già presente
    boolean existsByEmail(String email);        // Verifica se l'email è già presente
}
