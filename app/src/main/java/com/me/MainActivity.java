package com.me;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.snackbar.Snackbar;
import com.me.adapters.CuisinesAdapter;
import com.me.adapters.Top_Product_Adapter;
import com.me.pojo.Cart_Details;
import com.me.pojo.Category_Items;
import com.me.pojo.Cuisines_Items;
import com.me.pojo.MyApplication;

import java.util.ArrayList;
import java.util.Locale;

import static com.me.pojo.MyApplication.lan;

public class MainActivity extends AppCompatActivity implements CuisinesAdapter.OnItemClickListener, Top_Product_Adapter.ClickItem {

    Toolbar toolbar;
    RecyclerView recyclerView, top_products;
    CuisinesAdapter cuisinesAdapter;
    ArrayList<Cuisines_Items> cuisines_items;
    ArrayList<Category_Items> items;
    Top_Product_Adapter adapter;
    int currentItems, totalItems, scrollOutItem;
    Application myApplication;
    Context context;
    Resources resources;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cuisines_items = new ArrayList<>();
        items = new ArrayList<>();

        myApplication = this.getApplication();

        context = MainActivity.this;
        resources = MainActivity.this.getResources();

        top_products = findViewById(R.id.top_orders);
        recyclerView = findViewById(R.id.cuisine);
        toolbar = findViewById(R.id.menu_toolbar);
        setSupportActionBar(toolbar);

        items.add(new Category_Items(resources.getString(R.string.chole),"120","" +
                "Mouth-watering meal straight from the" +
                " Punjabi kitchen - garma garam bhature with chickpeas cooked in assorted spices."
                ,R.drawable.chole_bhature));
        items.add(new Category_Items(resources.getString(R.string.baati),"180","This Rajasthani bread snack is cooked in ghee and served with chutney and dal. Bati is usually stuffed with paneer and spices."
                ,R.drawable.bati));
        items.add(new Category_Items(resources.getString(R.string.pizza),"150",
                "Margherita Pizza is to many the true Italian flag. One of the most loved Italian dishes, it just takes a few simple ingredients and you get insanely delicious results! You just can't go wrong with that tomato, basil and fresh mozzarella combo."
                ,R.drawable.margherita_pizza));


        cuisines_items.add( new Cuisines_Items(resources.getString(R.string.north_indian),R.drawable.north_indian));
        cuisines_items.add( new Cuisines_Items(resources.getString(R.string.south_indian),R.drawable.south_indian));
        cuisines_items.add( new Cuisines_Items(resources.getString(R.string.mexican),R.drawable.mexican));
        cuisines_items.add( new Cuisines_Items(resources.getString(R.string.thai),R.drawable.thai_cuisine));
        cuisines_items.add( new Cuisines_Items(resources.getString(R.string.japanese),R.drawable.japanese_cuisine));
        cuisines_items.add( new Cuisines_Items(resources.getString(R.string.chinese),R.drawable.chinesefood));
        cuisines_items.add( new Cuisines_Items(resources.getString(R.string.italian),R.drawable.italian_cuisine));

        adapter = new Top_Product_Adapter(MainActivity.this,items,MainActivity.this);

        cuisinesAdapter = new CuisinesAdapter(MainActivity.this,cuisines_items, MainActivity.this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                currentItems = layoutManager.getChildCount();
                totalItems = layoutManager.getItemCount();
                scrollOutItem = layoutManager.findFirstVisibleItemPosition();

                if(scrollOutItem + currentItems == totalItems){
                    recyclerView.getLayoutManager().scrollToPosition((int)(Integer.MAX_VALUE/2f));
                    cuisinesAdapter.notifyDataSetChanged();
                }
            }
        });
        recyclerView.setAdapter(cuisinesAdapter);

        LinearLayoutManager layoutManager1 = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false);
        top_products.setLayoutManager(layoutManager1);
        top_products.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.cart,menu);
        MenuItem item = menu.findItem(R.id.action_lang);


        if(lan.equals("hi")){
            item.setIcon(R.drawable.hindi);
        }
        else {
            item.setIcon(R.drawable.eng);
        }
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.action_cart) {
            Intent intent = new Intent(MainActivity.this, Cart.class);
            startActivity(intent);
        }
        if( item.getItemId() == R.id.action_lang){
            if( lan.equals("hi") ){
                lan = "en";
                setLocale(MainActivity.this, lan);
                resources = MainActivity.this.getResources();
                startActivity(new Intent(MainActivity.this, MainActivity.class));
                finish();

            }else {
                lan = "hi";
                setLocale(MainActivity.this, lan);
                resources = MainActivity.this.getResources();

                startActivity(new Intent(MainActivity.this, MainActivity.class));
                finish();

            }

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(int position, String name) {
        Intent intent = new Intent(MainActivity.this, Category.class);
        intent.putExtra("Cat_Name",name);
        startActivity(intent);
    }

    @Override
    public void itemClicked(int position) {
        Snackbar.make(findViewById(android.R.id.content),items.get(position).getName()+" added to Cart",Snackbar.LENGTH_SHORT).show();
        MyApplication.cart_details.add(new Cart_Details(items.get(position).getName(),items.get(position).getPrice(),"1",items.get(position).getImage()));
    }

    public static void setLocale(Activity activity, String languageCode) {
        Locale locale = new Locale(languageCode);
        Locale.setDefault(locale);
        Resources resources = activity.getResources();
        Configuration config = resources.getConfiguration();
        config.setLocale(locale);
        resources.updateConfiguration(config, resources.getDisplayMetrics());
    }
}