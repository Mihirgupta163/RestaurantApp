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

import com.me.adapters.CuisinesAdapter;
import com.me.pojo.Category_Items;
import com.me.pojo.Cuisines_Items;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements CuisinesAdapter.OnItemClickListener {

    Toolbar toolbar;
    RecyclerView recyclerView, top_products;
    CuisinesAdapter cuisinesAdapter;
    ArrayList<Cuisines_Items> cuisines_items;
    ArrayList<Category_Items> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cuisines_items = new ArrayList<>();

        top_products = findViewById(R.id.top_orders);
        recyclerView = findViewById(R.id.cuisine);
        toolbar = findViewById(R.id.menu_toolbar);
        setSupportActionBar(toolbar);

        items.add(new Category_Items("Chole Bhature","120","" +
                "Mouth-watering meal straight from the" +
                " Punjabi kitchen - garma garam bhature with chickpeas cooked in assorted spices."
                ,R.drawable.chole_bhature));
        items.add(new Category_Items("Stuffed Bati","180","This Rajasthani bread snack is cooked in ghee and served with chutney and dal. Bati is usually stuffed with paneer and spices."
                ,R.drawable.bati));


        cuisines_items.add( new Cuisines_Items("North Indian",R.drawable.north_indian));
        cuisines_items.add( new Cuisines_Items("South Indian",R.drawable.south_indian));
        cuisines_items.add( new Cuisines_Items("Mexican",R.drawable.mexican));
        cuisines_items.add( new Cuisines_Items("Thai",R.drawable.thai_cuisine));
        cuisines_items.add( new Cuisines_Items("Japanese",R.drawable.japanese_cuisine));
        cuisines_items.add( new Cuisines_Items("Chinese",R.drawable.chinesefood));
        cuisines_items.add( new Cuisines_Items("Italian",R.drawable.italian_cuisine));

        cuisinesAdapter = new CuisinesAdapter(MainActivity.this,cuisines_items, MainActivity.this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if(dx == cuisines_items.size()){
                    recyclerView.getLayoutManager().scrollToPosition(0);
                }
            }
        });
        recyclerView.setAdapter(cuisinesAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.cart,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_cart) {
            Intent intent = new Intent(MainActivity.this, Cart.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(int position, String name) {
        Intent intent = new Intent(MainActivity.this, Category.class);
        intent.putExtra("Cat_Name",name);
        startActivity(intent);
    }
}