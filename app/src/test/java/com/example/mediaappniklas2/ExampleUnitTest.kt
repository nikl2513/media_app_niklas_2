package com.example.mediaappniklas2

import com.example.mediaappniklas2.datalayer.remote.SearchOnMovie
import com.example.mediaappniklas2.datalayer.remote.jsonStringToDataClass
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }
    @Test
    fun testtitle(){
        var movieId = "tt0112641"
        var movieresult = SearchOnMovie(movieId)
        var movietitlestart = jsonStringToDataClass(movieresult.toString())
        var titleofmovie = movietitlestart.get(0).Title
        assertEquals(titleofmovie, "Casino")
    }

    @Test
    fun testDTOList(){




    }
}