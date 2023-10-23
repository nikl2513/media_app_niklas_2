package com.example.mediaappniklas2

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mediaappniklas2.ui.theme.BackgroundBlue
import com.example.mediaappniklas2.ui.theme.MediaAppNiklas2Theme
@Preview
@Composable
fun OpstartStartskærm() {
    MedieKnapper()
}
@Composable
fun MedieKnapper(modifier: Modifier = Modifier .background(BackgroundBlue) .fillMaxSize() .wrapContentSize(Alignment.Center)){
    MediaAppNiklas2Theme {
        Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
            Row {
                Button(onClick = { /*TODO*/ }, Modifier.size(85.dp,40.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Red)) {
                    Text(text = "Netflix", fontSize = 10.sp, textAlign = TextAlign.Center)
                }
                Spacer(modifier = Modifier.width(5.dp))
                Button(onClick = { /*TODO*/ }, Modifier.size(85.dp,40.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Magenta)) {
                    Text(text = "Viaplay"
                        , fontSize = 10.sp
                        ,textAlign = TextAlign.Center)
                }
                Spacer(modifier = Modifier.width(5.dp))
                Button(onClick = { /*TODO*/ }, Modifier.size(85.dp,40.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.DarkGray)) {
                    Text(text = "HBO"
                        , fontSize = 10.sp
                        ,textAlign = TextAlign.Center)
                }

            }
            Spacer(modifier = Modifier.height(5.dp))
            Row {
                Button(onClick = { /*TODO*/ }, Modifier.size(85.dp,40.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Blue)) {
                    Text(text = "Disney+"
                        , fontSize = 10.sp
                        ,textAlign = TextAlign.Center)
                }
                Spacer(modifier = Modifier.width(5.dp))
                Button(onClick = { /*TODO*/ }, Modifier.size(85.dp,40.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Black)) {
                    Text(text = "Apple tv"
                        , fontSize = 10.sp
                        ,textAlign = TextAlign.Center)
                }
                Spacer(modifier = Modifier.width(5.dp))
                Button(onClick = { /*TODO*/ }, Modifier.size(85.dp,40.dp)) {
                    Text(text = "Prime"
                        , fontSize = 10.sp
                        ,textAlign = TextAlign.Center)
                }

            }

        }
    }
}

