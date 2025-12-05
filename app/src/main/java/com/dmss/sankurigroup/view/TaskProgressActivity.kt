package com.dmss.sankurigroup.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import com.dmss.sankurigroup.R
import kotlinx.coroutines.launch

class TaskProgressActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent { MainScreen() }
    }
    @Composable
    fun MainScreen() {
        SideEffect {
            WindowCompat.setDecorFitsSystemWindows(this.window, false)
            window.statusBarColor = Color.Transparent.toArgb()

            WindowCompat.getInsetsController(
                window,
                window.decorView
            ).isAppearanceLightStatusBars = true
        }
        val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
        val scope = rememberCoroutineScope()

        ModalNavigationDrawer(
            drawerState = drawerState,
            drawerContent = {

            }
        ) {
            Scaffold (
                containerColor = colorResource(id = R.color.body_bg),
                        topBar = {
                    AppHeader(
                        title = "Dashboard",
                        onLeftClick = {
                            scope.launch { drawerState.open() }   // Open drawer
                        },
                        onHomeClick = { /* navigate to home */ }
                    )
                }
            ) { paddingValues ->


                Box(modifier = Modifier.padding(paddingValues)
                    ) {
                    val sampleImages = listOf(
                        0.34f,0.45,0.55,0.66)
                    TaskProgressCompose().ProgressPagerScreen()
                }
            }
        }
    }
    @Composable
    fun AppHeader(
        title: String,
        onLeftClick: () -> Unit,
        onHomeClick: () -> Unit
    ) {
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,

        ) {

            // Left menu icon
            Icon(
                painter = painterResource(id = R.drawable.back),
                contentDescription = "Back",
                modifier = Modifier
                    .size(28.dp)
                    .clickable { finish() }
            )
            Box(
                modifier = Modifier
                    .weight(1f) ,     // takes remaining width
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.sankuri_log),
                    contentDescription = "Title",
                    modifier = Modifier
                        .clickable { onLeftClick() },

                    )
            }
            Spacer(modifier = Modifier.size(28.dp))

        }
    }

    @Preview
    @Composable
    fun preview(){
        AppHeader(
            title = "Dashboard",
            onLeftClick = { /* handle left click */ },
            onHomeClick = { /* handle home click */ }
        )
    }

}