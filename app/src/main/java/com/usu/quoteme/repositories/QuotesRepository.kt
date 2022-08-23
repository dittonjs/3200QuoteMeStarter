package com.usu.quoteme.repositories

import com.usu.quoteme.models.Quote
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface QuotesApi {
    @GET("/random")
    suspend fun getRandomQuote() : Response<Quote>
}

object QuotesRepository {
    private val quotesApi: QuotesApi = Retrofit.Builder()
        .baseUrl("https://quotable.io")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(QuotesApi::class.java)

    suspend fun getRandomQuote(): Quote {
        val result = quotesApi.getRandomQuote()
        val quote = result.body()
        quote ?: throw RuntimeException("An error occurred")
        return quote
    }
}