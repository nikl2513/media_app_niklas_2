package com.example.mediaappniklas2

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mediaappniklas2.Greeting
import com.example.mediaappniklas2.R

@Preview
@Composable
fun loginActivity() {
    OpstartMedButtonOgBaggrund()
}

@Composable
fun OpstartMedButtonOgBaggrund(
    modifier: Modifier = Modifier
        .fillMaxSize()
        .wrapContentSize(
            Alignment.BottomCenter
        )
) {
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Button(
            onClick = { /*TODO*/ },
            colors = ButtonDefaults.buttonColors(containerColor = Color.Blue)
        ) {
            Text(stringResource(id = R.string.tilmeld))
        }
        Spacer(modifier = Modifier.height(25.dp))
        Button(onClick = { /*TODO*/ },
            colors = ButtonDefaults.buttonColors(containerColor = Color.Blue))
        {
            Text(stringResource(id = R.string.logind))
        }
        Spacer(modifier = Modifier.height(150.dp))
    }

}