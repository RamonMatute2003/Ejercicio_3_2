package com.example.ejercicio3_2;

public class Forum_post{
    private String title;
    private String content;
    private int imageResource;

    //cosntructor
    public Forum_post(String title, String content, int imageResource) {
        this.title = title;
        this.content = content;
        this.imageResource = imageResource;
    }

    //metodos getters
    public int getImageResource() {
        return imageResource;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
}
