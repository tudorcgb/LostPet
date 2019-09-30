package com.lostpet.backend.mapper;

import com.lostpet.backend.chat.model.ChatMessage;
import com.lostpet.backend.entity.ChatComment;
import com.tudor.dto.ChatCommentDTO;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2019-07-18T20:15:08+0300",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 9.0.1 (Oracle Corporation)"
)
@Component
public class ChatCommentMapperImpl implements ChatCommentMapper {

    @Override
    public ChatCommentDTO toDto(ChatComment chatComment) {
        if ( chatComment == null ) {
            return null;
        }

        ChatCommentDTO chatCommentDTO = new ChatCommentDTO();

        chatCommentDTO.setId( chatComment.getId() );
        chatCommentDTO.setIdChat( chatComment.getIdChat() );
        chatCommentDTO.setMessage( chatComment.getMessage() );
        chatCommentDTO.setDate( chatComment.getDate() );
        chatCommentDTO.setSenderUsername( chatComment.getSenderUsername() );

        return chatCommentDTO;
    }

    @Override
    public ChatComment toEntity(ChatCommentDTO chatCommentDTO) {
        if ( chatCommentDTO == null ) {
            return null;
        }

        ChatComment chatComment = new ChatComment();

        chatComment.setId( chatCommentDTO.getId() );
        chatComment.setIdChat( chatCommentDTO.getIdChat() );
        chatComment.setMessage( chatCommentDTO.getMessage() );
        chatComment.setDate( chatCommentDTO.getDate() );
        chatComment.setSenderUsername( chatCommentDTO.getSenderUsername() );

        return chatComment;
    }

    @Override
    public ChatComment toEntity(ChatMessage chatMessage) {
        if ( chatMessage == null ) {
            return null;
        }

        ChatComment chatComment = new ChatComment();

        chatComment.setMessage( chatMessage.getContent() );
        chatComment.setIdChat( chatMessage.getIdChat() );
        chatComment.setDate( chatMessage.getDate() );
        chatComment.setSenderUsername( chatMessage.getSenderUsername() );

        return chatComment;
    }
}
