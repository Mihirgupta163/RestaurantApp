package com.me.pojo;

public class Category_Items {
    String Name, price, description;
    int Image;

    public Category_Items(String name, String price, String description, int image) {
        Name = name;
        this.price = price;
        this.description = description;
        Image = image;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getImage() {
        return Image;
    }

    public void setImage(int image) {
        Image = image;
    }

}
