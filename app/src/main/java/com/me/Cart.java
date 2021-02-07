package com.me;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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
    int tPrice;
    double gPrice;
    Button orderButton;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        pd = new ProgressDialog(Cart.this);
        pd.setCancelable(false);
        pd.setMessage("Placing Order.....");

        orderButton = findViewById(R.id.Order_Now_Button);
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
        gPrice = tPrice + tPrice*(2.5/100);
        totalPrice.setText("Rs. "+String.valueOf(tPrice));
        Grand_total.setText("Rs. "+String.valueOf(gPrice));
        recyclerView.setLayoutManager(new LinearLayoutManager(Cart.this));
        recyclerView.addItemDecoration(new DividerItemDecoration(Cart.this,
                DividerItemDecoration.VERTICAL));
        Cart_Adapter adapter = new Cart_Adapter(Cart.this,cart_details);
        recyclerView.setAdapter(adapter);

        orderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cart_details.isEmpty()){
                    Toast.makeText(Cart.this,"Nothing selected",Toast.LENGTH_SHORT).show();
                }else{
                    pd.show();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            pd.dismiss();
                            Toast.makeText(Cart.this,"Order is Placed Successfully",Toast.LENGTH_SHORT).show();
                        }
                    },3000);
                }
            }
        });
    }
}