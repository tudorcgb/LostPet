package com.lostpet.backend.mapper;

import com.lostpet.backend.entity.Comment;
import com.tudor.dto.CommentDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    CommentDTO toDto(Comment comment);

    Comment toEntity(CommentDTO commentDTO);

}
