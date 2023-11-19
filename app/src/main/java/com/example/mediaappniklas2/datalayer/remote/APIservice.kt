package com.example.mediaappniklas2.datalayer.remote

import org.slf4j.LoggerFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface MovieApiService {
    @Headers(
        "X-RapidAPI-Key:254e2c3adfmsh3da535182efaf51p108f81jsn69df13326d34",
        "X-RapidAPI-Host: moviesdatabase.p.rapidapi.com"
    )
    @GET("titles?titleType=movie&limit=50")
    suspend fun fetchMovies(
        @Query("startYear") startYear: Int,
        @Query("endYear") endYear: Int
    ): MovieApiResponse
    @GET("titles?titleType=movie&limit=50")
    suspend fun testMovies(
        @Query("startYear") startYear: Int,
        @Query("endYear") endYear: Int
    ) : Any

}

object RetrofitClient{
    private const val BASE_URL = "https://moviesdatabase.p.rapidapi.com/"

    val movieApiService: MovieApiService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(MovieApiService::class.java)
    }
}
val logger = LoggerFactory.getLogger("APIserviceKt")
suspend fun main() {
    try {
        // Replace startYear and endYear with your desired values

       // val response = RetrofitClient.movieApiService.fetchMovies(1990, 2023)
        val res = RetrofitClient.movieApiService.testMovies(1990,2023)
        logger.info("Json",res)
        // Convert the response object to a JSON string
      //  val jsonResponse = Gson().toJson(response)

        // Log the JSON response
        //logger.info("JSON Response", jsonResponse)
    } catch (e: Exception) {
        logger.error("Error", "Error fetching movies", e)
    }
}


/**
{
"page":1,
"next":"/titles?titleType=movie&limit=50&startYear=1990&endYear=2023&page=2",
"entries":50,
"results":[
{
"_id":"61e58509d8f3c0931e423367",
"id":"tt0439572",
"primaryImage":{
"id":"rm3802082561",
"width":2024,
"height":3000,
"url":"https://m.media-amazon.com/images/M/MV5BZWE2ZWE5MDQtMTJlZi00MTVjLTkxOTgtNmNiYjg2NDIxN2NhXkEyXkFqcGdeQXVyMTUzMTg2ODkz._V1_.jpg",
"caption":{
"plainText":"Michael Keaton, Ezra Miller, and Sasha Calle in The Flash (2023)",
"__typename":"Markdown"
},
"__typename":"Image"
},
"titleType":{
"displayableProperty":{
"value":{
"plainText":"",
"__typename":"Markdown"
},
"__typename":"DisplayableTitleTypeProperty"
},
"text":"Movie",
"id":"movie",
"isSeries":false,
"isEpisode":false,
"categories":[
{
"value":"movie",
"__typename":"TitleTypeCategory"
}
],
"canHaveEpisodes":false,
"__typename":"TitleType"
},
"titleText":{
"text":"The Flash",
"__typename":"TitleText"
},
"originalTitleText":{
"text":"The Flash",
"__typename":"TitleText"
},
"releaseYear":{
"year":2023,
"endYear":null,
"__typename":"YearRange"
}

 */
