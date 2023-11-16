package com.example.mediaappniklas2.datalayer.remote

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import okhttp3.OkHttpClient
import okhttp3.Request

fun SearchOnMovie(MovieId: String): String? {
    val client = OkHttpClient()

    val request = Request.Builder()
        .url("https://moviesdatabase.p.rapidapi.com/titles/" + MovieId)
        .get()
        .addHeader("X-RapidAPI-Key", "9e49ad0c7dmsha84b7507ff226d9p174243jsn236970c68134")
        .addHeader("X-RapidAPI-Host", "moviesdatabase.p.rapidapi.com")
        .build()

    val response = client.newCall(request).execute()

    val body = response.body?.string()

    return body
}

@Composable
fun printmovie(modifier: Modifier = Modifier
    .fillMaxSize()
    .wrapContentSize(Alignment.BottomCenter)) {
    var movieId = "tt0112641"
    var movieresult = SearchOnMovie(movieId)
    var movietitlestart = jsonStringToDataClass(movieresult.toString())
    var titleofmovie = movietitlestart.get(0).Title
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = titleofmovie)
    }
}

@Preview
@Composable
fun seMovie(){
    printmovie()
}