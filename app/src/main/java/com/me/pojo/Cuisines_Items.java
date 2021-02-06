package com.me.pojo;

public class Cuisines_Items {
    String Name;
    int image_Id;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getImage_Id() {
        return image_Id;
    }

    public void setImage_Id(int image_Id) {
        this.image_Id = image_Id;
    }

    public Cuisines_Items(String name, int image_Id) {
        Name = name;
        this.image_Id = image_Id;
    }
}
