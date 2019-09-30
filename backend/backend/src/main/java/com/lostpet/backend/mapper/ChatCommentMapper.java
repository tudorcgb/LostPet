package com.lostpet.backend.mapper;


import com.lostpet.backend.chat.model.ChatMessage;
import com.lostpet.backend.entity.Chat;
import com.lostpet.backend.entity.ChatComment;
import com.tudor.dto.ChatCommentDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ChatCommentMapper {

    ChatCommentDTO toDto(ChatComment chatComment);

    ChatComment toEntity(ChatCommentDTO chatCommentDTO);

    @Mapping(target = "message", source = "content")
    ChatComment toEntity(ChatMessage chatMessage);


}
