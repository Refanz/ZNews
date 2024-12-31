package com.refanzzzz.znews.data.model

import com.google.gson.annotations.SerializedName

data class NewsSource(

	@field:SerializedName("sources")
	private val sources: List<SourceItem>? = null,

	@field:SerializedName("status")
	private val status: String? = null
)

data class SourceItem(

	@field:SerializedName("country")
	private val country: String? = null,

	@field:SerializedName("name")
	private val name: String? = null,

	@field:SerializedName("description")
	private val description: String? = null,

	@field:SerializedName("language")
	private val language: String? = null,

	@field:SerializedName("id")
	private val id: String? = null,

	@field:SerializedName("category")
	private val category: String? = null,

	@field:SerializedName("url")
	private val url: String? = null
)
