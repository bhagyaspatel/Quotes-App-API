package com.example.quotes

import android.content.Context
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import kotlin.random.Random

class QuoteViewModel (val context: Context) : ViewModel(){
    private var idx = Random.nextInt(1, 200)
    private var quoteList : Array<Quote> = emptyArray()

    init{
        quoteList = loadQuotesFromAssets()
    }

    private fun loadQuotesFromAssets(): Array<Quote> {
        val inputStream = context.assets.open("quotes.json")
        val size : Int = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()
        val json = String (buffer, Charsets.UTF_8)
        val gson = Gson()
        return gson.fromJson(json, Array<Quote>::class.java)
    }

    fun getQuote() = quoteList [idx]

    fun prevQuote() = quoteList [(--idx + quoteList.count()) % quoteList.count()]

    fun nextQuote() = quoteList [++idx % quoteList.count()]
}