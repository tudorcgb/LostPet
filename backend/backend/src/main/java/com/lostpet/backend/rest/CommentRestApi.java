package com.lostpet.backend.rest;


import com.tudor.dto.CommentDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/comments")
public interface CommentRestApi {

    @GetMapping("/{id}")
    List<CommentDTO> findByListingId(@PathVariable("id") Long id );

    @DeleteMapping("/{id}")
    void delete(@PathVariable("id") Long id );

    @PostMapping("/save")
    Long save(@RequestBody CommentDTO commentDTO);



}
