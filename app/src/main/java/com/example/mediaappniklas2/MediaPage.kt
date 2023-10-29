package com.example.mediaappniklas2

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.rememberAsyncImagePainter
import com.example.mediaappniklas2.datalayer.Movie
import com.example.mediaappniklas2.datalayer.getMovieData

@Preview
@Composable
fun MediaPageAPP(){
    TopmenuBar()
    MediaPage()
}

@Composable
fun TopmenuBar(modifier: Modifier= Modifier
    .fillMaxSize()
    .wrapContentSize(
        Alignment.TopStart
    )
){
    Row (modifier = modifier,


        ) {
        Button(onClick = { /*TODO*/ }) {
            Text(stringResource(R.string.arrow))
        }

    }


}
@Composable
fun MediaPage(modifier: Modifier = Modifier){
    var movieid : Movie =  getMovieData()


    Column(modifier = Modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(painter = rememberAsyncImagePainter(movieid.imageRef),
            contentDescription = "A movie poster" ,
            modifier = Modifier
        )
        Text(movieid.Title)
        Text(movieid.releasedate)
    }

}