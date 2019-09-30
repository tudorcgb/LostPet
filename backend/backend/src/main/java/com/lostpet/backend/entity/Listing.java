package com.lostpet.backend.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Listing {

    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private String abstractField;
    @ManyToOne
    private User writer;
    @ManyToMany
    private List<Listing> relatedListings;
    @ManyToOne
    private Breed breed;
    private String body;
    private String lat;
    private String lng;
    private String imgUrl;
    private String lat_end;
    private String lng_end;
    private boolean pierdut;
    private String adress;

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
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

    public String getAbstractField() {
        return abstractField;
    }

    public void setAbstractField(String abstractField) {
        this.abstractField = abstractField;
    }

    public User getWriter() {
        return writer;
    }

    public void setWriter(User writer) {
        this.writer = writer;
    }

    public List<Listing> getRelatedListings() {
        return relatedListings;
    }

    public void setRelatedListings(List<Listing> relatedListings) {
        this.relatedListings = relatedListings;
    }

//    public List<Category> getUser() {
//        return categories;
//    }
//
//    public void setUser(List<Category> categories) {
//        this.categories = categories;
//    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Breed getBreed() {
        return breed;
    }

    public void setBreed(Breed breed) {
        this.breed = breed;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getLat_end() {
        return lat_end;
    }

    public void setLat_end(String lat_end) {
        this.lat_end = lat_end;
    }

    public String getLng_end() {
        return lng_end;
    }

    public void setLng_end(String lng_end) {
        this.lng_end = lng_end;
    }

    public boolean isPierdut() {
        return pierdut;
    }

    public void setPierdut(boolean pierdut) {
        this.pierdut = pierdut;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }
}
