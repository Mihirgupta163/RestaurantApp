package com.me.pojo;

public class Cart_Details {
    String name,price,quantity;
    int img;

    public Cart_Details(String name, String price, String quantity, int img) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }
}
