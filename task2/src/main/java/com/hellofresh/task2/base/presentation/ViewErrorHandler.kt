package com.hellofresh.task2.base.presentation

import android.content.Context
import android.widget.Toast
import com.hellofresh.task2.R
import com.hellofresh.task2.base.data.erros.ConnectionException

fun Context.handleError(e: Exception) {
    if (e is ConnectionException)
        Toast.makeText(this, getString(R.string.msg_connection_error), Toast.LENGTH_LONG).show()
    else
        Toast.makeText(this, getString(R.string.msg_unknown_error), Toast.LENGTH_SHORT).show()
}
