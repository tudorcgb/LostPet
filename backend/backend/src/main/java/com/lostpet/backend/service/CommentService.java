package com.lostpet.backend.service;

import com.tudor.dto.CommentDTO;
import com.tudor.dto.ListingDTO;

import java.util.List;


public interface CommentService {

    List<CommentDTO> findByListing(Long listingDTO);

    Long save(CommentDTO commentDTO);

    void delete(Long id);
}
