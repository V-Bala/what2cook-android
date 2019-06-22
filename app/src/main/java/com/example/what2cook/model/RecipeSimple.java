package com.example.what2cook.model;

import java.io.Serializable;

public class RecipeSimple implements Serializable {
    private String label;
    private String image;
    private String url;

    public RecipeSimple(String label, String image, String url) {
        this.label = label;
        this.image = image;
        this.url = url;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "RecipeSimple{" +
                "label='" + label + '\'' +
                ", image='" + image + '\'' +
                ", url='" + url + '\'' +
                '}';
    }

}
