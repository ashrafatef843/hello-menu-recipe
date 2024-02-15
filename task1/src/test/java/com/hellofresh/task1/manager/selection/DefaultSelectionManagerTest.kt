package com.hellofresh.task1.manager.selection

import com.hellofresh.task1.model.Recipe
import com.hellofresh.task1.model.Subscription
import com.hellofresh.task1.validator.SubscriptionLimitValidator
import org.junit.Test
import org.junit.Assert.*
import org.junit.Before

class DefaultSelectionManagerTest {
    private lateinit var selectionManager: DefaultSelectionManager
    private lateinit var subscriptionLimitValidator: SubscriptionLimitValidator

    @Test
    fun `select recipe, limit validator return true, mark recipe as selected`() {
        initDefaultSelectionManager(true)

        val recipe = Recipe(1, "Test Recipe", listOf("tag1", "tag2"))
        val subscription = Subscription(1, "Monday", isForFamily = false)
        selectionManager.selectRecipe(subscription, recipe)

        assertEquals(1, selectionManager.getSelectedRecipeCount())
    }

    @Test(expected = IllegalArgumentException::class)
    fun `select recipe, limit validator return false, throw exception`() {
        initDefaultSelectionManager(false)

        val recipe = Recipe(1, "Test Recipe", listOf("tag1", "tag2"))
        val subscription = Subscription(1, "Monday", isForFamily = false)
        selectionManager.selectRecipe(subscription, recipe)
    }

    @Test
    fun `unselect recipe, recipe is selected, mark recipe as unselected`() {
        initDefaultSelectionManager(true)
        val recipe = Recipe(1, "Test Recipe", listOf("tag1", "tag2"))
        val subscription = Subscription(1, "Monday", isForFamily = false)
        selectionManager.selectRecipe(subscription, recipe)

        selectionManager.unselectRecipe(recipe)

        assertEquals(0, selectionManager.getSelectedRecipeCount())
    }

    @Test
    fun `get selected recipe, after select two recipe, return 2`() {
        initDefaultSelectionManager(true)
        val recipe1 = Recipe(1, "Test Recipe 1", listOf("tag1", "tag2"))
        val recipe2 = Recipe(2, "Test Recipe 2", listOf("tag1", "tag2"))
        val subscription = Subscription(1, "Monday", isForFamily = false)
        selectionManager.selectRecipe(subscription, recipe1)
        selectionManager.selectRecipe(subscription, recipe2)

        assertEquals(2, selectionManager.getSelectedRecipeCount())
    }

    @Test
    fun `get selected recipe, after select two recipe, return 2 selected recipes`() {
        initDefaultSelectionManager(true)
        val recipe1 = Recipe(1, "Test Recipe 1", listOf("tag1", "tag2"))
        val recipe2 = Recipe(2, "Test Recipe 2", listOf("tag1", "tag2"))
        val subscription = Subscription(1, "Monday", isForFamily = false)
        selectionManager.selectRecipe(subscription, recipe1)
        selectionManager.selectRecipe(subscription, recipe2)

        val selectedRecipes = selectionManager.getSelectedRecipes()

        assertEquals(2, selectedRecipes.size)
        assertTrue(selectedRecipes.contains(recipe1))
        assertTrue(selectedRecipes.contains(recipe2))
    }

    private fun initDefaultSelectionManager(validatorFlag: Boolean) {
        // Mock SubscriptionLimitValidator for testing
        subscriptionLimitValidator = object : SubscriptionLimitValidator {
            override fun isSelectionAllowed(
                selectedRecipesCount:
                Int, subscription: Subscription
            ): Boolean {
                return validatorFlag // Mocking always allowed for testing
            }

            override fun getSelectionLimit(subscription: Subscription): Int {
                return 3 // Mocking selection limit for testing
            }
        }

        selectionManager = DefaultSelectionManager(subscriptionLimitValidator)
    }
}
