package com.lostpet.backend.service.impl;

import com.lostpet.backend.mail.connection.ServerConnection;
import com.lostpet.backend.mapper.CommentMapper;
import com.lostpet.backend.repository.CommentRepository;
import com.lostpet.backend.service.CommentService;
import com.lostpet.backend.service.ListingService;
import com.lostpet.backend.service.UserService;
import com.tudor.dto.CommentDTO;
import com.tudor.dto.ListingDTO;
import com.tudor.dto.MailDTO;
import com.tudor.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeoutException;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    private CommentRepository commentRepository;
    private CommentMapper commentMapper;
    private UserService userService;
    private ListingService listingService;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository, CommentMapper commentMapper, UserService userService, ListingService listingService) {
        this.commentRepository = commentRepository;
        this.commentMapper = commentMapper;
        this.userService = userService;
        this.listingService = listingService;
    }


    @Override
    public List<CommentDTO> findByListing(Long listingDTO) {
        List<CommentDTO> commentDTOS = commentRepository.findByListingOrderByDate(listingDTO)
                .stream()
                .map(commentMapper::toDto)
                .collect(Collectors.toList());
        return commentDTOS;
    }

    @Override
    public Long save(CommentDTO commentDTO) {
        ListingDTO listind = listingService.findById(commentDTO.getListing());
        UserDTO listingUser = userService.findById(listind.getWriter().getId());
        if(!listingUser.getId().equals(commentDTO.getUser())) {
            MailDTO mailDTO = new MailDTO();
            mailDTO.setDest(listingUser.getEmail());
            mailDTO.createTitle(commentDTO.getUsername(), listind.getTitle());
            mailDTO.createMessage(commentDTO.getUsername(), listind.getTitle(), commentDTO);

            try {
                ServerConnection.getInstance().sendMessage(mailDTO);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (TimeoutException e) {
                e.printStackTrace();
            }
        }
        return commentRepository.save(commentMapper.toEntity(commentDTO)).getId();
    }

    @Override
    public void delete(Long id) {
        commentRepository.deleteById(id);
    }

}
