package com.mobiquity.views.today

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltNavGraphViewModel
import androidx.navigation.NavController
import com.mobiquity.data.models.TodayForecast
import com.mobiquity.ui.theme.mobiquity_bg
import com.mobiquity.utils.Resource

@Composable
fun TodayDetailScreen(
    lat: Double?,
    lon: Double?,
    appID: String,
    viewModel: TodayDetailViewModel = hiltNavGraphViewModel()
) {
    val weatherInfo = produceState<Resource<TodayForecast>>(initialValue = Resource.Loading()) {
        value = viewModel.getPokemonList(lat, lon, appID)
    }.value
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(bottom = 16.dp)
    ){
        Text(
            text = "Temperature-"+weatherInfo.data?.main?.temp,
            fontWeight = FontWeight.Bold,
            color = mobiquity_bg,
            textAlign = TextAlign.Center,
            fontSize = 25.sp
        )
        Text(
            text = "Humidity-"+weatherInfo.data?.main?.humidity,
            fontWeight = FontWeight.Bold,
            color = mobiquity_bg,
            textAlign = TextAlign.Center,
            fontSize = 25.sp
        )
        Text(
            text = "Rain chances-"+weatherInfo.data?.weather?.get(0)?.description,
            fontWeight = FontWeight.Bold,
            color = mobiquity_bg,
            textAlign = TextAlign.Center,
            fontSize = 25.sp
        )

       Text(
            text = "wind speed-"+weatherInfo.data?.wind?.speed,
            fontWeight = FontWeight.Bold,
            color = mobiquity_bg,
            textAlign = TextAlign.Center,
            fontSize = 25.sp
       )
       Text(
            text = "wind deg-"+weatherInfo.data?.wind?.deg,
            fontWeight = FontWeight.Bold,
            color = mobiquity_bg,
            textAlign = TextAlign.Center,
            fontSize = 25.sp
        )
        Text(
            text = "wind gust-"+weatherInfo.data?.wind?.gust,
            fontWeight = FontWeight.Bold,
            color = mobiquity_bg,
            textAlign = TextAlign.Center,
            fontSize = 25.sp
        )
    }

}

@Composable
fun PokemonDetailTopSection(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    Box(
        contentAlignment = Alignment.TopStart,
        modifier = modifier
            .background(
                Brush.verticalGradient(
                    listOf(
                        Color.Black,
                        Color.Transparent
                    )
                )
            )
    ) {
        Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier
                .size(36.dp)
                .offset(16.dp, 16.dp)
                .clickable {
                    navController.popBackStack()
                }
        )
    }

}