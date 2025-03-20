package it.ecubit.gameshop.service;

import it.ecubit.gameshop.entity.User;

import java.util.List;

public interface UserService {

     List<User> readAll();

     User read(User user);

     User save(User user);

     void deleteUser(User user);

     void deleteAll(List<User> users);
}
