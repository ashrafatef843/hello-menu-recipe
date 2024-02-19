package com.hellofresh.task2.base.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Snackbar
import androidx.compose.material.TextButton
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.hellofresh.task2.R

@Composable
fun RetryItem(e: Exception, retryAction: () -> Unit) {
    val context = LocalContext.current
    val message = context.handleError(e)
    Snackbar(
        modifier = Modifier
            .padding(all = 8.dp),
        action = {
            TextButton(
                onClick = retryAction
            ) {
                Text(
                    text = context.getString(R.string.title_retry),
                    color = Color.White
                )
            }
        }
    ) {
        Text(
            text = message,
            color = Color.White
        )
    }
}

@Composable
fun CircularLoading() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator()
    }
}