package com.lostpet.backend.service;

import com.tudor.dto.ChatDTO;

import java.util.List;


public interface ChatService {

    List<ChatDTO> findByUser(Long id);

    Long save(ChatDTO chatDTO);
}
