package com.tudor.dto;

public class ChatDTO {

    private Long id;
    private UsernameIdDTO sender;
    private UsernameIdDTO dest;
    private String chatName;
    private Long multiplication;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UsernameIdDTO getSender() {
        return sender;
    }

    public void setSender(UsernameIdDTO sender) {
        this.sender = sender;
    }

    public UsernameIdDTO getDest() {
        return dest;
    }

    public void setDest(UsernameIdDTO dest) {
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
