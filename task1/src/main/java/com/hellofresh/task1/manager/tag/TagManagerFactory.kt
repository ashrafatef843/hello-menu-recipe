package com.hellofresh.task1.manager.tag

import com.hellofresh.task1.model.Recipe

object TagManagerFactory {
    fun create(): TagManager = DefaultTagManager()
}
