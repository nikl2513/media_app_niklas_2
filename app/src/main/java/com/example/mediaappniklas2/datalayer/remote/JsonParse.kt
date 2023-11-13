package com.example.mediaappniklas2.datalayer.remote

import org.json.JSONObject

class JsonParse {
    /**
     * @author s215698
     * This classed will contain functions that proccess the Json string returned by the API as it was hard to de serialize through a library
     * Basically it starts out by converting the String to a Json object and it then finds the results Array in the Json objects and
     * Scans each object in the array to find the Title, release date, and url for the movie poster
     *These 3 values are then passed into an object of the type movieDTO which is a dataclass
     * These data class objects are then added to a List<MovieDTO> which is then returned.
     */
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