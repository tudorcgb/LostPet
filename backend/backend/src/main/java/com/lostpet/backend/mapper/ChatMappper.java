package com.lostpet.backend.mapper;


import com.lostpet.backend.entity.Chat;
import com.tudor.dto.ChatDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ChatMappper {

    ChatDTO toDto(Chat chat);

    Chat toEntity(ChatDTO chatDTO);

}
