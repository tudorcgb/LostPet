package com.lostpet.backend.rest.impl;

import com.lostpet.backend.rest.ChatCommentApi;
import com.lostpet.backend.service.ChatCommentService;
import com.tudor.dto.ChatCommentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ChatCommentRestController implements ChatCommentApi {

    private ChatCommentService chatCommentService;


    @Autowired
    public ChatCommentRestController(ChatCommentService chatCommentService) {
        this.chatCommentService = chatCommentService;
    }


    @Override
    public List<ChatCommentDTO> findByChatId(@PathVariable("id") Long id) {
        return chatCommentService.findByChatId(id);
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Long save(ChatCommentDTO chatCommentDTO) {
        return null;
    }
}
