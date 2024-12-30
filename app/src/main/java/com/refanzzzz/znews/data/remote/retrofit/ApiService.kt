package com.refanzzzz.znews.data.remote.retrofit

import com.refanzzzz.znews.data.model.NewsSource
import retrofit2.http.GET

interface ApiService {
    @GET("top-headlines/sources")
    suspend fun getNewsSources(): NewsSource
}