package com.vi.newsapp.data.articles

import com.vi.newsapp.domain.pojo.Example
import retrofit2.Call
import retrofit2.http.GET

interface Api {

    @GET("in.json")
    fun getArticles(): Call<Example>

}