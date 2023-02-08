package com.example.newsapp.Models;

import java.io.Serializable;
import java.util.List;

public class ApiResponse implements Serializable {
    String status;
    int totalResults;
    List<Headlines> articles;

    public List<Headlines> getArticles() {
        return articles;
    }

    public void setArticles(List<Headlines> articles) {
        this.articles = articles;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }
}
