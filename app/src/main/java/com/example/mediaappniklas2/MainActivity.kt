package com.example.mediaappniklas2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.mediaappniklas2.ui.theme.MediaAppNiklas2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MediaAppNiklas2Theme {
                    Greeting(name = "hello")
                }
            }
        }
    }


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Surface {
       Column(
           verticalArrangement = Arrangement.Center,
           horizontalAlignment = Alignment.CenterHorizontally,
           modifier = Modifier.fillMaxSize()
       ) {
           Text(
               text = "Hello $name!",
               modifier = modifier
           )
       }
    }
}

@Preview(showBackground = true, device = "id:Nexus One", showSystemUi = true)
@Composable
fun loginPreview() {
    MediaAppNiklas2Theme {
        Greeting(name = "hello")
    }

}
@Preview(showBackground = true, device = "id:Nexus One", showSystemUi = true)
@Composable
fun loginPreviewdark() {
    MediaAppNiklas2Theme(darkTheme = true) {
        Greeting(name = "hello")
    }

}