package com.lostpet.backend.repository;


import com.lostpet.backend.entity.Comment;
import com.lostpet.backend.entity.Listing;
import com.tudor.dto.CommentDTO;
import com.tudor.dto.ListingDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    //List<Comment> findByListing(Long listing);

    List<Comment> findByListingOrderByDate(Long listing);



}
