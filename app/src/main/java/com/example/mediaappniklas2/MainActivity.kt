package com.example.mediaappniklas2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mediaappniklas2.ui.theme.MediaAppNiklas2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MediaAppNiklas2Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

//@Preview(showBackground = true)
@Composable
fun Movieknapper() {
    MediaAppNiklas2Theme{
        listOf(
            Button(onClick = { /*TODO*/ }) {
            }
        )
    }
}



@Preview
@Composable
fun Medieknapper(){
    MediaAppNiklas2Theme {
        Column {
            Row {
                Button(onClick = { /*TODO*/ },Modifier.size(85.dp,40.dp)) {
                    Text(text = "Netflix"
                    , fontSize = 10.sp)
                }
                Button(onClick = { /*TODO*/ }, Modifier.size(85.dp,40.dp)) {
                    Text(text = "Viaplay"
                        , fontSize = 10.sp)
                }
                Button(onClick = { /*TODO*/ },Modifier.size(85.dp,40.dp)) {
                    Text(text = "HBO"
                        , fontSize = 10.sp)
                }
            }
            Spacer(modifier = Modifier.height(5.dp))
            Row {
                Button(onClick = { /*TODO*/ },Modifier.size(85.dp,40.dp)) {
                    Text(text = "Disney +"
                        , fontSize = 10.sp)
                }
                Button(onClick = { /*TODO*/ },Modifier.size(85.dp,40.dp)) {
                    Text(text = "Apple tv"
                        , fontSize = 10.sp)
                }
                Button(onClick = { /*TODO*/ },Modifier.size(85.dp,40.dp)) {
                    Text(text = "Prime video"
                        , fontSize = 10.sp)
                }

            }

        }

    }
}