package com.example.mediaappniklas2

import android.graphics.drawable.Icon
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mediaappniklas2.ui.theme.MediaAppNiklas2Theme

@Composable
fun GradientButton(){

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ){

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
    ){
        TextButton(onClick = { /*TODO*/ }
        ) {
        Text(text = "Vælg dine streaming tjenester:")
        }
        ElevatedButton(onClick = { /*TODO*/ }
        ) {
           Text(text = "Netflix")
        }
        ElevatedButton(
            onClick = { /*TODO*/ }
        ) {
            Text(text = "Viaplay")
        }
        ElevatedButton(onClick = { /*TODO*/ }
        ) {
            Text(text = "Amazon Prime")
        }
        ElevatedButton(onClick = { /*TODO*/ }
        ) {
            Text(text = "HBO")
        }
        ElevatedButton(onClick = { /*TODO*/ }
        ) {
            Text(text = "Disney+")
        }
        ElevatedButton(onClick = { /*TODO*/ }
        ) {
            Text(text = "AppleTV")
        }
        Button(onClick = { /*TODO*/ }
        ) {
        Text(text = "Kom i gang" )
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





