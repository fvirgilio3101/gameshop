package it.ecubit.gameshop.service;

import it.ecubit.gameshop.entity.User;
import it.ecubit.gameshop.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> readAll() {
        return this.userRepository.findAll();
    }

    @Override
    public User read(User user) {
        return this.userRepository.getReferenceById(user.getIdUser());
    }

    @Override
    public User save(User user) {
       return this.userRepository.save(user);
    }

    @Override
    public void deleteUser(User user) {
        this.userRepository.delete(user);
    }

    @Override
    public void deleteAll(List<User> users) {
        this.userRepository.deleteAll();
    }
}
