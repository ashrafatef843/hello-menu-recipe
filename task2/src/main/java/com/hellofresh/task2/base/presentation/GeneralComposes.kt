package com.hellofresh.task2.base.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Snackbar
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.TextButton
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.hellofresh.task2.R
import kotlinx.coroutines.launch

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