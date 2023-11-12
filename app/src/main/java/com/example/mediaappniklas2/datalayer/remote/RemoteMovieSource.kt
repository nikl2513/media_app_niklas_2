package com.example.mediaappniklas2.datalayer.remote

import android.util.Log
import retrofit2.Retrofit
private val apikey: APIkey = APIkey()
private val key : String = apikey.getKey()
fun kage (){

    Log.v("DEBUG", key)

     val retrofit = Retrofit.Builder()
         .baseUrl("http://www.omdbapi.com/?apikey=$key")
         .build()

    val service  = retrofit.create(MovieDataSource::class.java)

}