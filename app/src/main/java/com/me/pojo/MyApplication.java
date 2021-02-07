package com.me.pojo;

import android.app.Application;

import com.me.adapters.Cart_Adapter;

import java.util.ArrayList;

public class MyApplication extends Application {
    public static ArrayList<Cart_Details> cart_details = new ArrayList<>();

    public static ArrayList<Cart_Details> getCart_details() {
        return cart_details;
    }

    public static void setCart_details(ArrayList<Cart_Details> cart_details) {
        MyApplication.cart_details = cart_details;
    }
}
