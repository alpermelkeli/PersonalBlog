package com.alpermelkeli.personalblog.model;

public class Education {
    String title;
    String imageUrl;
    String from;
    String category;

    public Education(String title, String imageUrl, String from, String category) {
        this.title = title;
        this.imageUrl = imageUrl;
        this.from = from;
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
