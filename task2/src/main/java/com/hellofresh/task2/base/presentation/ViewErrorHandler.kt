package com.hellofresh.task2.base.presentation

import android.content.Context
import com.hellofresh.task2.R
import com.hellofresh.task2.base.data.erros.ConnectionException

fun Context.handleError(e: Exception): String {
   return if (e is ConnectionException)
        getString(R.string.msg_connection_error)
    else
        getString(R.string.msg_unknown_error)
}
