package com.refanzzzz.znews.data.remote.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.refanzzzz.znews.data.model.ArticlesItem
import com.refanzzzz.znews.data.remote.retrofit.ApiService
import javax.inject.Inject

class NewsArticlePagingDataSource @Inject constructor(
    private val apiService: ApiService,
    private val source: String,
    private val title: String = ""
) :
    PagingSource<Int, ArticlesItem>() {

    override fun getRefreshKey(state: PagingState<Int, ArticlesItem>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ArticlesItem> {
        try {
            val page = params.key ?: 1
            val newsArticles =
                apiService.getNewsArticlesBySource(source, page, params.loadSize).articles

            val filteredArticles = if (title.isBlank()) {
                newsArticles
            } else {
                newsArticles.filter { it.title!!.contains(title, ignoreCase = true) }
            }

            return LoadResult.Page(
                data = filteredArticles,
                prevKey = if (page == 1) null else page.minus(1),
                nextKey = if (newsArticles.isEmpty()) null else page.plus(1)
            )

        } catch (exception: Exception) {
            Log.e(TAG, exception.message ?: "")
            return LoadResult.Error(exception)
        }
    }

    private companion object {
        const val TAG = "NewsSourcePagingDataSource"
    }
}