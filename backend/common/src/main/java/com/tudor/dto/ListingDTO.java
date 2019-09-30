package com.tudor.dto;

import java.util.List;

    public class ListingDTO extends RelatedArticleDTO {


    private NameIdDTO writer;
    private NameIdDTO breed;
    private String body;
    private List<TitleIdDTO> relatedArticles;
    private String imgUrl;
    private String lat_end;
    private String lng_end;
    private String lat;
    private String lng;
    private boolean pierdut;
    private String adress;

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

        public NameIdDTO getWriter() {
        return writer;
    }

    public void setWriter(NameIdDTO writer) {
        this.writer = writer;
    }

    public NameIdDTO getBreed() {
        return breed;
    }

    public void setBreed(NameIdDTO breed) {
        this.breed = breed;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public List<TitleIdDTO> getRelatedArticles() {
        return relatedArticles;
    }

    public void setRelatedArticles(List<TitleIdDTO> relatedArticles) {
        this.relatedArticles = relatedArticles;
    }

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
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
