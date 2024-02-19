package com.hellofresh.task2.base.presentation

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object Utilities {

    fun getCurrentDate(
        pattern: String = DATE_TIME_UI_PATTERN
    ): String {
        return LocalDateTime
            .now()
            .format(DateTimeFormatter.ofPattern(pattern))
    }
}
