package ru.freehardskill.springdudar.models;

import jakarta.persistence.*;

@Entity
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String annotation;
    private Float rating;
    private Long views;
    private Long downloads;
    private String imgUrl;
    @Column(length = 2056)
    private String fullText;


    public Long getId() {
        return id;
    }

    public Game() {
    }

    public Game(String name, String annotation, String fullText) {
        this.name = name;
        this.annotation = annotation;
        this.fullText = fullText;
        this.imgUrl = "";
        this.views = (long) 0;
        this.downloads = (long) 0;
        this.rating = (float) 0.0;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAnnotation() {
        return annotation;
    }

    public void setAnnotation(String annotation) {
        this.annotation = annotation;
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public Long getViews() {
        return views;
    }

    public void setViews(Long views) {
        this.views = views;
    }

    public Long getDownloads() {
        return downloads;
    }

    public void setDownloads(Long downloads) {
        this.downloads = downloads;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getFullText() {
        return fullText;
    }

    public void setFullText(String fullText) {
        this.fullText = fullText;
    }
}
