package com.example.mediaappniklas2.datalayer.remote

import okhttp3.OkHttpClient
import okhttp3.Request

/**
 * @author s215698
 * This function requests a Json string from an API using the okhttp3 liberary to get a json string response
 * This response contains a list of films from the API database.
 *
 */
fun getMovieListAPIAcess() : String?{
    val url = "https://moviesdatabase.p.rapidapi.com/titles?startYear=1990&titleType=movie&endYear=2023&limit=3"

    val client = OkHttpClient()

    val request = Request.Builder()
        .url(url)
        .get()
        .addHeader("X-RapidAPI-Key", "254e2c3adfmsh3da535182efaf51p108f81jsn69df13326d34")
        .addHeader("X-RapidAPI-Host", "moviesdatabase.p.rapidapi.com")
        .build()

    val response = client.newCall(request).execute()

    val body = response.body?.string()


    return body

}