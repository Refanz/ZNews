package com.refanzzzz.znews.data.remote.retrofit

import com.refanzzzz.znews.data.model.NewsArticle
import com.refanzzzz.znews.data.model.NewsSource
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("top-headlines/sources")
    suspend fun getNewsSources(@Query("category") category: String): NewsSource

    @GET("everything")
    suspend fun getNewsArticlesBySource(@Query("sources") source: String): NewsArticle
}