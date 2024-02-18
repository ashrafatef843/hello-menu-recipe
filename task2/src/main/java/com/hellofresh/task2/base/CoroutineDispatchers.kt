package com.hellofresh.task2.base

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

interface CoroutineDispatchers {
    val IO: CoroutineDispatcher
    val Main: CoroutineDispatcher
}

class CoroutineDispatchersImpl(
    override val IO: CoroutineDispatcher = Dispatchers.IO,
    override val Main: CoroutineDispatcher = Dispatchers.Main
) : CoroutineDispatchers

val coroutineDispatcher: CoroutineDispatchers by lazy {
    CoroutineDispatchersImpl()
}
