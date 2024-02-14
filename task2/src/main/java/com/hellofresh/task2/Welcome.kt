package com.hellofresh.task2

import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun Welcome(name: String) {
    Column {
        Text(name)
    }
}

@Preview
@Composable
private fun WelcomePreview() {
    MaterialTheme {

        Welcome(name = "HelloFresh")
    }
}
