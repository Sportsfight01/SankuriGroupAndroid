package com.dmss.sankurigroup.view

import androidx.annotation.ColorRes
import androidx.compose.foundation.Canvas
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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dmss.sankurigroup.R
import com.dmss.sankurigroup.model.Timeline

class TaskProgressCompose {

    @Composable
    fun ProgressPagerScreen() {

        val images = listOf(0.2f, 0.5f)     // your progress values
        val pagerState = rememberPagerState(pageCount = { images.size })
        var currentPage by remember { mutableStateOf(0) }
        LaunchedEffect (pagerState) {
            snapshotFlow { pagerState.currentPage }
                .collect { page ->
                    currentPage = page
                }
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(colorResource(R.color.body_bg))
                .padding(10.dp)
                .fillMaxHeight()     // keeps fixed height for all devices
        ) {

            Column(modifier = Modifier.fillMaxSize()) {
                Row(modifier = Modifier.padding( 10.dp)) {
                    Text("Project", fontSize = 18.sp, color = Color.Black)
                    Text(" OverView", fontSize = 18.sp, color = colorResource(R.color.app_yellow))

                }
                // Horizontal Pager
                HorizontalPager(state = pagerState,) { page ->

                    Column(
                        modifier = Modifier
                            .padding(10.dp)
                            .background(Color.White, RoundedCornerShape(16.dp))
                            .fillMaxWidth()
                    ) {

                        // TOP ROW
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {

                            Column(horizontalAlignment = Alignment.Start) {
                                Text("Start Date", fontSize = 12.sp, color = Color.Gray)
                                Text("January, 2026",
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Medium
                                )
                            }

                            Column(horizontalAlignment = Alignment.End) {
                                Text("Stage", fontSize = 12.sp, color = Color.Gray)
                                Text("01",
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Medium
                                )
                            }
                        }

                        // PERFECT CIRCLE SECTION
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 5.dp, bottom = 30.dp),
                            contentAlignment = Alignment.Center
                        ) {

                            val stroke = 10.dp

                            Canvas(modifier = Modifier.size(120.dp,)) {

                                // background circle
                                drawArc(
                                    color = Color.Black,
                                    startAngle = -90f,
                                    sweepAngle = 360f,
                                    useCenter = false,
                                    style = Stroke(
                                        width = stroke.toPx(),
                                        cap = StrokeCap.Round
                                    )
                                )

                                // progress
                                drawArc(
                                    color = Color(0xFFFFC107),
                                    startAngle = -90f,
                                    sweepAngle = images[page] * 360,
                                    useCenter = false,
                                    style = Stroke(
                                        width = stroke.toPx(),
                                        cap = StrokeCap.Round
                                    )
                                )
                            }

                            // percentage text
                            Text(
                                "${(images[page] * 100).toInt()}%",
                                fontSize = 22.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(1.dp))

                // INDICATORS
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 10.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    repeat(images.size) { index ->
                        val selected = pagerState.currentPage == index

                        Box(
                            modifier = Modifier
                                .padding(4.dp)
                                .size(if (selected) 10.dp else 8.dp)
                                .clip(CircleShape)
                                .background(
                                    if (selected) Color.Red else Color.Gray.copy(alpha = 0.5f)
                                )
                        )
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))  // EXTRA SPACE AFTER INDICATOR

                TimeLines(generateList(currentPage))
            }
        }
    }
    fun generateList(currentPage:Int): List<Timeline>{

        println("currentPage:: $currentPage")
        val list = mutableListOf<Timeline>().apply {
//            for (i in 1..6) {
if(currentPage==0){
            add(
                Timeline(
                    name = "Timeline $2",
                    icon = R.drawable.complete,
                    date = "12-11-2025",
                    colour = R.color.app_yellow
                )
            )
            add(
                Timeline(
                    name = "Timeline $3",
                    icon = R.drawable.inprogress,
                    date = "12-11-2025",
                    colour = R.color.black
                )
            )
            add(
                Timeline(
                    name = "Timeline $4",
                    icon = R.drawable.pending,
                    date = "12-11-2025",
                    colour = R.color.dark_grey
                )
            )

            add(
                Timeline(
                    name = "Timeline $2",
                    icon = R.drawable.complete,
                    date = "12-11-2025",
                    colour = R.color.app_yellow
                )
            )
            add(
                Timeline(
                    name = "Timeline $4",
                    icon = R.drawable.pending,
                    date = "12-11-2025",
                    colour = R.color.dark_grey
                )
            )
            add(
                Timeline(
                    name = "Timeline $3",
                    icon = R.drawable.inprogress,
                    date = "12-11-2025",
                    colour = R.color.black
                )
            )

            }else{
    add(
        Timeline(
            name = "Timeline $66",
            icon = R.drawable.inprogress,
            date = "14-11-2025",
            colour = R.color.black
        )
    )
    add(
        Timeline(
            name = "Timeline $64",
            icon = R.drawable.pending,
            date = "12-11-2025",
            colour = R.color.dark_grey
        )
    )
    add(
        Timeline(
            name = "Timeline $25",
            icon = R.drawable.complete,
            date = "12-11-2025",
            colour = R.color.app_yellow
        )
    )

    add(
        Timeline(
            name = "Timeline $25",
            icon = R.drawable.complete,
            date = "12-11-2025",
            colour = R.color.app_yellow
        )
    )
    add(
        Timeline(
            name = "Timeline $64",
            icon = R.drawable.pending,
            date = "12-11-2025",
            colour = R.color.dark_grey
        )
    )
    add(
        Timeline(
            name = "Timeline $37",
            icon = R.drawable.inprogress,
            date = "12-11-2025",
            colour = R.color.black
        )
    )
            }
        }
        return list
    }

