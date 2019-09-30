package com.lostpet.backend.rest.impl;

import com.lostpet.backend.rest.ChatApi;
import com.lostpet.backend.service.ChatService;
import com.tudor.dto.ChatDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.ws.RespectBinding;
import java.util.List;
@RestController
public class ChatRestController implements ChatApi {


    private ChatService chatService;

    @Autowired
    public ChatRestController(ChatService chatService) {
        this.chatService = chatService;
    }


    @Override
    public List<ChatDTO> findByUserId(@PathVariable("id") Long id) {
        return chatService.findByUser(id);
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Long save(@RequestBody ChatDTO chatDTO) {
        return chatService.save(chatDTO);
    }
}
