package com.usu.quoteme

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import com.usu.quoteme.models.Quote
import com.usu.quoteme.repositories.QuotesRepository

class MainStateHolder {
    var loading by mutableStateOf(false)
    var quote by mutableStateOf<Quote?>(null)
}

class MainViewModel(application: Application): AndroidViewModel(application) {
    val uiState = MainStateHolder()
    suspend fun loadRandomQuote() {
        uiState.loading = true
        val quote = QuotesRepository.getRandomQuote()
        uiState.quote = quote
        uiState.loading = false
    }
}