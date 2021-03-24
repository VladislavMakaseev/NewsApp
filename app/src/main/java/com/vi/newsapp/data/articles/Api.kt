package com.vi.newsapp.data.articles

import com.vi.newsapp.domain.pojo.Example
import retrofit2.Call
import retrofit2.http.GET

interface Api {

    @GET("NewsAPI/top-headlines/category/science/in.json")
    fun getArticles(): Call<Example>

}