package com.nt.kavakapp.home.core.service

import com.nt.kavakapp.home.core.domain.HomeModel
import com.nt.kavakapp.home.core.domain.ResultModel
import retrofit2.http.GET

interface HomeService {
    @GET("main/books.json")
    suspend fun getBooksData(): ResultModel

    @GET("main/best_sellers.json")
    suspend fun getBestSellers(): ResultModel

}