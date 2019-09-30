package com.lostpet.backend.mapper;


import com.lostpet.backend.entity.User;
import com.tudor.dto.NameIdDTO;
import com.tudor.dto.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface UserMapper {

    User toEntity(UserDTO userDTO);

    //todo no Role mapping

    @Mapping(target = "password", ignore = false)
    UserDTO toDto(User user);

    @Mapping(target = "name", source = "username")
    NameIdDTO toNameIdDto(User user);
}
