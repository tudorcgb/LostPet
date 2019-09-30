package com.lostpet.backend.mapper;

import com.lostpet.backend.entity.Chat;
import com.lostpet.backend.entity.User;
import com.tudor.dto.ChatDTO;
import com.tudor.dto.UsernameIdDTO;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2019-07-18T20:15:08+0300",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 9.0.1 (Oracle Corporation)"
)
@Component
public class ChatMapperImpl implements ChatMapper {

    @Override
    public ChatDTO toDto(Chat chat) {
        if ( chat == null ) {
            return null;
        }

        ChatDTO chatDTO = new ChatDTO();

        chatDTO.setId( chat.getId() );
        chatDTO.setSender( userToUsernameIdDTO( chat.getSender() ) );
        chatDTO.setDest( userToUsernameIdDTO( chat.getDest() ) );
        chatDTO.setChatName( chat.getChatName() );
        chatDTO.setMultiplication( chat.getMultiplication() );

        return chatDTO;
    }

    @Override
    public Chat toEntity(ChatDTO chatDTO) {
        if ( chatDTO == null ) {
            return null;
        }

        Chat chat = new Chat();

        chat.setId( chatDTO.getId() );
        chat.setSender( usernameIdDTOToUser( chatDTO.getSender() ) );
        chat.setDest( usernameIdDTOToUser( chatDTO.getDest() ) );
        chat.setChatName( chatDTO.getChatName() );
        chat.setMultiplication( chatDTO.getMultiplication() );

        return chat;
    }

    protected UsernameIdDTO userToUsernameIdDTO(User user) {
        if ( user == null ) {
            return null;
        }

        UsernameIdDTO usernameIdDTO = new UsernameIdDTO();

        usernameIdDTO.setId( user.getId() );
        usernameIdDTO.setUsername( user.getUsername() );

        return usernameIdDTO;
    }

    protected User usernameIdDTOToUser(UsernameIdDTO usernameIdDTO) {
        if ( usernameIdDTO == null ) {
            return null;
        }

        User user = new User();

        user.setId( usernameIdDTO.getId() );
        user.setUsername( usernameIdDTO.getUsername() );

        return user;
    }
}
