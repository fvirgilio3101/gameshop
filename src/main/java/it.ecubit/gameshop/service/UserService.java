package it.ecubit.gameshop.service;

import it.ecubit.gameshop.dto.UserDTO;
import it.ecubit.gameshop.entity.User;

import java.util.List;

public interface UserService {

     List<UserDTO> readAll();

     UserDTO read(UserDTO dto);

     UserDTO save(UserDTO dto);

     void deleteUser(UserDTO dtos);

     void deleteAll(List<UserDTO> users);

     UserDTO registerUser(UserDTO dto);
}
