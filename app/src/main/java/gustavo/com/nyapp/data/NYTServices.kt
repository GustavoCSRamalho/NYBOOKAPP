package gustavo.com.nyapp.data

import gustavo.com.nyapp.data.response.BookBodyResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NYTServices {

    @GET("lists.json")
    fun getBooks(
        @Query("api-key") apiKey: String = "fVnCRXiQtrvhrKkkgoOlAG9y8A8AKvve",
        @Query("list") list: String = "hardcover-fiction"
    ): Call<BookBodyResponse>
}