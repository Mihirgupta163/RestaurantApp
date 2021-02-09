package com.me.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.me.R;
import com.me.pojo.Cart_Details;
import com.me.pojo.MyApplication;

import java.util.ArrayList;

public class Cart_Adapter extends RecyclerView.Adapter<Cart_Adapter.CartItems> {

    Context context;
    ArrayList<Cart_Details> cart_details;
    ClickItem mClickItem;


    public Cart_Adapter(Context context, ArrayList<Cart_Details> cart_details,ClickItem mClickItem) {
        this.context = context;
        this.cart_details = cart_details;
        this.mClickItem = mClickItem;
    }

    @NonNull
    @Override
    public CartItems onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.cart_list_item,parent,false);
        return new CartItems(itemView,mClickItem);
    }

    @Override
    public void onBindViewHolder(@NonNull CartItems holder, int position) {
        final int [] quan = new int[1];
        quan[0] = 0;
        final int [] n = new int[1];
        n[0] = 0;
        final int [] prod = new int[1];
        prod[0] = 0;

        Cart_Details item = cart_details.get(position);
        holder.name.setText(item.getName());
        holder.price.setText("X "+item.getPrice());
        holder.quantity.setText(item.getQuantity());

        n[0] = Integer.parseInt(item.getPrice());
        quan[0] = Integer.parseInt(item.getQuantity());
        prod[0] = n[0] * quan[0];

        holder.total_price.setText("=  "+String.valueOf(prod[0]));

    }

    @Override
    public int getItemCount() {
        return cart_details.size();
    }

    public void delete(int position){
        cart_details.remove(position);
        notifyItemRemoved(position);
        notifyDataSetChanged();
    }

    public void incrementBtn(int pos){
        int quant = 0;
        quant = Integer.parseInt(cart_details.get(pos).getQuantity());
        quant += 1;
        cart_details.get(pos).setQuantity(String.valueOf(quant));
        MyApplication.setCart_details(cart_details);
        notifyItemChanged(pos);
    }

    public void decrementBtn(int pos){
        int quant = 0;
        quant = Integer.parseInt(cart_details.get(pos).getQuantity());
        if(quant>=2){
            quant -= 1;
            cart_details.get(pos).setQuantity(String.valueOf(quant));
            MyApplication.setCart_details(cart_details);
            notifyItemChanged(pos);
        }
    }

    public class CartItems extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView name, quantity,total_price,price;
        ImageView imageView;
        ClickItem clickItem;
        Button incrementBtn, decrementBtn;
        public CartItems(@NonNull View itemView, ClickItem clickItem) {
            super(itemView);
            name = itemView.findViewById(R.id.cart_list_item_name);
            quantity = itemView.findViewById(R.id.cart_list_item_quantity);
            price = itemView.findViewById(R.id.cart_list_item_price);
            total_price = itemView.findViewById(R.id.total_per_price);
            imageView = itemView.findViewById(R.id.delete_btn);
            incrementBtn = itemView.findViewById(R.id.incrementButton);
            decrementBtn = itemView.findViewById(R.id.decrementButton);

            this.clickItem = clickItem;
            imageView.setOnClickListener(this);
            incrementBtn.setOnClickListener(this);
            decrementBtn.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(v.getId() == imageView.getId()) clickItem.itemClick(getAdapterPosition());
            else if(v.getId() == incrementBtn.getId()) clickItem.incrementBtnClick(getAdapterPosition());
            else if(v.getId() == decrementBtn.getId()) clickItem.decrementBtnClick(getAdapterPosition());
        }
    }

    public interface ClickItem{
        public void itemClick(int position);
        public void incrementBtnClick(int position);
        public void decrementBtnClick(int position);
    }
}
