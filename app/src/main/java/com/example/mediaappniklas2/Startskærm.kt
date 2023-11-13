package com.example.mediaappniklas2

import android.graphics.drawable.Icon
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Share
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mediaappniklas2.ui.theme.BackgroundBlue
import com.example.mediaappniklas2.ui.theme.MediaAppNiklas2Theme
private data class Film(
    val filmName: String,
    val description: String,
    val imdb: Int,
    val genre: String,
    val image: Int

)

private val filmList = listOf(
    Film("Batman Begins","Batman 1",8,"Action",R.drawable.batman),
    Film("Batman Begins","Batman 1",2,"Action",R.drawable.batman),
    Film("Batman Begins","Batman 1",2,"Action",R.drawable.batman),
    Film("Batman Begins","Batman 1",2,"Action",R.drawable.batman),
    Film("Batman Begins","Batman 1",8,"Action",R.drawable.batman)
)

@Preview
@Composable
fun OpstartStartskærm(
    modifier: Modifier = Modifier
        .background(BackgroundBlue)
        .fillMaxSize()
        .wrapContentSize(Alignment.TopCenter)
) {
    LazyColumn(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        item {
            Topapp()
            Spacer(modifier = Modifier.height(35.dp))
            Image(painter = painterResource(id = R.drawable.mand), contentDescription = "")
            Spacer(modifier = Modifier.height(25.dp))

        }
        item {
            Text(text = "Tjenester", color = Color.White, fontSize = 20.sp)
            Spacer(modifier = Modifier.height(10.dp))
            MedieKnapper()
            Spacer(modifier = Modifier.height(25.dp))
        }
        item {
            Text(text = "Anbefalede", color = Color.White, fontSize = 20.sp)
            Spacer(modifier = Modifier.height(10.dp))
            verticalList()
            Spacer(modifier = Modifier.height(25.dp))
            Text(text = "Nyt og spændende", color = Color.White, fontSize = 20.sp)
            Spacer(modifier = Modifier.height(10.dp))
            verticalList()
            Spacer(modifier = Modifier.height(25.dp))
            Text(text = "Action", color = Color.White, fontSize = 20.sp)
            Spacer(modifier = Modifier.height(10.dp))
            verticalList()
        }
    }
}


@Composable
fun MedieKnapper(){
    MediaAppNiklas2Theme {
        Column {
            Row {
                Button(onClick = { /*TODO*/ }, Modifier.size(85.dp,40.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.DarkGray)) {
                    Text(text = "Netflix",
                        fontSize = 10.sp,
                        textAlign = TextAlign.Center)
                }
                Spacer(modifier = Modifier.width(5.dp))
                Button(onClick = { /*TODO*/ }, Modifier.size(85.dp,40.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.DarkGray)) {
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
                    colors = ButtonDefaults.buttonColors(containerColor = Color.DarkGray)) {
                    Text(text = "Disney+"
                        , fontSize = 10.sp
                        ,textAlign = TextAlign.Center)
                }
                Spacer(modifier = Modifier.width(5.dp))
                Button(onClick = { /*TODO*/ }, Modifier.size(85.dp,40.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.DarkGray)) {
                    Text(text = "Apple tv"
                        , fontSize = 10.sp
                        ,textAlign = TextAlign.Center)
                }
                Spacer(modifier = Modifier.width(5.dp))
                Button(onClick = { /*TODO*/ }, Modifier.size(85.dp,40.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.DarkGray)) {
                    Text(text = "Prime"
                        , fontSize = 10.sp
                        ,textAlign = TextAlign.Center)
                }

            }

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Topapp(){
        Row() {
            IconButton(onClick = {/*TODO*/ }) {
                Icon(
                    imageVector = Icons.Default.List,
                    contentDescription = null
                )
            }

            Spacer(modifier = Modifier.width(120.dp))
            Image(painter = painterResource(id = R.drawable.logo1), contentDescription = "")
            Spacer(modifier = Modifier.width(120.dp))
            IconButton(onClick = {/*TODO*/ }) {
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = null
                )
            }


        }

}
@Composable
private fun MovieItem(film : Film) {
     val imageidd: Int = film.image
    Image(modifier = Modifier.clickable { /*TODO*/ },painter = painterResource(id = imageidd), contentDescription = "")

}

@Composable
private fun verticalList() {
    LazyRow {
        items(filmList) { film ->
            if (0 < film.imdb) {
                Spacer(modifier = Modifier.width(10.dp))
                MovieItem(film = film)
            }
        }
    }
}















