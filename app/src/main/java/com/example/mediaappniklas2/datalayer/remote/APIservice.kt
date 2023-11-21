package com.example.mediaappniklas2.datalayer.remote

import com.example.mediaappniklas2.datalayer.MovieApiResponse
import com.example.mediaappniklas2.datalayer.MovieDTO
import com.example.mediaappniklas2.datalayer.MovieData
import com.example.mediaappniklas2.datalayer.convertToMovieData
import org.slf4j.LoggerFactory
import retrofit2.Call
import retrofit2.Response
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
     fun fetchMovies(
        @Query("startYear") startYear: Int,
        @Query("endYear") endYear: Int
    ): Call<MovieApiResponse>

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
 fun main() {
    try {
        // Replace startYear and endYear with your desired values

        val response : Response<MovieApiResponse> = RetrofitClient.movieApiService.fetchMovies(1990, 2023).execute()
        if(response.isSuccessful){
            val movieApiResponse : MovieApiResponse? = response.body()
            if(movieApiResponse != null){
                val resultsList : List<MovieDTO> = movieApiResponse.results

                logger.info("123: $resultsList",resultsList)
                val movieDataList : List<MovieData> = resultsList.map { convertToMovieData(it) }
                val firstmovietitle : String = movieDataList[0].title
                val lastmovietitle : String = movieDataList.last().title
                logger.info("First movie Title: $firstmovietitle")
                logger.info("Last movie Title: $lastmovietitle")
            }
        }
        logger.info("",response)
     //   val resultslist : List<MovieDTO> = response.javaClass

      //  logger.info("",resultslist)
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
