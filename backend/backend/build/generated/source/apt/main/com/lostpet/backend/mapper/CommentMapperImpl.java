package com.lostpet.backend.mapper;

import com.lostpet.backend.entity.Comment;
import com.tudor.dto.CommentDTO;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2019-07-18T20:15:08+0300",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 9.0.1 (Oracle Corporation)"
)
@Component
public class CommentMapperImpl implements CommentMapper {

    @Override
    public CommentDTO toDto(Comment comment) {
        if ( comment == null ) {
            return null;
        }

        CommentDTO commentDTO = new CommentDTO();

        commentDTO.setId( comment.getId() );
        commentDTO.setListing( comment.getListing() );
        commentDTO.setUser( comment.getUser() );
        commentDTO.setComment( comment.getComment() );
        commentDTO.setDate( comment.getDate() );

        return commentDTO;
    }

    @Override
    public Comment toEntity(CommentDTO commentDTO) {
        if ( commentDTO == null ) {
            return null;
        }

        Comment comment = new Comment();

        comment.setId( commentDTO.getId() );
        comment.setListing( commentDTO.getListing() );
        comment.setUser( commentDTO.getUser() );
        comment.setComment( commentDTO.getComment() );
        comment.setDate( commentDTO.getDate() );

        return comment;
    }
}
