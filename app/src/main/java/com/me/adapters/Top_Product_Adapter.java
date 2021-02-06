package com.me.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.me.R;
import com.me.pojo.Category_Items;

import java.util.ArrayList;

public class Top_Product_Adapter extends RecyclerView.Adapter<Top_Product_Adapter.Product_VIewHolder> {

    Context context;
    ArrayList<Category_Items> items;
    ClickItem mClickItem;

    public Top_Product_Adapter(Context context, ArrayList<Category_Items> items, ClickItem mClickItem) {
        this.context = context;
        this.items = items;
        this.mClickItem = mClickItem;
    }

    @NonNull
    @Override
    public Product_VIewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.product,parent,false);
        return new Product_VIewHolder(itemView,mClickItem);
    }

    @Override
    public void onBindViewHolder(@NonNull Product_VIewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class Product_VIewHolder extends RecyclerView.ViewHolder {
        public ImageView image;
        public TextView name;
        public  TextView price;
        public  TextView add;
        ClickItem clickItem;

        public Product_VIewHolder(@NonNull View itemView, ClickItem clickItem) {
            super(itemView);
            this.image = itemView.findViewById(R.id.p_image);
            this.name = itemView.findViewById(R.id.p_name);
            this.price=itemView.findViewById(R.id.p_price);
            this.add=itemView.findViewById(R.id.p_add_btn);
            this.clickItem = clickItem;
        }
    }

    public interface ClickItem{
        public void itemClicked(int position);
    }
}
