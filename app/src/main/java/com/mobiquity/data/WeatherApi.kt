package com.mobiquity.data

import com.mobiquity.data.models.TodayForecast
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("weather?")
    suspend fun getTodayForecast(
        @Query("lat") lat: Double?,
        @Query("lon") lon: Double?,
        @Query("appid") appID: String,
    ): TodayForecast
}