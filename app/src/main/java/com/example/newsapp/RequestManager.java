package com.example.newsapp;

import android.content.Context;
import android.widget.Toast;

import com.example.newsapp.Models.ApiResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class RequestManager {
    Context context;
    Retrofit retrofit=new Retrofit.Builder()
            .baseUrl("https://newsapi.org/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();


    public void getNewsHeadlines(onfetchDataListener listener,String category,String query){
        CallNewsApi callNewsApi=retrofit.create(CallNewsApi.class);
        Call<ApiResponse> call=callNewsApi.callHeadlines("in",category,query, context.getString(R.string.api_key));
        try{
            call.enqueue(new Callback<ApiResponse>() {
                @Override
                public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                    if(!response.isSuccessful()){
                        Toast.makeText(context, "Error!!", Toast.LENGTH_SHORT).show();
                    }

                    listener.onFetchData(response.body().getArticles(),response.message());
                }

                @Override
                public void onFailure(Call<ApiResponse> call, Throwable t) {
                    listener.onError("Request Failed!");
                }
            });
        }catch (Exception e){

        }
    }
    public RequestManager(Context context) {
        this.context = context;
    }
    public interface CallNewsApi{
        @GET("top-headlines")
        Call<ApiResponse> callHeadlines(
                @Query("country") String country,
                @Query("category") String category,
                @Query("q") String query,
                @Query("apiKey") String apiKey
        );
    }
}
