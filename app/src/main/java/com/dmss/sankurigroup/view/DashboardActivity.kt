package com.dmss.sankurigroup.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.magnifier
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.material3.Text
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import com.dmss.sankurigroup.R
import kotlinx.coroutines.launch

class DashboardActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent { MainScreen()  }
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
                SideMenuContent(
                    onMenuItemClick = { /* handle item */ }
                )
            }
        ) {
            Scaffold (
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
                Box(modifier = Modifier.padding(paddingValues)) {
                    MainContent()
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
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            // Left menu icon
            Icon(
                painter = painterResource(id = R.drawable.menu),
                contentDescription = "Menu",
                modifier = Modifier
                    .size(28.dp)
                    .clickable { onLeftClick() }
            )

            Icon(
                painter = painterResource(id = R.drawable.sankuri_log),
                contentDescription = "Menu",
                modifier = Modifier
                    .clickable { onLeftClick() }
            )

            // Right home icon
            Icon(
                painter = painterResource(id = R.drawable.home),
                contentDescription = "Home",
                modifier = Modifier
                    .size(28.dp)
                    .clickable { onHomeClick() }
            )
        }
    }
    @Composable
    fun SideMenuContent(
        onMenuItemClick: (String) -> Unit
    ) {

        Column(
            modifier = Modifier
                .fillMaxHeight()
                .width(260.dp)
                .background(Color.White)
                .padding(16.dp)
                .padding(top = 80.dp),
        ) {
            Row(modifier = Modifier
                .padding(bottom = 10.dp),
                horizontalArrangement = Arrangement.Center) {
                Image(
                    painter = painterResource(id = R.drawable.user),
                    contentDescription = null,
                    modifier = Modifier
                        .size(30.dp)
                        .clip(RoundedCornerShape(12.dp)),
                    contentScale = ContentScale.Crop,


                )
                Text(
                text = "Durga Prasad",
                fontSize = 22.sp,
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 10.dp)


            )
        }


            DrawerItem("Home", R.drawable.home, onMenuItemClick)
            Spacer(modifier = Modifier.width(5.dp))
            HorizontalDivider(Modifier.background(color = Color.Gray))
            DrawerItem("Profile", R.drawable.home, onMenuItemClick)
            Spacer(modifier = Modifier.width(5.dp))
            HorizontalDivider(Modifier.background(color = Color.Gray))
            DrawerItem("Settings", R.drawable.home, onMenuItemClick)
            Spacer(modifier = Modifier.width(5.dp))
            HorizontalDivider(Modifier.background(color = Color.Gray))
            DrawerItem("Logout", R.drawable.home, onMenuItemClick)
            Spacer(modifier = Modifier.width(5.dp))
            HorizontalDivider(Modifier.background(color = Color.Gray))
        }
    }
    @Composable
    fun DrawerItem(
        label: String,
        icon: Int,
        onClick: (String) -> Unit
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onClick(label) }
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Icon(
                painter = painterResource(id = icon),
                contentDescription = label,
                tint = Color.White,
                modifier = Modifier.size(24.dp)
            )

            Spacer(modifier = Modifier.width(16.dp))

            Text(
                text = label,
                color = Color.Black,
                fontSize = 18.sp
            )

        }
    }
@Composable
fun MainContent(){

    Column(modifier = Modifier.fillMaxSize().padding(0.dp,30.dp,)) {

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
//            Text(text = "Welcome ", fontSize = 18.sp,
//                color = android.graphics.Color.BLACK,
//                fontWeight = FontWeight.SemiBold, Modifier.padding(10.dp))

            Text(
                text = "Welcome ",
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Black,
            )
            Text(
                text = "To ",
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Black,
            )
            Text(
                text = "Suncity ",
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Yellow,
            )

        }

        val sampleImages = listOf(
            R.drawable.three,
            R.drawable.two,
            R.drawable.one
        )
        Spacer(Modifier.padding(20.dp))
        DashboardComposes().ImageSlider(images = sampleImages)
        Spacer(Modifier.padding(10.dp))

        DashboardComposes().ResultsLazyRow()
        Spacer(Modifier.padding(10.dp))
        Text(text = "Stages", fontSize = 18.sp, color = Color.Black, fontWeight = FontWeight.SemiBold, modifier = Modifier.padding(10.dp,5.dp))

        DashboardComposes().SimpleGrid {
            startActivity(Intent(this@DashboardActivity, TaskProgressActivity::class.java))
        }

    }
}
    @Preview(showBackground = true)
    @Composable
    fun Preview(){
      MainScreen()
    }

}