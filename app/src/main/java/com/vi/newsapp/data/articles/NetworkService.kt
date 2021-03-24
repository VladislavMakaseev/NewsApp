package com.vi.newsapp.data.articles

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

abstract class NetworkService  {

    companion object {
        private const val BASE_URL = "https://saurav.tech/"
        private var retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient.Builder().build())
            .build()

        fun createApi(): Api {
            return retrofit.create(Api::class.java)
        }
    }

}