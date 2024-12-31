package com.refanzzzz.znews.data.local

import com.refanzzzz.znews.R
import com.refanzzzz.znews.data.model.NewsCategory

object StaticDataSource {
    fun getNewsCategories(): List<NewsCategory> {
        return listOf(
            NewsCategory("business", R.drawable.business),
            NewsCategory("entertainment", R.drawable.entertainment),
            NewsCategory("general", R.drawable.general),
            NewsCategory("health", R.drawable.health),
            NewsCategory("science", R.drawable.science),
            NewsCategory("sports", R.drawable.sports),
            NewsCategory("technology", R.drawable.technology))
    }
}