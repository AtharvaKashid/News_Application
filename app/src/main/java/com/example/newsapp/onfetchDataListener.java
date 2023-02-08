package com.example.newsapp;


import com.example.newsapp.Models.Headlines;

import java.util.List;

public interface onfetchDataListener<ApiResponse> {
    void onFetchData(List<Headlines> list, String message);
    void onError(String message);
}
