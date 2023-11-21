package com.example.mediaappniklas2.remote

import com.example.mediaappniklas2.datalayer.MovieApiResponse
import com.example.mediaappniklas2.datalayer.MovieDTO
import com.example.mediaappniklas2.datalayer.MovieData
import com.example.mediaappniklas2.datalayer.convertToMovieData
import com.example.mediaappniklas2.datalayer.remote.MovieApiService
import com.example.mediaappniklas2.datalayer.remote.RetrofitClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Call

@RunWith(JUnit4::class)
class MovieApiServiceTest {

    private lateinit var mockWebServer: MockWebServer
    private lateinit var movieApiService: MovieApiService

    @Before
    fun setup() {
        mockWebServer = MockWebServer()
        movieApiService = RetrofitClient.movieApiService
    }

    @After
    fun teardown() {
        mockWebServer.shutdown()
    }

    @Test
    fun fetchMovies_SuccessResponse() {

        val mockResponse = MockResponse()
            .setResponseCode(200)
            .setBody("{ \"results\": [{ \"title\": \"The Flash\", \"releasedate\": \"2022-01-01\", \"imageRef\": \"path/to/image.jpg\" }] }")


        mockWebServer.enqueue(mockResponse)


        val call: Call<MovieApiResponse> = RetrofitClient.movieApiService.fetchMovies(1990, 2023)
        val apiResponse = call.execute()
        assert(apiResponse.isSuccessful)
        val movieApiResponse: MovieApiResponse? = apiResponse.body()
        if (movieApiResponse != null) {
            val resultsList: List<MovieDTO> = movieApiResponse.results


            val movieDataList: List<MovieData> = resultsList.map { convertToMovieData(it) }
            val firstmovietitle: String = movieDataList.first().title
            val lastmovietitle: String = movieDataList.last().title
            assertEquals("The Flash", firstmovietitle)
            assertEquals("The Out-Laws", lastmovietitle)

        }


    }



        @Test
        fun searchMovie() {
            val searchWord = "the big short" // Use the unencoded search word
            val call: Call<MovieApiResponse> =
                RetrofitClient.movieApiService.searchmovies(searchWord)
            val searchResponse = call.execute()
            val searchApiResponse = searchResponse.body()

            if (searchApiResponse != null) {
                val searchResultList: List<MovieDTO> = searchApiResponse.results

                if (searchResultList.isNotEmpty()) {
                    val movieData: List<MovieData> = searchResultList.map { convertToMovieData(it) }
                    val movieTitle: String = movieData.first().title
                    assertEquals("the big short", movieTitle)
                }
            }
        }


    }

