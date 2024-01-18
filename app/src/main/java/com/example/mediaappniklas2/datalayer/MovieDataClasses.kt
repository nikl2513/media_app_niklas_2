package com.example.mediaappniklas2.datalayer

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

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
    val imdbID: String,
    @SerializedName("titleText")
    val titleText: TitleText,
    @SerializedName("releaseYear")
    val releaseYear: ReleaseYear,
    @SerializedName("primaryImage")
    val imageRef: ImageRef
)

data class ImdbDTO(
    @SerializedName("tconst")
    val imdbID: String,
    @SerializedName("averageRating")
    val averageRating: Float,
    @SerializedName("numVotes")
    val numVotes: Float
)

@Serializable
data class MovieData(
    val movieID: String,
    val imdbID: String,
    val title: String,
    val releasedate: String,
    val imageRef: String
)

fun convertToMovieData(movieDTO: MovieDTO): MovieData {
    val id = movieDTO.id
    val idIMDB = movieDTO.imdbID
    val title = movieDTO.titleText.text
    val releasedate = "${movieDTO.releaseYear?.year}" ?: "UnkownreleaseYear"
    val imageRef = movieDTO.imageRef?.url
        ?: "https://cdn.discordapp.com/attachments/1014540895955193889/1192754307427221524/360_F_462936689_BpEEcxfgMuYPfTaIAOC1tCDurmsno7Sp.png?ex=65aa3a03&is=6597c503&hm=fac7c7b03804e45c786d6605d23c096e5ff436e3d92472ce3e93a8558e8045f1&"
    return MovieData(id, idIMDB, title, releasedate, imageRef)
}

data class MovieApiResponse(
    val results: List<MovieDTO>
)

data class ImdbApiResponse(
    val results: ImdbDTO
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