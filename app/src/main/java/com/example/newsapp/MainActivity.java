package com.example.newsapp;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.newsapp.Models.ApiResponse;
import com.example.newsapp.Models.Headlines;

import java.util.List;

public class MainActivity extends AppCompatActivity implements SelectInterface,View.OnClickListener{
    RecyclerView recyclerView;
    CustomAdapter adapter;
    ProgressDialog dialog;
    Button btn1,btn2,btn3,btn4,btn5,btn6,btn7;
    SearchView searchView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
           searchView=(SearchView) findViewById(R.id.search_view);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                dialog.setTitle("Fetching news articles.. of"+query);
                dialog.show();
                RequestManager manager=new RequestManager(MainActivity.this);
                manager.getNewsHeadlines(listener,"general",query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
        dialog=new ProgressDialog(this);
        dialog.setTitle("Fetching news articles...");
        dialog.show();
        btn1=findViewById(R.id.btn1);
        btn1.setOnClickListener(this);
        btn2=findViewById(R.id.btn2);
        btn2.setOnClickListener( this);
        btn3=findViewById(R.id.btn3);
        btn3.setOnClickListener( this);
        btn4=findViewById(R.id.btn4);
        btn4.setOnClickListener(this);
        btn5=findViewById(R.id.btn5);
        btn5.setOnClickListener( this);
        btn6=findViewById(R.id.btn6);
        btn6.setOnClickListener( this);
        btn7=findViewById(R.id.btn7);
        btn7.setOnClickListener( this);
        RequestManager manager=new RequestManager(this);
        manager.getNewsHeadlines(listener,"general",null);
    }
    private final onfetchDataListener<ApiResponse> listener=new onfetchDataListener<ApiResponse>() {
        @Override
        public void onFetchData(List<Headlines> list, String message) {
            if(list.isEmpty()){
                Toast.makeText(MainActivity.this,"No data found!!!",Toast.LENGTH_SHORT).show();
            }else{
                showNews(list);
                dialog.dismiss();
            }

        }

        @Override
        public void onError(String message) {
            Toast.makeText(MainActivity.this,"An Error Occurred",Toast.LENGTH_SHORT).show();
        }
    };

    private void showNews(List<Headlines> list) {
        recyclerView=findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this,1));
        adapter=new CustomAdapter( this,list, this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onNewsClicked(Headlines headlines) {
        startActivity(new Intent(MainActivity.this,DetailsActivity.class).putExtra("data",headlines));

    }

    @Override
    public void onClick(View view) {
        Button button=(Button) view;
        String category=button.getText().toString();
        dialog.setTitle("Fetching news articles of.."+category);
        dialog.show();
        RequestManager manager=new RequestManager(this);
        manager.getNewsHeadlines(listener,category,null);
    }
}