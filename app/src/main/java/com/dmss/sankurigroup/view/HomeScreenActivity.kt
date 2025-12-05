package com.dmss.sankurigroup.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import com.dmss.sankurigroup.MainActivity
import com.dmss.sankurigroup.R

class HomeScreenActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContent {
            HomeScreen()
        }
    }

    override fun onStop() {
        super.onStop()
        println("LifeCycleMethod:: onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        println("LifeCycleMethod:: onDestroy")

    }

@Composable
fun HomeScreen() {
    val context = LocalContext.current
    SideEffect {
        WindowCompat.setDecorFitsSystemWindows(this.window, false)
        window.statusBarColor = Color.Transparent.toArgb()

        WindowCompat.getInsetsController(
            window,
            window.decorView
        ).isAppearanceLightStatusBars = true
    }
    Box(modifier = Modifier.fillMaxSize()) {


        Image(
            painter = painterResource(R.drawable.home_screen),
            contentDescription = "",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 50.dp)
                .clip(shape = RoundedCornerShape(12.dp))
                .clickable(
                    onClick = {
                        startActivity(Intent(context, DashboardActivity::class.java))

                    }
                )

        ) {
//            Card(
//                modifier = Modifier
//                    .width(230.dp)
//                    .height(200.dp),
//                shape = RoundedCornerShape(22.dp),
//                colors = CardDefaults.cardColors(containerColor = Color(0xFF66635C))
//            ){
//
//            }
            Image(
                painter = painterResource(R.drawable.suncity),
                contentDescription = "",
                modifier = Modifier
                    .width(150.dp)
                    .height(180.dp),
                contentScale = ContentScale.Crop
            )
        }

    }
}
@Preview(showBackground = true)
@Composable
fun preview(){
    HomeScreen()
}
}