    @Composable
    fun TimeLines( list : List<Timeline> ) {



        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)
        ) {

            Text(
                "Timeline",
                fontSize = 18.sp,
                color = Color.Black,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(10.dp))

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(list) { item ->

                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(10.dp))
                            .background(colorResource(item.colour))
                            .fillMaxWidth()
                            .padding(top = 10.dp, bottom = 10.dp, start = 10.dp, end = 10.dp)
                    ) {
                        Row( verticalAlignment = Alignment.CenterVertically) {
                                Image(
                                    painter = painterResource(item.icon),
                                    contentDescription = "",
                                    alignment = Alignment.Center,
                                    modifier = Modifier.size(24.dp)
                                )

                            Column(modifier = Modifier.padding(start = 10.dp,5.dp)) {
                                Text(
                                    item.name,
                                    color = Color.White,
                                    fontSize = 16.sp,
                                )
                                Text(
                                    item.date,
                                    color = Color.White,
                                    fontSize = 12.sp,
                                    modifier = Modifier.padding(top=5.dp)
                                )
                            }
                        }
                    }
                }
            }
        }
    }


    /* @Composable
    fun CircularProgress(progress: Float) {

        Column() {

            Text(
                text = "Progress",
                fontSize = 18.sp,
                color = Color.Black, modifier = Modifier.padding(10.dp))

            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center,

                ) {

                val strokeWidth = 14.dp

                Canvas(modifier = Modifier.size(140.dp)) {

                    // Background circle (black)
                    drawArc(
                        color = Color.Black,
                        startAngle = -90f,
                        sweepAngle = 360f,
                        useCenter = false,
                        style = Stroke(width = strokeWidth.toPx(), cap = StrokeCap.Round)
                    )

                    // Progress arc (yellow)
                    drawArc(
                        color = Color(0xFFFFC107),
                        startAngle = -90f,
                        sweepAngle = progress * 360,
                        useCenter = false,
                        style = Stroke(width = strokeWidth.toPx(), cap = StrokeCap.Round)
                    )
                }

                // Percentage text inside
                Text(
                    text = "${(progress * 100).toInt()}%",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
//            modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }*/
    @Preview(showBackground = true)
    @Composable
   fun Preview(){
       val sampleImages = listOf(
           0.34f,0.45,0.55,0.66)
       ProgressPagerScreen()

   }

}