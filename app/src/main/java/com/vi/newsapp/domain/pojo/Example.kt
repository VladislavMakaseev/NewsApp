package com.vi.newsapp.domain.pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Example(
    @SerializedName("status")
    @Expose
    val status: String,

    @SerializedName("totalResults")
    @Expose
    var totalResults: Int? = null,

    @SerializedName("articles")
    @Expose
    var articles: List<Article>? = null
)