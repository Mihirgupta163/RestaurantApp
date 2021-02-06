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

public class Category_Adapter extends RecyclerView.Adapter <Category_Adapter.MainAdapter_Holder>{

    ArrayList<Category_Items> category_items;
    Context context;
    OnItemClickListener itemClickListener;

    public Category_Adapter(ArrayList<Category_Items> category_items, Context context, OnItemClickListener itemClickListener) {
        this.category_items = category_items;
        this.context = context;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public MainAdapter_Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.cat_data, parent, false);
        return new Category_Adapter.MainAdapter_Holder(listItem,itemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MainAdapter_Holder holder, int position) {
        Category_Items item = category_items.get(position);
        holder.description.setText(item.getDescription());
        holder.image.setImageResource(item.getImage());
        holder.name.setText(item.getName());
        holder.price.setText(item.getPrice());

    }

    @Override
    public int getItemCount() {
        return category_items.size();
    }

    public class MainAdapter_Holder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ImageView image;
        public TextView name;
        public  TextView price;
        public TextView description;
        public  TextView add;
        OnItemClickListener onItemClickListener;

       public MainAdapter_Holder(@NonNull View itemView, OnItemClickListener onItemClickListener) {
           super(itemView);
           this.image = itemView.findViewById(R.id.image);
           this.name = itemView.findViewById(R.id.name);
           this.description=itemView.findViewById(R.id.description);
           this.price=itemView.findViewById(R.id.price);
           this.add=itemView.findViewById(R.id.add);
           this.onItemClickListener = onItemClickListener;
           add.setOnClickListener(this);
       }

        @Override
        public void onClick(View v) {
            onItemClickListener.onItemClick(getAdapterPosition());
        }
    }
    public interface OnItemClickListener{
        void onItemClick(int position);
    }
}
