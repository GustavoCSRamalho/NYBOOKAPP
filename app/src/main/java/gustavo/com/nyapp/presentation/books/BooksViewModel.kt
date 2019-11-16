package gustavo.com.nyapp.presentation.books

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import gustavo.com.nyapp.R
import gustavo.com.nyapp.data.ApiService
import gustavo.com.nyapp.data.model.Book
import gustavo.com.nyapp.data.response.BookBodyResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BooksViewModel: ViewModel() {
    val booksLiveData: MutableLiveData<List<Book>> = MutableLiveData()
    val viewFlipperLiveData: MutableLiveData<Pair<Int,Int?>> =  MutableLiveData()

    fun getBooks(){

        ApiService.service.getBooks().enqueue(object : Callback<BookBodyResponse>{
            override fun onFailure(call: Call<BookBodyResponse>, t: Throwable) {
                viewFlipperLiveData.value = Pair(VIEW_FLIPPER_ERROR,R.string.books_error_500_generic)
            }

            override fun onResponse(
                call: Call<BookBodyResponse>,
                response: Response<BookBodyResponse>
            ) {
                when {
                    response.isSuccessful -> {
                        val books: MutableList<Book> = mutableListOf()
                        println("Response")
                        println(response.body())
                        response.body()?.let {  bookBodyResponse ->
                            println("body response ")
                            println(bookBodyResponse.bookResults)
                            bookBodyResponse.bookResults.forEach{
                                val book = it.bookDetailResponses[0].getBookModel()
                                books.add(book)
                            }
                        }
                        booksLiveData.value = books
                        viewFlipperLiveData.value = Pair(VIEW_FLIPPER_BOOKS,null)
                    }
                    response.code() == 401 -> viewFlipperLiveData.value = Pair(VIEW_FLIPPER_ERROR, R.string.books_error_401)
                    else -> viewFlipperLiveData.value = Pair(VIEW_FLIPPER_ERROR,R.string.books_error_400_generic)
                }
            }

        })

//        booksLiveData.value = createFakeBooks()
    }

    companion object{
        private const val VIEW_FLIPPER_BOOKS = 1
        private const val VIEW_FLIPPER_ERROR = 2
    }

}