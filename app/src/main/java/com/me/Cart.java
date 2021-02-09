package com.me;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
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

public class Cart extends AppCompatActivity implements Cart_Adapter.ClickItem {
    Toolbar toolbar;
    RecyclerView recyclerView;
    TextView totalPrice, Grand_total;
    ArrayList<Cart_Details> cart_details;
    MyApplication myApplication;
    int tPrice=0;
    Button orderButton;
    ProgressDialog pd;
    Cart_Adapter adapter;

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
            tPrice += Integer.parseInt(cart_detail.getPrice())* Integer.parseInt(cart_detail.getQuantity());
        }
        setPriceInCart(tPrice);

        recyclerView.setLayoutManager(new LinearLayoutManager(Cart.this));
        recyclerView.addItemDecoration(new DividerItemDecoration(Cart.this,
                DividerItemDecoration.VERTICAL));
        adapter = new Cart_Adapter(Cart.this,cart_details, Cart.this);
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
                            cart_details.clear();
                            adapter.notifyDataSetChanged();
                            startActivity(new Intent(Cart.this,MainActivity.class));
                            MyApplication.cart_details.clear();
                        }
                    },3000);
                }
            }
        });
    }

    @Override
    public void itemClick(int position) {
        tPrice = 0;
        adapter.delete(position);
        adapter.notifyDataSetChanged();
        MyApplication.setCart_details(cart_details);
        for(Cart_Details cart_detail: MyApplication.getCart_details()){
            tPrice += Integer.parseInt(cart_detail.getPrice()) * Integer.parseInt(cart_detail.getQuantity());
        }
        setPriceInCart(tPrice);

    }

    @Override
    public void incrementBtnClick(int position) {
     tPrice = 0;
     adapter.incrementBtn(position);
     for(Cart_Details cart_detail: MyApplication.getCart_details()){
               tPrice += Integer.parseInt(cart_detail.getPrice()) * Integer.parseInt(cart_detail.getQuantity());
           }
           setPriceInCart(tPrice);
    }

    @Override
    public void decrementBtnClick(int position) {
        tPrice = 0;
      adapter.decrementBtn(position);
      for(Cart_Details cart_detail: MyApplication.getCart_details()){
          tPrice += Integer.parseInt(cart_detail.getPrice())* Integer.parseInt(cart_detail.getQuantity());
      }
      setPriceInCart(tPrice);

    }

    public void setPriceInCart(int totalprice){

        if(totalprice >= 0 && cart_details.size()>0){
            double gPrice = totalprice + totalprice*(2.5/100);
            totalPrice.setText("Rs. "+String.valueOf(totalprice));
            Grand_total.setText("Rs. "+String.valueOf(gPrice));
        }
        else{
            tPrice = 0;
            totalPrice.setText("Rs. "+String.valueOf(0));
            Grand_total.setText("Rs. "+String.valueOf(0));
        }
    }
}