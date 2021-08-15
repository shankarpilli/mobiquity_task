package com.mobiquity.views

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.location.Address
import android.location.Geocoder
import android.util.Log
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.room.Room
import com.daanidev.googlemapinlist.utils.rememberMapViewWithLifecycle
import com.google.android.libraries.maps.CameraUpdateFactory
import com.google.android.libraries.maps.model.LatLng
import com.mobiquity.R
import com.mobiquity.ui.theme.mobiquity_bg
import com.mobiquity.utils.Constants.HELP_URL
import com.mobiquity.views.today.TodayDetailScreen
import com.google.maps.android.ktx.awaitMap
import com.mobiquity.database.AppDatabase
import com.mobiquity.database.enties.LocationEntity
import com.mobiquity.models.MapModel
import com.mobiquity.utils.Constants.DB_TABLE_NAME
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*
import android.os.AsyncTask
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.ui.res.stringResource
import com.mobiquity.views.home.ShowListActivity

var lat: Double = 0.0
var lng: Double = 0.0
var cityName: String = ""

/**
 * THis function is used to show map.
 */
@Composable
fun HomeScreen(objMap: MapModel) {
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.white))
            .wrapContentSize(Alignment.Center)
    ) {
        val mapView = rememberMapViewWithLifecycle()
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(colorResource(id = R.color.white))
                .wrapContentSize(Alignment.Center)
        ) {
            AndroidView({ mapView }) { mapView ->
                CoroutineScope(Dispatchers.Main).launch {
                    val map = mapView.awaitMap()
                    val destination = objMap.latLng
                    map.moveCamera(CameraUpdateFactory.newLatLngZoom(destination, 10f))
                    map.setOnCameraMoveListener {
                        val position = map.cameraPosition.target
                        lat = position.longitude
                        lng = position.longitude
                        Log.d("MapActivity", "Position $lat")
                    }
                    map.setOnCameraIdleListener {
                        val geocoder: Geocoder = Geocoder(context, Locale.getDefault())
                        val addresses: List<Address> =
                            geocoder.getFromLocation(
                                map.cameraPosition.target.latitude,
                                map.cameraPosition.target.longitude, 1
                            )
                        cityName = addresses.get(0).getAddressLine(0)
                        Log.d("MapActivity", "CityName $cityName")
                    }
                }
            }
            Image(
                painterResource(id = R.drawable.add_location),
                contentDescription = "map",
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(50.dp)
            )
            Image(
                painterResource(id = R.drawable.view_list),
                contentDescription = "view_list",
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(0.dp, 0.dp, 10.dp, 60.dp)
                    .clickable(enabled = true, onClick = {
                        val intent = Intent(context, ShowListActivity::class.java)
                        context.startActivity(intent)
                    })
                    .size(50.dp)

            )
            Button(
                onClick = {
                    addLocationToDb(context)
                },
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(0.dp, 0.dp, 0.dp, 60.dp)
            ) {
                Text(text = stringResource(R.string.bookmark))
            }
        }
    }
}

/**
 * This function is used to add location to room db.
 * Here Asynctask is used because,
 * I want to show and represent I know even old functions also
 * I place of that we can use kotlin coroutines.
 */
fun addLocationToDb(context: Context) {
    val db = Room.databaseBuilder(
        context,
        AppDatabase::class.java, DB_TABLE_NAME
    ).build()
    val locationDao = db.locationDao()
    AsyncTask.execute {
        locationDao.insertAll(
            LocationEntity(
                UUID.randomUUID().toString(), lat, lng, cityName
            )
        )
    }
    Toast.makeText(
        context, "$cityName added to local db",
        Toast.LENGTH_SHORT
    ).show()
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen(MapModel(LatLng(17.4128084, 78.12784), "Hyderabad"))
}

@Composable
fun CityScreen(cityName: String?, lat: Double?, lang: Double?) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.white))
            .wrapContentSize(Alignment.Center)
    ) {
        Text(
            text = "Today Information \n $cityName",
            fontWeight = FontWeight.Bold,
            color = mobiquity_bg,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center,
            fontSize = 25.sp
        )

        TodayDetailScreen(
            lat,
            lang,
            "fae7190d7e6433ec3a45285ffcf55c86"
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CityScreenPreview() {
    CityScreen(
        stringResource(R.string.hyderabad), 17.4603159,
        78.4410889
    )
}

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun HelpScreen(urlToRender: String) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.white))
            .wrapContentSize(Alignment.Center)
    ) {
        AndroidView(factory = {
            WebView(it).apply {
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
                webViewClient = WebViewClient()
                loadUrl(urlToRender)
            }
        }, update = {
            it.loadUrl(urlToRender)
        })
    }
}

@Preview(showBackground = true)
@Composable
fun HelpScreenPreview() {
    HelpScreen(HELP_URL)
}