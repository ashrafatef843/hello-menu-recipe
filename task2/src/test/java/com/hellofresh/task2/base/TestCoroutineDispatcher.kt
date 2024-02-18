package com.hellofresh.task2.base

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestCoroutineScheduler
import kotlinx.coroutines.test.TestDispatcher

class TestCoroutineDispatcher(
    val testCoroutineScheduler: TestCoroutineScheduler = TestCoroutineScheduler(),
    val dispatcher : TestDispatcher = StandardTestDispatcher(testCoroutineScheduler),
    override val IO: CoroutineDispatcher = dispatcher,
    override val Main: CoroutineDispatcher = dispatcher
) : CoroutineDispatchers
