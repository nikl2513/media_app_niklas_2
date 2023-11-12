package com.example.mediaappniklas2.datalayer.remote

import org.json.JSONObject

class JsonParse {

    fun jsonStringToDataClass( body : String) : List<MovieDTO>{
        val jsonObject = JSONObject(body)

// Access data within the "results" array
        val results = jsonObject.getJSONArray("results")

// Create a list of Movie objects to store the data
        val movies = mutableListOf<MovieDTO>()
        for (i in 0 until results.length()) {
            val result = results.getJSONObject(i)
            val titleText = result.getJSONObject("titleText").getString("text")
            val releaseYear = result.getJSONObject("releaseYear").getInt("year").toString()

            // Access data within the "primaryImage" object
            val primaryImage = result.getJSONObject("primaryImage")
            val imageUrl = primaryImage.getString("url")

            // Create a Movie object and add it to the list
            val movie = MovieDTO(titleText, releaseYear, imageUrl)
            movies.add(movie)
        }
        return movies
    }



}