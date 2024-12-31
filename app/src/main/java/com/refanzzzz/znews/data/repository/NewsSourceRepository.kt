package com.refanzzzz.znews.data.repository

import com.refanzzzz.znews.data.model.NewsArticle
import com.refanzzzz.znews.data.model.NewsSource
import com.refanzzzz.znews.data.remote.retrofit.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class NewsSourceRepository @Inject constructor(private val apiService: ApiService) {
    fun getNewsSource(category: String): Flow<NewsSource> = flow {
        emit(apiService.getNewsSources(category))
    }.flowOn(Dispatchers.IO)

    fun getNewsArticlesBySource(source: String): Flow<NewsArticle> = flow {
        emit(apiService.getNewsArticlesBySource(source))
    }.flowOn(Dispatchers.IO)
}