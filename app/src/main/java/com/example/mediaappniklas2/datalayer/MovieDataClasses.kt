package com.example.mediaappniklas2.datalayer

import com.google.gson.annotations.SerializedName

/**
 * @author s215698
 * The following  data classes that contains the information about a movie that is needed to display it in
 * the app.
 * Title: this is the Title of the movie
 * Release date: When the movie was released
 * imageRef: This is a url for the movie poster
 * These objects are used by the viewmodel to tell it what to show.
 */
data class MovieDTO(
        @SerializedName("_id")
        val id: String,

        @SerializedName("id")
        val imdbID : String,

        @SerializedName("titleText")
        val titleText : TitleText,

        @SerializedName("releaseYear")
        val releaseYear : ReleaseYear,

        @SerializedName("primaryImage")
        val imageRef : ImageRef
){

}


data class MovieData(
        val movieID : String,
        val imdbID : String,
        val title: String,
        val releasedate: String,
        val imageRef: String
)

fun convertToMovieData(movieDTO: MovieDTO): MovieData {
        val id = movieDTO.id
        val idIMDB = movieDTO.imdbID
        val title = movieDTO.titleText.text
        val releasedate = "${movieDTO.releaseYear?.year}" ?: "UnkownreleaseYear"
        val imageRef = movieDTO.imageRef?.url ?: "defaultImageUrl"

        return MovieData(id,idIMDB,title, releasedate, imageRef)
}
data class MovieApiResponse(
        val results : List<MovieDTO>
)
data class ApiSearchResponse(
        val result : MovieDTO
)

data class ImageRef(
        val id: String,
        val width: Int,
        val height: Int,
        val url: String,
        val caption: Caption
)

data class Caption(
        val plainText: String
)

data class TitleText(
        val text: String
)

data class ReleaseYear(
        val year: Int
)



