package com.me;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;

import com.google.android.material.snackbar.Snackbar;
import com.me.adapters.Category_Adapter;
import com.me.pojo.Cart_Details;
import com.me.pojo.Category_Items;
import com.me.pojo.MyApplication;

import java.util.ArrayList;

public class Category extends AppCompatActivity implements Category_Adapter.OnItemClickListener {

    ArrayList<Cart_Details> cart_details;
    Toolbar toolbar;
    RecyclerView recyclerView;
    ArrayList<Category_Items> category_items;
    Category_Adapter category_adapter;
    MyApplication myApplication;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        toolbar = findViewById(R.id.cat_toolbar);

        myApplication = (MyApplication) this.getApplication();

        cart_details = new ArrayList<>();
        category_items = new ArrayList<>();

        recyclerView = findViewById(R.id.catData);
        recyclerView.setLayoutManager(new LinearLayoutManager(Category.this));

        Intent i = getIntent();
        String cat_name = i.getStringExtra("Cat_Name");
        toolbar.setTitle(cat_name);
        setSupportActionBar(toolbar);


        switch (cat_name){
            case "North Indian":
                category_items.add(new Category_Items("Chole Bhature","120","" +
                        "Mouth-watering meal straight from the" +
                        " Punjabi kitchen - garma garam bhature with chickpeas cooked in assorted spices."
                ,R.drawable.chole_bhature));
                category_items.add(new Category_Items("Stuffed Bati","180","This Rajasthani bread snack is cooked in ghee and served with chutney and dal. Bati is usually stuffed with paneer and spices."
                        ,R.drawable.bati));
                break;
            case "South Indian":
                break;
            case "Mexican":
                break;
            case "Thai":
                break;
            case "Japanese":
                break;
            case "Chinese":
                break;
            case "Italian":
                break;
        }

        category_adapter = new Category_Adapter(category_items,Category.this,Category.this);
        recyclerView.setAdapter(category_adapter);

    }

    @Override
    public void onItemClick(int position) {
        Category_Items item = category_items.get(position);
        Snackbar.make(findViewById(android.R.id.content),item.getName()+" added to Cart",Snackbar.LENGTH_SHORT).show();
        Cart_Details cart = new Cart_Details(item.getName(),item.getPrice(),"1",item.getImage());
        MyApplication.cart_details.add(cart);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.cart,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_cart) {
            Intent intent = new Intent(Category.this, Cart.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}