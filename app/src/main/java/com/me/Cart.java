package com.me;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

public class Cart extends AppCompatActivity {
    Toolbar toolbar;
    RecyclerView recyclerView;
    TextView totalPrice, Grand_total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        recyclerView = findViewById(R.id.cart_recyclerView);

        totalPrice = findViewById(R.id.cart_totalAmount);
        Grand_total = findViewById(R.id.grand_total_price);

        toolbar = findViewById(R.id.cart_toolbar);
        setSupportActionBar(toolbar);

    }
}