package gustavo.com.nyapp.data.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import gustavo.com.nyapp.data.model.Book
import java.util.*

@JsonClass(generateAdapter = true)
data class BookDetailsResponse(
    @Json(name = "title")
    val title: String,
    @Json(name = "author")
    val author: String,
    @Json(name = "description")
    val description: String){

    fun getBookModel() = Book(
        title = this.title,
        author = this.author,
        description = this.description)
}

