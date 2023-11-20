package com.example.mediaappniklas2.remote

import com.example.mediaappniklas2.datalayer.remote.MovieApiService
import com.example.mediaappniklas2.datalayer.remote.RetrofitClient
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
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

    //@Test
    //@Throws(IOException::class, InterruptedException::class)
    /*
    fun fetchMovies_SuccessResponse() {
        // Set up a mock response with a sample JSON payload
        val mockResponse = MockResponse()
            .setResponseCode(200)
            .setBody("{ \"results\": [{ \"title\": \"The Flash\", \"releasedate\": \"2022-01-01\", \"imageRef\": \"path/to/image.jpg\" }] }")

        // Enqueue the mock response
        mockWebServer.enqueue(mockResponse)

        // Call the function you want to test
        val response = runBlocking {
            movieApiService.fetchMovies(1990, 2023)
        }



        // Assert that the response is not null and contains the expected data
        assertNotNull(response)
        assertNotNull(response.results)
        assertFalse(response.results.isEmpty())
        assertEquals("The Flash", response.results.first().title)

        // Print the actual JSON response

    }

     */

    // You can add more test cases to cover various scenarios (e.g., error responses, edge cases)
}