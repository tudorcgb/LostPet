package com.lostpet.backend.rest.impl;

import com.lostpet.backend.rest.CommentRestApi;
import com.lostpet.backend.service.CommentService;
import com.lostpet.backend.service.UserService;
import com.tudor.dto.CommentDTO;
import com.tudor.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class CommentRestController implements CommentRestApi {

    private CommentService commentService;
    private UserService userService;

    @Autowired
    public CommentRestController(CommentService commentService1, UserService userService) {
        this.commentService = commentService1;
        this.userService = userService;
    }

    @Override
    public List<CommentDTO> findByListingId(@PathVariable("id") Long id) {
//        ListingDTO listingDTO = new ListingDTO();
//        listingDTO.setId(id);
        List<CommentDTO> commentDTOS =  commentService.findByListing(id);;
        for(CommentDTO comment : commentDTOS){
            UserDTO userDTO = userService.findById(comment.getUser());
            if(userDTO != null) {
                comment.setUsername(userDTO.getUsername());
            }
        }
        return commentDTOS;
    }

    @Override
    public void delete(@PathVariable("id") Long id) {
        commentService.delete(id);
    }

    @Override
    public Long save(@RequestBody CommentDTO commentDTO) {


        return commentService.save(commentDTO);
    }
}
