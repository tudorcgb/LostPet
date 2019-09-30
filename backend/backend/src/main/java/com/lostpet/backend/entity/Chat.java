package com.lostpet.backend.entity;

import javax.persistence.*;

@Entity
public class Chat {

    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    private User sender;
    @ManyToOne
    private User dest;
    private String chatName;
    private Long multiplication;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public User getDest() {
        return dest;
    }

    public void setDest(User dest) {
        this.dest = dest;
    }

    public String getChatName() {
        return chatName;
    }

    public void setChatName(String chatName) {
        this.chatName = chatName;
    }

    public Long getMultiplication() {
        return multiplication;
    }

    public void setMultiplication(Long multiplication) {
        this.multiplication = multiplication;
    }
}
