package com.lostpet.backend.repository;

import com.lostpet.backend.entity.Chat;
import com.lostpet.backend.entity.Comment;
import com.lostpet.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatRepository extends JpaRepository< Chat,Long> {

    List<Chat> findBySenderOrDest(User sender, User dest);

    @Query("SELECT chat FROM Chat chat WHERE chat.sender.id = :id OR chat.dest.id = :id")
    List<Chat> findByIsSendOrReceive(@Param("id")Long id);
}
