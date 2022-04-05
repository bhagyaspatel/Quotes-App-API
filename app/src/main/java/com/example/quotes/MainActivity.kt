package com.example.quotes

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {

    lateinit var viewModel : QuoteViewModel
//    val viewModel : QuoteViewModel by viewModels()
//    val QuoteList = ArrayList<Quote>()

    private val quoteText: TextView
        get() = findViewById(R.id.quoteText)

    private val quoteAuthor: TextView
        get() = findViewById(R.id.quoteAuthor)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        getData()
        viewModel = ViewModelProvider(this, QuoteViewModelFactory(application)).get(QuoteViewModel::class.java)
        setQuote(viewModel.getQuote())
    }

//    fun getData(){
//        val quoteUrl = "https://type.fit/api/quotes"
//
//        val JsonObjectRequest = JsonObjectRequest(
//            Request.Method.GET, quoteUrl, null,
//            {
//                val quoteJsonArray = it.getJSONArray("results")
//
//                for (i in 0 until quoteJsonArray.length() - 1){
//                    val newsJsonObject = quoteJsonArray.getJSONObject(i)
//                    val myNews = Quote ( //passing the data to News in order
//                        newsJsonObject.getString("text"), //Extracting data from API url that we want
//                        newsJsonObject.getString("author"), //All these names are from the API url
//                    )
//                    setQuote(myNews)
//                    QuoteList.add(myNews)
//                }
//                viewModel.getQuote(QuoteList)
//            },
//            {
//                Toast.makeText(this, "Something Went Wrong", Toast.LENGTH_LONG).show()
//            })
//
//        MySingleton.getInstance(this).addToRequestQueue(JsonObjectRequest)
//    }

    fun setQuote(quote: Quote){
        quoteAuthor.text = quote.author
        quoteText.text = quote.quote
    }

    fun prevQuote(view: android.view.View) {
//        return viewModel.prevQuote(QuoteList)
        setQuote(viewModel.prevQuote())
    }

    fun nextQuote(view: android.view.View) {
//        return viewModel.nextQuote(QuoteList)
        setQuote(viewModel.nextQuote())
    }

    fun share(view: android.view.View) {
        val intent = Intent (Intent.ACTION_SEND)
        intent.setType("text/plain")
        intent.putExtra(Intent.EXTRA_TEXT, viewModel.getQuote().quote)
        startActivity(intent)
    }
}