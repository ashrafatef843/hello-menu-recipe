package com.hellofresh.task1.validator

object SubscriptionLimitValidatorFactory {
    fun create(): SubscriptionLimitValidator = DefaultSubscriptionLimitValidator()
}
