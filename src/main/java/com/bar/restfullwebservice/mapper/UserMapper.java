package com.bar.restfullwebservice.mapper;

import com.bar.restfullwebservice.dto.UserDTO;
import com.bar.restfullwebservice.entity.User;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Created by Htay Hlaing Aung on 9/22/2021
 */

@Mapper(componentModel = "spring")
public interface UserMapper extends BaseMapper<UserDTO, User> {

    @Override
    UserDTO toDTO(User user);

    @Override
    User toEntity(UserDTO userDTO);

    @Override
    List<UserDTO> toDTOs(List<User> users);

    @Override
    List<User> toEntities(List<UserDTO> userDTOS);
}
