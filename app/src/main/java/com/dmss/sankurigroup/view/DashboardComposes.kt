package com.dmss.sankurigroup.view

import android.R.attr.onClick
import android.content.Intent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dmss.sankurigroup.R

class DashboardComposes {


    @Composable
    fun ImageSlider(
        images: List<Int>,
        modifier: Modifier = Modifier
    ) {
        val pagerState = rememberPagerState(pageCount = { images.size })

        Box(
            modifier = modifier
                .fillMaxWidth()
                .height(220.dp)
        ) {

            // Pager
            HorizontalPager (
                state = pagerState
            ) { page ->
                Image(
                    painter = painterResource(id = images[page]),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(10.dp)
                        .clip(RoundedCornerShape(2.dp)),
                    contentScale = ContentScale.Crop
                )
            }

            // Indicators
            Row (
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 10.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                repeat(images.size) { index ->
                    val isSelected = pagerState.currentPage == index

                    Box(
                        modifier = Modifier
                            .padding(3.dp)
                            .size(if (isSelected) 10.dp else 8.dp)
                            .clip(CircleShape)
                            .background(
                                if (isSelected) Color.Red
                                else Color.White.copy(alpha = 0.6f)
                            )
                    )
                }
            }
        }
    }

    @Composable
    fun ResultsLazyRow() {
        val listItems = listOf(
            "Discovery", "Planning", "Execution", "Results")
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            LazyRow(
                modifier = Modifier.padding(10.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                items(listItems) { item ->
                    var bgColour = Color.LightGray
                    if (item == "Planning") {
                        bgColour = Color.Yellow
                    }
                    Box(
                        modifier = Modifier
                            .padding(end = 3.dp)
                            .width(85.dp)              // ⭐ FIXED WIDTH
                            .clip(RoundedCornerShape(5.dp))
                            .clickable { }
                            .background(bgColour)
                            .padding(10.dp)


                    ) {
                        Text(
                            text = item,
                            fontSize = 12.sp,
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                }
            }
        }
    }
    @Composable
    fun SimpleGrid(onItemClick: (String) -> Unit) {

        val items = (1..6).map { "Stage $it" }

        LazyVerticalGrid (
            columns = GridCells.Fixed(3), // 3 columns
            modifier = Modifier
                .fillMaxSize()
                .padding(5.dp),
            horizontalArrangement = Arrangement.spacedBy(5.dp),
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            items(items) { item ->
                var bgColour = Color.Gray
                if(item=="Stage 3"){
                    bgColour = Color.Blue
                }
                if(item=="Stage 5"){
                    bgColour = Color.Yellow
                }
                Box(
                    modifier = Modifier
                        .background(bgColour, RoundedCornerShape(10.dp))
                        .fillMaxWidth()
                        .clickable { onItemClick(item) }
                        .aspectRatio(1f), // Square box
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = item, color = Color.White, fontSize = 18.sp)
                }
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun Preview(){
        val sampleImages = listOf(
            R.drawable.three,
            R.drawable.two,
            R.drawable.one
        )
        Spacer(Modifier.padding(20.dp))
        SimpleGrid(onItemClick = {

        })
    }

}