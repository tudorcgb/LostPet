package com.lostpet.backend.rest;


import com.tudor.dto.ChatCommentDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/chatComment")
public interface ChatCommentApi {

    @GetMapping("/{id}")
    List<ChatCommentDTO> findByChatId(@PathVariable("id") Long id );

    @DeleteMapping("/{id}")
    void delete(@PathVariable("id") Long id );

    @PostMapping("/save")
    Long save(@RequestBody ChatCommentDTO chatCommentDTO);
}
