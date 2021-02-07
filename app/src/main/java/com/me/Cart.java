package com.me;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.me.adapters.Cart_Adapter;
import com.me.pojo.Cart_Details;
import com.me.pojo.MyApplication;

import java.util.ArrayList;

public class Cart extends AppCompatActivity {
    Toolbar toolbar;
    RecyclerView recyclerView;
    TextView totalPrice, Grand_total;
    ArrayList<Cart_Details> cart_details;
    MyApplication myApplication;
    int tPrice, gPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        recyclerView = findViewById(R.id.cart_recyclerView);
        cart_details = new ArrayList<>();

        myApplication = (MyApplication) this.getApplication();


        totalPrice = findViewById(R.id.cart_totalAmount);
        Grand_total = findViewById(R.id.grand_total_price);

        toolbar = findViewById(R.id.cart_toolbar);
        setSupportActionBar(toolbar);

        cart_details = MyApplication.getCart_details();

        for(Cart_Details cart_detail: cart_details){
            tPrice += Integer.parseInt(cart_detail.getPrice());
        }

        totalPrice.setText(String.valueOf(tPrice));
        recyclerView.setLayoutManager(new LinearLayoutManager(Cart.this));
        Cart_Adapter adapter = new Cart_Adapter(Cart.this,cart_details);
        recyclerView.setAdapter(adapter);

    }
}