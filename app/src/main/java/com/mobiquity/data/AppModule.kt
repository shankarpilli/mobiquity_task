package com.mobiquity.data

import com.mobiquity.data.repository.WeatherRepository
import com.mobiquity.utils.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun providePokemonRepository(
        api: WeatherApi
    ) = WeatherRepository(api)

    @Singleton @Provides
    fun providePokeApi(): WeatherApi {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(WeatherApi::class.java)
    }
}