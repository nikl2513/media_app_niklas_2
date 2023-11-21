package com.example.mediaappniklas2

import android.graphics.drawable.Icon
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mediaappniklas2.ui.theme.MediaAppNiklas2Theme

@Composable
fun GradientButton(modifier: Modifier = Modifier){

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ){
        Image(
            painter = painterResource(id = R.drawable.background1),
            contentDescription = "",
            modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
    )
    {
        val buttonColorN = remember {mutableStateOf(Color.Black)}
        val buttonColorV = remember {mutableStateOf(Color.Black)}
        val buttonColorA = remember {mutableStateOf(Color.Black)}
        val buttonColorH = remember {mutableStateOf(Color.Black)}
        val buttonColorD = remember {mutableStateOf(Color.Black)}
        val buttonColorAP = remember {mutableStateOf(Color.Black)}
        TextButton(onClick = { }
        ) {
        Text(text = "Vælg dine streaming tjenester:")
        }
        Button(
            onClick = { buttonColorN.value = Color.White},
            colors = ButtonDefaults.buttonColors(buttonColorN.value),
            enabled = true

        ) {
           Text(text = "Netflix", color = Color.LightGray)
        }
       Button(
           onClick = { buttonColorV.value = Color.White},
           colors = ButtonDefaults.buttonColors(buttonColorV.value)
        ) {
            Text(text = "Viaplay", color = Color.LightGray)
        }
        Button(onClick = { buttonColorA.value = Color.White},
            colors = ButtonDefaults.buttonColors(buttonColorA.value)
        ) {
            Text(text = "Amazon Prime", color = Color.LightGray)
        }
        Button(onClick = { buttonColorH.value = Color.White},
            colors = ButtonDefaults.buttonColors(buttonColorH.value)
        ) {
            Text(text = "HBO", color = Color.LightGray)
        }
        Button(onClick = { buttonColorD.value = Color.White},
            colors = ButtonDefaults.buttonColors(buttonColorD.value)
        ) {
            Text(text = "Disney+", color = Color.LightGray)
        }
        Button(onClick = { buttonColorAP.value = Color.White},
            colors = ButtonDefaults.buttonColors(buttonColorAP.value)
        ) {
            Text(text = "AppleTV", color = Color.LightGray)
        }
        Button(onClick = {  },
            colors = ButtonDefaults.buttonColors(Color.Transparent)
        ) {
        Text(text = "Kom i gang" , color = Color.LightGray)
        }
    }
    }
    }


@Preview()
@Composable
fun loginPreviewdark2() {
    MediaAppNiklas2Theme(darkTheme = true) {
        GradientButton()
        }
    }





