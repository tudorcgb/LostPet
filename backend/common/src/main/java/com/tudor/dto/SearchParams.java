package com.tudor.dto;

public class SearchParams {

    private boolean pierdut;
    private Long breed;
    private String searchString;
    private String lat_end;
    private String lng_end;
    private String lat;
    private String lng;

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

    public boolean isPierdut() {
        return pierdut;
    }

    public void setPierdut(boolean pierdut) {
        this.pierdut = pierdut;
    }

    public Long getBreed() {
        return breed;
    }

    public void setBreed(Long breed) {
        this.breed = breed;
    }

    public String getSearchString() {
        return searchString;
    }

    public void setSearchString(String searchString) {
        this.searchString = searchString;
    }
}
