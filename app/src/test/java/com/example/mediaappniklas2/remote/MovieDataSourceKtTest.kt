package com.example.mediaappniklas2.remote

import com.example.mediaappniklas2.datalayer.MovieApiResponse
import com.example.mediaappniklas2.datalayer.MovieDTO
import com.example.mediaappniklas2.datalayer.MovieData
import com.example.mediaappniklas2.datalayer.convertToMovieData
import com.example.mediaappniklas2.datalayer.remote.MovieApiService
import com.example.mediaappniklas2.datalayer.remote.RetrofitClient
import com.example.mediaappniklas2.uiLayer.mediapage.MediaPageViewModel
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

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
        fun `fetchMovies success response`() = runBlocking {

                // Set up a mock response with a successful JSON body
                val mockResponse = MockResponse()
                    .setResponseCode(200)
                    .setBody("{ \"results\": [{ \"title\": \"The Flash\", \"releasedate\": \"2022-01-01\", \"imageRef\": \"path/to/image.jpg\" }] }")

                // Enqueue the mock response
                mockWebServer.enqueue(mockResponse)

                val movieApiResponse: MovieApiResponse = RetrofitClient.movieApiService.fetchMovies(1990, 2023)

                // Process the response as before
                val resultsList: List<MovieDTO> = movieApiResponse.results

                // Map MovieDTO to MovieData
                val movieDataList: List<MovieData> = resultsList.map { convertToMovieData(it) }

                // Verify specific details about the movies
                assertEquals(50, movieDataList.size)
            assertEquals("61e58509d8f3c0931e423367",movieDataList.first().movieID)
                assertEquals("The Flash", movieDataList.first().title)
                assertEquals("2023", movieDataList.first().releasedate)
                assertEquals("https://m.media-amazon.com/images/M/MV5BZWE2ZWE5MDQtMTJlZi00MTVjLTkxOTgtNmNiYjg2NDIxN2NhXkEyXkFqcGdeQXVyMTUzMTg2ODkz._V1_.jpg",
                    movieDataList.first().imageRef)

                // Additional assertions or verifications if needed
            }







    @Test
        fun searchMovie() = runBlocking{
            val searchWord = "The Wolf of Wall Street" // Use the unencoded search word
            val call: MovieApiResponse =
                RetrofitClient.movieApiService.searchmovies(searchWord)

            val searchApiResponse = call.results

            if (searchApiResponse != null) {


                if (searchApiResponse.isNotEmpty()) {
                    val movieData: List<MovieData> = searchApiResponse.map { convertToMovieData(it) }
                    val movieTitle: String = movieData.first().title
                   println(movieTitle)
                    println(searchWord)



                    assertTrue(movieTitle == searchWord)
                }

            }
        }

    @Test
    fun mediaRating_IsCorrect() = runBlocking {
        val movieID = ("tt0439572")
        val TheFlashRating = 6.7F

        MediaPageViewModel.searchRating(movieID)
        val MediaPageRating = MediaPageViewModel.currentImdb.value?.averageRating

        assertEquals(TheFlashRating, MediaPageRating)
    }



}

