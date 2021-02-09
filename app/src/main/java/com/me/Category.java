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
import android.view.View;
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
        setSupportActionBar(toolbar);

        myApplication = (MyApplication) this.getApplication();

        cart_details = new ArrayList<>();
        category_items = new ArrayList<>();

        recyclerView = findViewById(R.id.catData);
        recyclerView.setLayoutManager(new LinearLayoutManager(Category.this));

        Intent i = getIntent();
        String cat_name = i.getStringExtra("Cat_Name");
        toolbar.setTitle(cat_name);
        toolbar.setNavigationIcon(R.drawable.arrow_back);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Category.super.onBackPressed();
            }
        });



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
                category_items.add(new Category_Items("Onion Dosa","90",
                        "Crispy, deep-fried and divine, this rava-style dosa made with onions is too good to be true. Soon to" +
                                " be your new favourite, you can even add some green chillies" +
                                " for that extra zing.",R.drawable.o_dosa));
                category_items.add(new Category_Items("Uttpam","80",
                        "Light and scrummy, uttapam is a dosa-like preparation made with a mixture of rice, dhuli urad dal and fenugreek seeds. Once you try it, you're sure to ask for seconds!"
                                ,R.drawable.uttapam));
                break;
            case "Mexican":
                category_items.add(new Category_Items("Burritos","110",
                        "Gorge on delicious tortilla wraps stuffed with spiced minced meat. They are wholesome and ideal for a meal on-the-go."
                        ,R.drawable.burritos));
                category_items.add(new Category_Items("Do-It-Yourself Tacos","80",
                        "Load your taco shells with some kidney beans, scraped cheddar and hot jalapenos for a sumptuous bite. Add a touch of cumin for the rustic flavour."
                        ,R.drawable.tacos));
                break;
            case "Thai":
                category_items.add(new Category_Items("Som Tam (Papaya Salad)","110",
                        "Som Tam is a green papaya salad that combines all four tastes - sour, chilli, sweet and salty. This salad is not only pleasing to the eyes, but to the palate as well. "
                        ,R.drawable.papaya_salad));
                category_items.add(new Category_Items("Massaman Curry","150",
                        "Chicken cooked in coconut flavors, tamarind, potatoes and an aromatic massaman curry paste. This Thai Muslim curry is made using a variety of flavours, so get ready for a flavourful blast."
                        ,R.drawable.curry));
                break;
            case "Japanese":
                category_items.add(new Category_Items("Sushi","90",
                        "Sushi usually refers to a dish of pressed vinegared rice with a piece of raw fish or shellfish, called a neta, on top. Sushi is generally eaten with soy sauce and wasabi"
                        ,R.drawable.sushi));
                category_items.add(new Category_Items("Soba","150",
                        "Soba is a noodle dish made from buckwheat flour with water and flour, thinly spread and cut into noodles with widths of 1cm-2cm." +
                                "Soba is enjoyed hot or cold, making it an ideal dish year-round."
                        ,R.drawable.soba));
                break;
            case "Chinese":
                category_items.add(new Category_Items("Momo","90",
                        "Small bite-sized rounds stuffed with veggies or meat. Dimsums are perfect steamed snack to delight those evening cravings."
                        ,R.drawable.momo));
                category_items.add(new Category_Items("Stir Fried Tofu with Rice","150",
                        "A simple stir-fry with tofu and oriental sauces. Sti fried togu with rice is a great main course dish to prepare at home laced with flavourful spices and sauces"
                        ,R.drawable.tofu_with_rice));
                break;
            case "Italian":
                category_items.add(new Category_Items("Bruschetta","90",
                        "An antipasto dish, bruschetta has grilled bread topped with veggies, rubbed garlic and tomato mix. A country bread sliced and topped with different toppings - the evergreen tomato-basil and an inventive mushroom-garlic. "
                        ,R.drawable.bruschetta));
                category_items.add(new Category_Items("Margherita Pizza","150",
                        "Margherita Pizza is to many the true Italian flag. One of the most loved Italian dishes, it just takes a few simple ingredients and you get insanely delicious results! You just can't go wrong with that tomato, basil and fresh mozzarella combo."
                        ,R.drawable.margherita_pizza));
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
        getMenuInflater().inflate(R.menu.cat_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_cart_cat) {
            Intent intent = new Intent(Category.this, Cart.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}