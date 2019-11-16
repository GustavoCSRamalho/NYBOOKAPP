package gustavo.com.nyapp.presentation.books

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import gustavo.com.nyapp.data.ApiService
import gustavo.com.nyapp.data.model.Book
import gustavo.com.nyapp.data.response.BookBodyResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BooksViewModel: ViewModel() {
    val booksLiveData: MutableLiveData<List<Book>> = MutableLiveData()

    fun getBooks(){


        ApiService.service.getBooks().enqueue(object : Callback<BookBodyResponse>{
            override fun onFailure(call: Call<BookBodyResponse>, t: Throwable) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onResponse(
                call: Call<BookBodyResponse>,
                response: Response<BookBodyResponse>
            ) {
                if(response.isSuccessful){
                    val books: MutableList<Book> = mutableListOf()
                    println("Response")
                    println(response.body())
                    response.body()?.let {  bookBodyResponse ->
                        println("body response ")
                        println(bookBodyResponse.bookResults)
                        bookBodyResponse.bookResults.forEach{
                            val book: Book = Book(
                                title = it.bookDetailResponses[0].title,
                                author = it.bookDetailResponses[0].author,
                                description = it.bookDetailResponses[0].description
                            )
                            books.add(book)
                        }
                    }
                    booksLiveData.value = books
                }
            }

        })

//        booksLiveData.value = createFakeBooks()
    }

}