package com.me.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.me.R;
import com.me.pojo.Cuisines_Items;

import java.util.ArrayList;

public class CuisinesAdapter extends RecyclerView.Adapter <CuisinesAdapter.MainAdapter_Holder> {

    ArrayList<Cuisines_Items> list;
    Context context;
    OnItemClickListener mListener;

    public CuisinesAdapter(Context context, ArrayList<Cuisines_Items> list, OnItemClickListener mListener) {
        this.context=context;
        this.list = list;
        this.mListener = mListener;
    }

    @NonNull
    @Override
    public MainAdapter_Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.cuisine_layout,parent,false);
        return new CuisinesAdapter.MainAdapter_Holder(listItem, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MainAdapter_Holder holder, int position) {
        Cuisines_Items item = list.get(position % list.size());
        holder.main_name.setText(item.getName());
        holder.main_image.setImageResource(item.getImage_Id());

        // first define colors
        final int[] backgroundColors = {
                R.color.list_color1,
                R.color.list_color2,
                R.color.list_color3,
                R.color.list_color4,
                R.color.list_color5,
                R.color.list_color6,
                R.color.list_color7 };

        int index = position % backgroundColors.length;
        int color = ContextCompat.getColor(context, backgroundColors[index]);
        holder.main_card_view.setCardBackgroundColor(color);
    }


    @Override
    public int getItemCount() {
        return Integer.MAX_VALUE;
    }


    public class MainAdapter_Holder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ImageView main_image;
        public TextView main_name;
        public CardView main_card_view;
        OnItemClickListener mListener;
        String name = "";

        public MainAdapter_Holder(@NonNull View itemView, OnItemClickListener mListener) {

            super(itemView);
            this.main_card_view=itemView.findViewById(R.id.main_cart_item);
            this.main_image = itemView.findViewById(R.id.main_item_image);
            this.main_name = itemView.findViewById(R.id.main_item_name);
            this.mListener = mListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(mListener !=null){
                int position=getAdapterPosition()% list.size();
                String name = list.get(position ).getName();
                if(position !=RecyclerView.NO_POSITION){
                    mListener.onItemClick(position,name);
                }
            }
        }
    }


    public interface OnItemClickListener{
        void onItemClick(int position,String name);
    }
}
