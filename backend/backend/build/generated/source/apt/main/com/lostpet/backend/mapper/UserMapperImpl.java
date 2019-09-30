package com.lostpet.backend.mapper;

import com.lostpet.backend.entity.User;
import com.tudor.dto.NameIdDTO;
import com.tudor.dto.UserDTO;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2019-07-18T20:15:08+0300",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 9.0.1 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public User toEntity(UserDTO userDTO) {
        if ( userDTO == null ) {
            return null;
        }

        User user = new User();

        user.setId( userDTO.getId() );
        user.setUsername( userDTO.getUsername() );
        user.setPassword( userDTO.getPassword() );
        user.setRole( userDTO.getRole() );
        user.setAddress( userDTO.getAddress() );
        user.setCreated( userDTO.getCreated() );
        user.setEmail( userDTO.getEmail() );
        user.setTelephone( userDTO.getTelephone() );
        user.setImgUrl( userDTO.getImgUrl() );

        return user;
    }

    @Override
    public UserDTO toDto(User user) {
        if ( user == null ) {
            return null;
        }

        UserDTO userDTO = new UserDTO();

        userDTO.setAddress( user.getAddress() );
        userDTO.setCreated( user.getCreated() );
        userDTO.setEmail( user.getEmail() );
        userDTO.setTelephone( user.getTelephone() );
        userDTO.setId( user.getId() );
        userDTO.setUsername( user.getUsername() );
        userDTO.setPassword( user.getPassword() );
        userDTO.setRole( user.getRole() );
        userDTO.setImgUrl( user.getImgUrl() );

        return userDTO;
    }

    @Override
    public NameIdDTO toNameIdDto(User user) {
        if ( user == null ) {
            return null;
        }

        NameIdDTO nameIdDTO = new NameIdDTO();

        nameIdDTO.setName( user.getUsername() );
        nameIdDTO.setId( user.getId() );

        return nameIdDTO;
    }
}
