package com.example.newsapp;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class CustomViewHolder extends RecyclerView.ViewHolder {
    TextView texttitle,textsource;
    ImageView image_headline;
    CardView cardView;
    public CustomViewHolder(@NonNull View itemView) {
        super(itemView);
        texttitle=itemView.findViewById(R.id.texttitle);
        textsource=itemView.findViewById(R.id.textsource);
        image_headline=itemView.findViewById(R.id.image_headline);
        cardView=itemView.findViewById(R.id.main_container);
    }
}
