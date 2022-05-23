package com.example.listusergithub

import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @GET("search/users")
    fun getListUser(@Query("q") searchText: String): Call<ApiResponse>

    @GET("users/{username}")
    fun getUserDetail(@Path("username") id: String): Call<DetailResponse>

}
