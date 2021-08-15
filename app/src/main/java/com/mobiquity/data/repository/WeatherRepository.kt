package  com.mobiquity.data.repository

import com.mobiquity.data.WeatherApi
import com.mobiquity.data.models.TodayForecast
import com.mobiquity.utils.Resource
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class WeatherRepository @Inject constructor(
    private val api: WeatherApi
) {

    suspend fun getPokemonList(lat: Double?, lon: Double?, appID: String): Resource<TodayForecast> {
        val response = try {
            api.getTodayForecast(lat, lon, appID)
        } catch (e: Exception) {
            return Resource.Error("An unknown error occured.")
        }
        return Resource.Success(response)
    }


}