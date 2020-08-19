package com.ramadan.hiltplayground.demo.retrofit

import retrofit2.http.GET

interface BlogAPI {
    @GET("blogs")
    suspend fun getBlogs():List<BlogNetworkEntity>
}