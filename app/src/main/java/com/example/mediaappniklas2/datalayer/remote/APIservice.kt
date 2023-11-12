package com.example.mediaappniklas2.datalayer.remote

import retrofit2.http.GET


interface MovieDataSource {

    @GET("")
    suspend fun fetchMovies(): List<MovieDTO>


}