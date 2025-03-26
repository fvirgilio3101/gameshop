package it.ecubit.gameshop.mappers;

import it.ecubit.gameshop.dto.UserDTO;
import it.ecubit.gameshop.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDTO userToUserDTO(User user);

    User userDTOToUser(UserDTO dto);
}
