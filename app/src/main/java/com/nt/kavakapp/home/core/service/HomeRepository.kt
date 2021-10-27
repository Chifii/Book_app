package com.nt.kavakapp.home.core.service

import com.nt.kavakapp.base.BaseRepository
import com.nt.kavakapp.home.core.domain.HomeModel
import com.nt.kavakapp.home.core.domain.ResultModel
import com.nt.kavakapp.utils.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException

class HomeRepository : BaseRepository<HomeService>(HomeService::class.java) {
    suspend fun getScreenDataFromApi(): Result<ResultModel> {
        return withContext(Dispatchers.IO) {
            try {
                val data = service.getBooksData()
                Result.Success(data)
            } catch (exception: HttpException) {
                Result.Error(exception)
            }
        }
    }

    suspend fun getBestSellers(): Result<ResultModel> {
        return withContext(Dispatchers.IO) {
            try {
                val data = service.getBestSellers()
                Result.Success(data)
            } catch (exception: HttpException) {
                Result.Error(exception)
            }
        }
    }
}