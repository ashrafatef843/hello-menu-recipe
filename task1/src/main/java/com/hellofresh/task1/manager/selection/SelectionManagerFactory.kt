package com.hellofresh.task1.manager.selection

import com.hellofresh.task1.validator.SubscriptionLimitValidator

object SelectionManagerFactory {
    fun create(
        subscriptionLimitValidator: SubscriptionLimitValidator
    ): SelectionManager {
        return DefaultSelectionManager(subscriptionLimitValidator)
    }
}
