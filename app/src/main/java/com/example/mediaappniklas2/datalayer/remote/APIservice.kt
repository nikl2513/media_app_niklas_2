package com.example.mediaappniklas2.datalayer.remote

import com.example.mediaappniklas2.datalayer.ImdbApiResponse
import com.example.mediaappniklas2.datalayer.MovieApiResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApiService {
    @GET("titles?titleType=movie&limit=50")
    suspend fun fetchMovies(
        @Query("startYear") startYear: Int,
        @Query("endYear") endYear: Int,
        @Header("X-RapidAPI-Key") apiKey: String = "254e2c3adfmsh3da535182efaf51p108f81jsn69df13326d34",
        @Header("X-RapidAPI-Host") apiHost: String = "moviesdatabase.p.rapidapi.com"
    ): MovieApiResponse

    @Headers(
        "X-RapidAPI-Key:254e2c3adfmsh3da535182efaf51p108f81jsn69df13326d34",
        "X-RapidAPI-Host: moviesdatabase.p.rapidapi.com"
    )
    @GET("titles/search/title/{title}")
    suspend fun searchmovies(
        @Path("title") title: String,
        @Query("exact") exact: Boolean = true,
        @Query("titleType") titleType: String = "movie"
    ): MovieApiResponse

    @Headers(
        "X-RapidAPI-Key:254e2c3adfmsh3da535182efaf51p108f81jsn69df13326d34",
        "X-RapidAPI-Host: moviesdatabase.p.rapidapi.com"
    )
    @GET("titles/{id}/ratings")
    suspend fun getRatings(
        @Path("id") id: String
    ): ImdbApiResponse
}

object RetrofitClient {
    private const val BASE_URL = "https://moviesdatabase.p.rapidapi.com/"
    val movieApiService: MovieApiService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofit.create(MovieApiService::class.java)
    }
}