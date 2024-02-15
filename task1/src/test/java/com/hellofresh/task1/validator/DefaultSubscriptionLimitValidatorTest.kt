package com.hellofresh.task1.validator


import com.hellofresh.task1.model.Subscription
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class DefaultSubscriptionLimitValidatorTest {

    private val validator = DefaultSubscriptionLimitValidator()

    @Test
    fun `test isSelectionAllowed, with regular subscription and within limit, return true`() {
        val selectedRecipesCount = 2
        val subscription = Subscription(1, "Monday", isForFamily = false)
        assertTrue(validator.isSelectionAllowed(selectedRecipesCount, subscription))
    }

    @Test
    fun `test isSelectionAllowed, with regular subscription and at limit, return false`() {
        val selectedRecipesCount = 3
        val subscription = Subscription(1, "Monday", isForFamily = false)
        assertFalse(validator.isSelectionAllowed(selectedRecipesCount, subscription))
    }

    @Test
    fun `test isSelectionAllowed, with regular subscription and over limit, return false`() {
        val selectedRecipesCount = 4
        val subscription = Subscription(1, "Monday", isForFamily = false)
        assertFalse(validator.isSelectionAllowed(selectedRecipesCount, subscription))
    }

    @Test
    fun `test isSelectionAllowed, with family subscription and within limit, return true`() {
        val selectedRecipesCount = 4
        val subscription = Subscription(1, "Monday", isForFamily = true)
        assertTrue(validator.isSelectionAllowed(selectedRecipesCount, subscription))
    }

    @Test
    fun `test isSelectionAllowed, with family subscription and at limit, return false`() {
        val selectedRecipesCount = 5
        val subscription = Subscription(1, "Monday", isForFamily = true)
        assertFalse(validator.isSelectionAllowed(selectedRecipesCount, subscription))
    }

    @Test
    fun `test isSelectionAllowed, with family subscription and over limit, return false`() {
        val selectedRecipesCount = 6
        val subscription = Subscription(1, "Monday", isForFamily = true)
        assertFalse(validator.isSelectionAllowed(selectedRecipesCount, subscription))
    }

    @Test
    fun `test getSelectionLimit with regular subscription`() {
        val subscription = Subscription(1, "Monday", isForFamily = false)
        val expectedLimit = REGULAR_SELECTION_LIMIT_COUNT
        val actualLimit = validator.getSelectionLimit(subscription)
        assertTrue(expectedLimit == actualLimit)
    }

    @Test
    fun `test getSelectionLimit with family subscription`() {
        val subscription = Subscription(1, "Monday", isForFamily = true)
        val expectedLimit = FAMILY_SELECTION_LIMIT_COUNT
        val actualLimit = validator.getSelectionLimit(subscription)
        assertTrue(expectedLimit == actualLimit)
    }
}