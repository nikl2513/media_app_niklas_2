package com.example.mediaappniklas2.uiLayer.Opstart

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mediaappniklas2.R
import com.example.mediaappniklas2.navcontroller.Screen

@Composable
fun OpstartMedButtonOgBaggrund(
    modifier: Modifier = Modifier
        .fillMaxSize()
        .wrapContentSize(
            Alignment.BottomCenter
        ),
    navController: NavController
) {
    Image(
        painter = painterResource(id = R.drawable.background1),
        contentDescription = "",
        modifier.fillMaxSize(),
        contentScale = ContentScale.Crop
    )
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Image(painter = painterResource(id = R.drawable.logo), contentDescription = "")
        Image(
            painter = painterResource(id = R.drawable.logotext2),
            contentDescription = "",
            modifier = Modifier.size(300.dp)
        )
        Button(
            onClick = { navController.navigate(Screen.Startskaerm.route) },
            shape = RoundedCornerShape(5.dp),
            colors = ButtonDefaults.buttonColors(colorResource(id = R.color.deep_Blue)),
            modifier = Modifier
                .height(50.dp)
                .width(300.dp)
        ) {
            Text(
                stringResource(id = R.string.Go_find_a_movie),
                fontSize = 20.sp
            )
        }
        Spacer(modifier = Modifier.height(100.dp))
    }
}