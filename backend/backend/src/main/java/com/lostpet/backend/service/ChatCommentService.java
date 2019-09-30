package com.lostpet.backend.service;


import com.lostpet.backend.chat.model.ChatMessage;
import com.tudor.dto.ChatCommentDTO;

import java.util.List;


public interface ChatCommentService {


    List<ChatCommentDTO> findByChatId(Long id);

    void save(ChatMessage message);
}
