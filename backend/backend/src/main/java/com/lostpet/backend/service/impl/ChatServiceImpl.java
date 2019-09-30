package com.lostpet.backend.service.impl;

import com.lostpet.backend.entity.Chat;
import com.lostpet.backend.entity.User;
import com.lostpet.backend.mapper.ChatMapper;
import com.lostpet.backend.repository.ChatRepository;
import com.lostpet.backend.service.ChatService;
import com.tudor.dto.ChatDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class ChatServiceImpl implements ChatService {

    private ChatRepository chatRepository;
    private ChatMapper chatMapper;

    @Autowired
    public ChatServiceImpl(ChatRepository chatRepository, ChatMapper chatMapper) {
        this.chatRepository = chatRepository;
        this.chatMapper = chatMapper;
    }

    @Override
    public List<ChatDTO> findByUser(Long id) {
        //todo s-ar putea sa nu fie ok
        User send = new User();
        send.setId(id);
        User dest = new User();
        List<Chat> chats = chatRepository.findByIsSendOrReceive(id);
        List<ChatDTO> chatDTOS = chats
                .stream()
                .map(chatMapper::toDto)
                .collect(Collectors.toList());;
        return chatDTOS;
    }

    @Override
    public Long save(ChatDTO chatDTO) {
        chatDTO.setChatName(chatDTO.getSender().getUsername()+"user" + chatDTO.getDest().getUsername());
        //unique number
        chatDTO.setMultiplication(chatDTO.getSender().getId() * chatDTO.getDest().getId() * 17);
        Chat chat = chatRepository.save(chatMapper.toEntity(chatDTO));
        return chat.getId();
    }
}
