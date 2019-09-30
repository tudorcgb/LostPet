package com.tudor.dto;

import java.io.Serializable;
import java.sql.Timestamp;

public class CommentDTO implements Serializable {

    private Long id;
    private Long listing;
    private Long user;
    private String comment;
    private Timestamp date;
    private String username;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getListing() {
        return listing;
    }

    public void setListing(Long listing) {
        this.listing = listing;
    }

    public Long getUser() {
        return user;
    }

    public void setUser(Long user) {
        this.user = user;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
