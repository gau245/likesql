package com.example.favlist.Model;

public class ModelFavList {

    String pos;
    String name;
    int image;

    public ModelFavList(String pos, String name, int image) {
        this.pos = pos;
        this.name = name;
        this.image = image;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getPos() {
        return pos;
    }

    public void setPos(String pos) {
        this.pos = pos;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
