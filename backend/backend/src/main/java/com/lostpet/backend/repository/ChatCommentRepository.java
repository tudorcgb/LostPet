package com.lostpet.backend.repository;

import com.lostpet.backend.entity.ChatComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatCommentRepository extends JpaRepository<ChatComment, Long> {


    List<ChatComment> findByIdChatOrderByDateAsc(Long chatId);
}
