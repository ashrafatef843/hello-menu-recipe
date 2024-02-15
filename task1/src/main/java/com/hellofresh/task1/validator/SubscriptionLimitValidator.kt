package com.hellofresh.task1.validator

import com.hellofresh.task1.model.Subscription

const val REGULAR_SELECTION_LIMIT_COUNT = 3
const val FAMILY_SELECTION_LIMIT_COUNT = 5

interface SubscriptionLimitValidator {
    fun isSelectionAllowed(selectedRecipesCount: Int, subscription: Subscription): Boolean

    fun getSelectionLimit(subscription: Subscription): Int
}

class DefaultSubscriptionLimitValidator : SubscriptionLimitValidator {
    override fun isSelectionAllowed(
        selectedRecipesCount: Int,
        subscription: Subscription
    ): Boolean {
        return selectedRecipesCount < getSelectionLimit(subscription)

    }

    override fun getSelectionLimit(subscription: Subscription): Int {
        return if (subscription.isForFamily)
            FAMILY_SELECTION_LIMIT_COUNT
        else
            REGULAR_SELECTION_LIMIT_COUNT
    }
}
