package com.refanzzzz.znews.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.refanzzzz.znews.data.model.ArticlesItem
import com.refanzzzz.znews.data.model.NewsSource
import com.refanzzzz.znews.data.remote.paging.NewsArticlePagingDataSource
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

    fun getNewsArticlesPagingDataSource(source: String): Flow<PagingData<ArticlesItem>> {
        return Pager(
            pagingSourceFactory = {
                NewsArticlePagingDataSource(apiService, source)
            },
            config = PagingConfig(20)
        ).flow
    }

    fun searchNewsArticles(source: String, title: String): Flow<PagingData<ArticlesItem>> {
        return Pager(
            pagingSourceFactory = {
                NewsArticlePagingDataSource(apiService, source, title)
            },
            config = PagingConfig(20)
        ).flow
    }
}