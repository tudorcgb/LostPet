package com.lostpet.backend.service.impl;

import com.lostpet.backend.chat.model.ChatMessage;
import com.lostpet.backend.entity.ChatComment;
import com.lostpet.backend.mapper.ChatCommentMapper;
import com.lostpet.backend.repository.ChatCommentRepository;
import com.lostpet.backend.service.ChatCommentService;
import com.tudor.dto.ChatCommentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChatCommentServiceImpl implements ChatCommentService {


    private ChatCommentRepository chatCommentRepository;
    private ChatCommentMapper chatCommentMapper;

    @Autowired
    public ChatCommentServiceImpl(ChatCommentRepository chatCommentRepository, ChatCommentMapper chatCommentMapper) {
        this.chatCommentRepository = chatCommentRepository;
        this.chatCommentMapper = chatCommentMapper;
    }


    @Override
    public List<ChatCommentDTO> findByChatId(Long id) {

        List<ChatComment> chatComments = chatCommentRepository.findByIdChatOrderByDateAsc(id);
        List<ChatCommentDTO> chatCommentDTOS = chatComments
                .stream()
                .map(chatCommentMapper::toDto)
                .collect(Collectors.toList());
        return chatCommentDTOS;
    }

    @Override
    public void save(ChatMessage message) {

        ChatComment chatComment = chatCommentMapper.toEntity(message);
        chatCommentRepository.save(chatComment);

    }

}
