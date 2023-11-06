package com.example.mediaappniklas2

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mediaappniklas2.Greeting
import com.example.mediaappniklas2.R

@Preview
@Composable
fun Opstartapp(onNavigateToTilmeld: (String) -> Unit, onNavigateToLogInd: (String) -> Unit) {
    OpstartMedButtonOgBaggrund(
        onNavigateToTilmeld = onNavigateToTilmeld,
        onNavigateToLogInd = onNavigateToLogInd
    )
}

@Composable
fun OpstartMedButtonOgBaggrund(
    modifier: Modifier = Modifier
        .fillMaxSize()
        .wrapContentSize(
            Alignment.BottomCenter
        ),
    onNavigateToTilmeld: (String) -> Unit,
    onNavigateToLogInd: (String) -> Unit

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
            onClick = { onNavigateToTilmeld },
            shape = RoundedCornerShape(5.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Blue),
            modifier = Modifier
                .height(50.dp)
                .width(300.dp)
        ) {
            Text(stringResource(id = R.string.tilmeld), fontSize = 20.sp)
        }
        Spacer(modifier = Modifier.height(30.dp))
        Button(
            onClick = { onNavigateToLogInd },
            shape = RoundedCornerShape(5.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Blue),
            modifier = Modifier
                .height(50.dp)
                .width(300.dp)
        )
        {
            Text(stringResource(id = R.string.logind), fontSize = 20.sp)
        }
        Spacer(modifier = Modifier.height(100.dp))
    }

}