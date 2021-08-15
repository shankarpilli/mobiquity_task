package com.mobiquity.views

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.mobiquity.R
import com.mobiquity.ui.theme.MobiquityTheme
import com.mobiquity.ui.theme.mobiquity_bg

/**
 * This is a splash screen
 */
class SplashActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MobiquityTheme {
                splashImage()
            }

        }
        Handler().postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, 3000)
    }
}

/**
 * This function is used to show splash image
 */
@Composable
fun splashImage() {
    Surface(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth(),
        color = mobiquity_bg
    ) {
        Image(
            painterResource(id = R.drawable.mobiquity),
            contentDescription = stringResource(R.string.app_name)
        )
    }
}

/**
 * Default preview
 */
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MobiquityTheme {
        splashImage()
    }
}