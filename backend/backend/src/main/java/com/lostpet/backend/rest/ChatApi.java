package com.lostpet.backend.rest;

import com.tudor.dto.ChatDTO;
import com.tudor.dto.CommentDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/chat")
public interface ChatApi {

    @GetMapping("/{id}")
    List<ChatDTO> findByUserId(@PathVariable("id") Long id );

    @DeleteMapping("/{id}")
    void delete(@PathVariable("id") Long id );

    @PostMapping("/save")
    Long save(@RequestBody ChatDTO chatDTO);
}
