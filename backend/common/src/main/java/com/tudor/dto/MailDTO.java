package com.tudor.dto;

import java.io.Serializable;

public class MailDTO implements Serializable {

    private String dest;
    private String subject;
    private String message;

    public String getDest() {
        return dest;
    }

    public void setDest(String dest) {
        this.dest = dest;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void createTitle(String username, String listingTitle){
        this.subject =  username + " a postat un comentariul la anuntul " + listingTitle;
    }
    public void createMessage(String username, String listingTitle , CommentDTO comment){
        this.message =  "Utilizatorul : " + username + " a postat urmatorull comentariul la anuntul " + listingTitle + ": " + comment.getComment() + " la data de: " + comment.getDate() ;
    }
}
