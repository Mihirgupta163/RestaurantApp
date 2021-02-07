package com.me.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.me.R;
import com.me.pojo.Cart_Details;

import java.util.ArrayList;

public class Cart_Adapter extends RecyclerView.Adapter<Cart_Adapter.CartItems> {

    Context context;
    ArrayList<Cart_Details> cart_details;

    public Cart_Adapter(Context context, ArrayList<Cart_Details> cart_details) {
        this.context = context;
        this.cart_details = cart_details;
    }

    @NonNull
    @Override
    public CartItems onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.cart_list_item,parent,false);
        return new CartItems(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CartItems holder, int position) {
        Cart_Details item = cart_details.get(position);
        holder.name.setText(item.getName());
        holder.price.setText(item.getPrice());
        holder.quantity.setText(item.getQuantity()+"  X");

        int n = Integer.parseInt(item.getPrice());
        int quan = Integer.parseInt(item.getQuantity());
        int prod = n*quan;

        holder.total_price.setText("=  "+String.valueOf(prod));

    }

    @Override
    public int getItemCount() {
        return cart_details.size();
    }

    public class CartItems extends RecyclerView.ViewHolder {
        TextView name, quantity,total_price,price;
        public CartItems(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.cart_list_item_name);
            quantity = itemView.findViewById(R.id.cart_list_item_quantity);
            price = itemView.findViewById(R.id.cart_list_item_price);
            total_price = itemView.findViewById(R.id.total_per_price);
        }
    }
}
