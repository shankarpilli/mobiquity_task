package com.mobiquity.views.city

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.google.android.libraries.maps.model.LatLng
import com.mobiquity.R
import com.mobiquity.models.MapModel
import com.mobiquity.navigation.NavigationItem
import com.mobiquity.utils.Constants.CITY_NAME
import com.mobiquity.utils.Constants.HELP_URL
import com.mobiquity.utils.Constants.LAT
import com.mobiquity.utils.Constants.LNG
import com.mobiquity.views.CityScreen
import dagger.hilt.android.AndroidEntryPoint

/**
 * This is a city data screen
 */
@AndroidEntryPoint
class CityActivity : ComponentActivity() {
    private lateinit var intentData: Intent
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        intentData = getIntent()
        setContent {
            CityDataScreen(intentData)
        }
    }
}

@Composable
fun CityDataScreen(intentData: Intent?) {
    Scaffold(
        topBar = { TopBar() }
    ) {
        CityScreen(
            intentData?.getStringExtra(CITY_NAME),
            intentData?.getDoubleExtra(LAT, 17.4603159),
            intentData?.getDoubleExtra(LNG, 78.4410889)
        )
    }
}


@Preview(showBackground = true)
@Composable
fun TopBarPreview() {
    TopBar()
}

@Composable
fun TopBar() {
    TopAppBar(
        title = { Text(text = stringResource(R.string.city_screen), fontSize = 18.sp) },
        backgroundColor = colorResource(id = R.color.mobiquity_bg),
        contentColor = Color.White
    )
}

@Preview(showBackground = true)
@Composable
fun CityDefaultPreview() {
    CityDataScreen(null)
}