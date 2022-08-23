package com.usu.quoteme

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.usu.quoteme.repositories.QuotesRepository
import com.usu.quoteme.ui.theme.QuoteMeTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            QuoteMeTheme {
                val viewModel: MainViewModel = viewModel()
                val state = viewModel.uiState
                val scope = rememberCoroutineScope()
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .clickable {
                            scope.launch {
                                viewModel.loadRandomQuote()
                            }
                        }
                        .padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    if (state.loading) {
                        Text(
                            text = "Loading...",
                            style = TextStyle(fontSize = 60.sp),
                        )
                    } else if (state.quote != null) {
                        Text(
                            text = state.quote?.content ?: "",
                            style = TextStyle(fontSize = 50.sp),
                            fontStyle = FontStyle.Italic,
                            textAlign = TextAlign.Center
                        )
                        Text(
                            text = "- ${state.quote?.author}",
                            style = TextStyle(fontSize = 30.sp),
                        )
                    } else {
                        Text(
                            text = "Click for a life changing quote",
                            style = TextStyle(fontSize = 60.sp),
                        )
                    }
                }
            }
        }
    }
}
