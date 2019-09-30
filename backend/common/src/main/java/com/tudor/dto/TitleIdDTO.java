package com.tudor.dto;

public class TitleIdDTO {

    Long id;
    String title;
    String abstractField;

    public String getAbstractField() {
        return abstractField;
    }

    public void setAbstractField(String abstractField) {
        this.abstractField = abstractField;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
