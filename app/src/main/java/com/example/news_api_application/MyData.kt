package com.example.news_api_application

data class MyData(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)