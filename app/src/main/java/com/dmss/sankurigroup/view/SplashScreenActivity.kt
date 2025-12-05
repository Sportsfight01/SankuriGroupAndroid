package com.dmss.sankurigroup.view

import android.content.Intent
import android.os.Bundle
import android.window.SplashScreen
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.magnifier
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.dmss.sankurigroup.MainActivity
import com.dmss.sankurigroup.R
import kotlinx.coroutines.delay

class SplashScreenActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            var showSplash by remember { mutableStateOf(true) }

            if (showSplash) {
                SplashScreen { showSplash = false }
            } else {

                startActivity(Intent(this, HomeScreenActivity::class.java))
                finish()            }
        }
    }

    @Composable
    fun SplashScreen(onTimeout:() -> Unit){

        LaunchedEffect(Unit) {

            delay(2000)
            onTimeout()
        }

        Box(modifier =
            Modifier.fillMaxSize().background(color = Color.White),
            contentAlignment = Alignment.Center) {
            Image(painter = painterResource(id = R.drawable.sankuri_log),
                contentDescription = "Splash",
                Modifier.fillMaxWidth(),
                contentScale = ContentScale.Inside)
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun SplashScreenPreview(){
        SplashScreen{}
    }

}