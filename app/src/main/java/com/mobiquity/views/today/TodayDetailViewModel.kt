package com.mobiquity.views.today

import androidx.lifecycle.ViewModel
import com.mobiquity.data.models.TodayForecast
import com.mobiquity.data.repository.WeatherRepository
import com.mobiquity.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TodayDetailViewModel @Inject constructor(
    private val repository: WeatherRepository
) : ViewModel() {

    suspend fun getPokemonList(lat: Double?, lon: Double?, appID: String): Resource<TodayForecast> {
        return repository.getPokemonList(lat, lon, appID)
    }
}