package com.example.mediaappniklas2

import android.graphics.drawable.Icon
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mediaappniklas2.navcontroller.Screen
import com.example.mediaappniklas2.ui.theme.MediaAppNiklas2Theme

@Composable
fun GradientButton(modifier: Modifier = Modifier, navController: NavController) {

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
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
            val buttonColor = remember { mutableStateOf(Color.Black) }
            var switchCheckedState1 by remember { mutableStateOf(false) }
            var switchCheckedState2 by remember { mutableStateOf(false) }
            var switchCheckedState3 by remember { mutableStateOf(false) }
            var switchCheckedState4 by remember { mutableStateOf(false) }
            var switchCheckedState5 by remember { mutableStateOf(false) }
            var switchCheckedState6 by remember { mutableStateOf(false) }

            TextButton(onClick = { }
            ) {
                Text(text = "Choose your streaming services:")
            }
            Row {
                Switch(
                    checked = switchCheckedState1,
                    onCheckedChange = { switchCheckedState1 = it })

                if (switchCheckedState1) {
                    Button(
                        onClick = {},
                        colors = ButtonDefaults.buttonColors(buttonColor.value),

                        ) { Text(text = "Netflix", color = Color.Green) }
                } else {
                    Button(
                        onClick = {},
                        colors = ButtonDefaults.buttonColors(buttonColor.value),

                        ) { Text(text = "Netflix", color = Color.LightGray) }

                }
            }
            Row {
                Switch(
                    checked = switchCheckedState2,
                    onCheckedChange = { switchCheckedState2 = it })
                if (switchCheckedState2) {


                    Button(
                        onClick = { },
                        colors = ButtonDefaults.buttonColors(buttonColor.value)
                    ) {
                        Text(text = "Viaplay", color = Color.Green)

                    }
                } else {
                    Button(
                        onClick = { },
                        colors = ButtonDefaults.buttonColors(buttonColor.value)
                    ) {
                        Text(text = "Viaplay", color = Color.LightGray)

                    }

                }


            }
            Row {
                Switch(
                    checked = switchCheckedState3,
                    onCheckedChange = { switchCheckedState3 = it })

                if (switchCheckedState3) {
                    Button(
                        onClick = { },
                        colors = ButtonDefaults.buttonColors(buttonColor.value)
                    ) {
                        Text(text = "Amazon Prime", color = Color.Green)
                    }
                } else {
                    Button(
                        onClick = { },
                        colors = ButtonDefaults.buttonColors(buttonColor.value)
                    ) {
                        Text(text = "Amazon Prime", color = Color.LightGray)
                    }

                }

            }
            Row {
                Switch(
                    checked = switchCheckedState4,
                    onCheckedChange = { switchCheckedState4 = it })
                if (switchCheckedState4) {
                    Button(
                        onClick = { },
                        colors = ButtonDefaults.buttonColors(buttonColor.value)
                    ) {
                        Text(text = "HBO", color = Color.Green)
                    }
                } else {
                    Button(
                        onClick = { },
                        colors = ButtonDefaults.buttonColors(buttonColor.value)
                    ) {
                        Text(text = "HBO", color = Color.LightGray)
                    }

                }


            }

            Row {
                Switch(
                    checked = switchCheckedState5,
                    onCheckedChange = { switchCheckedState5 = it })
                if (switchCheckedState5) {
                    Button(
                        onClick = {},
                        colors = ButtonDefaults.buttonColors(buttonColor.value)
                    ) {
                        Text(text = "Disney+", color = Color.Green)
                    }
                } else {
                    Button(
                        onClick = {},
                        colors = ButtonDefaults.buttonColors(buttonColor.value)
                    ) {
                        Text(text = "Disney+", color = Color.LightGray)
                    }

                }

            }
            Row {
                Switch(
                    checked = switchCheckedState6,
                    onCheckedChange = { switchCheckedState6 = it })
                if (switchCheckedState6) {
                    Button(
                        onClick = { },
                        colors = ButtonDefaults.buttonColors(buttonColor.value)
                    ) {
                        Text(text = "AppleTV", color = Color.Green)
                    }
                } else {
                    Button(
                        onClick = { },
                        colors = ButtonDefaults.buttonColors(buttonColor.value)
                    ) {
                        Text(text = "AppleTV", color = Color.LightGray)
                    }
                }

            }
            Button(
                onClick = { navController.navigate(Screen.Startskaerm.route) },
                colors = ButtonDefaults.buttonColors(Color.Blue)
            ) {
                Text(text = "Let's get it", color = Color.LightGray)
            }


            var netflixtjek by remember { mutableStateOf(false) }
            if (switchCheckedState1) {
                netflixtjek = true
            }

            var viaplaytjek by remember { mutableStateOf(false) }
            if (switchCheckedState2) {
                viaplaytjek = true
            }

            var amazontjek by remember { mutableStateOf(false) }
            if (switchCheckedState3) {
                amazontjek = true
            }

            var hbotjek by remember { mutableStateOf(false) }
            if (switchCheckedState4) {
                hbotjek = true
            }

            var disneytjek by remember { mutableStateOf(false) }
            if (switchCheckedState5) {
                disneytjek = true
            }

            var appeltvtjek by remember { mutableStateOf(false) }
            if (switchCheckedState6) {
                appeltvtjek = true
            }
        }
    }
}

/*@Preview()
@Composable
fun loginPreviewdark2() {
    MediaAppNiklas2Theme(darkTheme = true) {
        GradientButton()
        }
    }*/





