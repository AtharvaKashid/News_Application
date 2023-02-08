package com.example.newsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.newsapp.Models.Headlines;
import com.squareup.picasso.Picasso;

public class DetailsActivity extends AppCompatActivity {
    Headlines headlines;
    TextView title,author,detail,time,content;
    ImageView img_news;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        title=findViewById(R.id.textdetail);
        author=findViewById(R.id.text_detail_author);
        detail=findViewById(R.id.text_detail_detail);
        time=findViewById(R.id.text_detail_time);
        content=findViewById(R.id.text_detail_content);
        img_news=findViewById(R.id.image_detsil_news);
        headlines=(Headlines) getIntent().getSerializableExtra("data");
        title.setText(headlines.getTitle());
        author.setText(headlines.getAuthor());
        time.setText(headlines.getPublishedAt());
        detail.setText(headlines.getDescription());
        content.setText(headlines.getContent());
        Picasso.get().load(headlines.getUrlToImage()).into(img_news);
    }
}