package com.hellofresh.task2.base.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.hellofresh.task2.R
import com.hellofresh.task2.base.presentation.handleError

@Composable
fun RetryItem(e: Exception, retryAction: ()-> Unit) {
    LocalContext.current.handleError(e)
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Text(text = stringResource(R.string.msg_connection_error))
        Button(
            modifier = Modifier
                .wrapContentSize()
                .padding(start = 20.dp, top = 8.dp, end = 20.dp, bottom = 8.dp),
            onClick = retryAction
        ) {
            Text(text = stringResource(R.string.title_retry), color = Color.White)
        }
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