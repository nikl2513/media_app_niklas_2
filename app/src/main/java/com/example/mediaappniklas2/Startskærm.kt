package com.example.mediaappniklas2

import android.graphics.drawable.Icon
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
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
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mediaappniklas2.navcontroller.Screen
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
    Film("Batman Begins","Batman 1",8,"Action",R.drawable.batman),
    Film("The Mandalorian", "The Mandalorian series", 9,"Action", R.drawable.mand),
    Film("The Mandalorian", "The Mandalorian series", 9,"Action", R.drawable.mand),
    Film("The Mandalorian", "The Mandalorian series", 9,"Action", R.drawable.mand)
)

@Composable
fun OpstartStartskærm(
    modifier: Modifier = Modifier
        .background(Color.DarkGray)
        .fillMaxSize()
        .wrapContentSize(Alignment.TopCenter),
    navController: NavController
) {
    LazyColumn(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        item {
            Topapp(navController)
            Spacer(modifier = Modifier.height(35.dp))
            verticalListTopHighlight()
            Spacer(modifier = Modifier.height(25.dp))

        }
        item {
            Text(text = "Streaming services", color = Color.White, fontSize = 20.sp)
            Spacer(modifier = Modifier.height(10.dp))
            MedieKnapper(navController)
            Spacer(modifier = Modifier.height(25.dp))
        }
        item {
            Text(text = "Recommended", color = Color.White, fontSize = 20.sp)
            Spacer(modifier = Modifier.height(10.dp))
            verticalList(navController)
            Spacer(modifier = Modifier.height(25.dp))
            Text(text = "New and exciting", color = Color.White, fontSize = 20.sp)
            Spacer(modifier = Modifier.height(10.dp))
            verticalList(navController)
            Spacer(modifier = Modifier.height(25.dp))
            Text(text = "Action", color = Color.White, fontSize = 20.sp)
            Spacer(modifier = Modifier.height(10.dp))
            verticalList(navController)
        }
    }
}


@Composable
fun MedieKnapper(
    navController: NavController
){
    MediaAppNiklas2Theme {
        Column {
            Row {
                Button(onClick = {navController.navigate(Screen.MediaPage.route)}, Modifier.size(85.dp,40.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Gray)) {
                    Text(text = "Netflix",
                        fontSize = 10.sp,
                        textAlign = TextAlign.Center)
                }
                Spacer(modifier = Modifier.width(5.dp))
                Button(onClick = {navController.navigate(Screen.MediaPage.route)}, Modifier.size(85.dp,40.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Gray)) {
                    Text(text = "Viaplay"
                        , fontSize = 10.sp
                        ,textAlign = TextAlign.Center)
                }
                Spacer(modifier = Modifier.width(5.dp))
                Button(onClick = {navController.navigate(Screen.MediaPage.route)}, Modifier.size(85.dp,40.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Gray)) {
                    Text(text = "HBO"
                        , fontSize = 10.sp
                        ,textAlign = TextAlign.Center)
                }

            }
            Spacer(modifier = Modifier.height(5.dp))
            Row {
                Button(onClick = {navController.navigate(Screen.MediaPage.route)}, Modifier.size(85.dp,40.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Gray)) {
                    Text(text = "Disney+"
                        , fontSize = 10.sp
                        ,textAlign = TextAlign.Center)
                }
                Spacer(modifier = Modifier.width(5.dp))
                Button(onClick = {navController.navigate(Screen.MediaPage.route)}, Modifier.size(85.dp,40.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Gray)) {
                    Text(text = "Apple tv"
                        , fontSize = 10.sp
                        ,textAlign = TextAlign.Center)
                }
                Spacer(modifier = Modifier.width(5.dp))
                Button(onClick = {navController.navigate(Screen.MediaPage.route)}, Modifier.size(85.dp,40.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Gray)) {
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
fun Topapp(navController: NavController){
        Row() {
            IconButton(onClick = {navController.navigate(Screen.MediaPage.route)}) {
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
private fun MovieItem(film : Film, navController: NavController) {
     val imageidd: Int = film.image
    Image(modifier = Modifier.clickable {navController.navigate(Screen.MediaPage.route)},painter = painterResource(id = imageidd), contentDescription = "")

}

@Composable
private fun verticalList(navController: NavController) {
    LazyRow {
        items(filmList) { film ->
            if (0 < film.imdb) {
                Spacer(modifier = Modifier.width(10.dp))
                MovieItem(film = film, navController)
            }
        }
    }
}

@Composable
private fun verticalListTopHighlight(
    modifier: Modifier = Modifier
    .background(Color.DarkGray)
    .fillMaxSize()
    .wrapContentSize(Alignment.TopCenter)) {
    LazyRow(modifier = modifier) {
        items(filmList) { film ->
            if (8 < film.imdb) {
                Spacer(modifier = Modifier.width(10.dp))
                Box(
                    modifier
                        .size(360.dp, 190.dp)
                        .background(Color.DarkGray)
                        .clip(shape = RoundedCornerShape(10.dp))
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.mand),
                        contentDescription = "",
                        modifier
                            .fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                }
            }
        }
    }
}














