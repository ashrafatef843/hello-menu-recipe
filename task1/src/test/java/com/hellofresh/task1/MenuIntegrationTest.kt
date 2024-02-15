package com.hellofresh.task1

import com.hellofresh.task1.manager.selection.SelectionManagerFactory
import com.hellofresh.task1.manager.tag.TagManagerFactory
import com.hellofresh.task1.model.Recipe
import com.hellofresh.task1.model.Subscription
import com.hellofresh.task1.validator.SubscriptionLimitValidatorFactory
import org.junit.Test
import org.junit.Assert.*

class MenuIntegrationTest {
    @Test
    fun testMenuIntegration() {
        val recipe1 = Recipe(1, "Spaghetti Carbonara", listOf("hot", "italian"))
        val recipe2 = Recipe(2, "Caesar Salad", listOf("quick", "low-calories"))
        val recipe3 = Recipe(3, "Vegetable Stir-Fry", listOf("quick", "healthy"))
        val recipes = listOf(recipe1, recipe2, recipe3)
        val subscription = Subscription(1, "Monday", false)

        val subscriptionLimitValidator = SubscriptionLimitValidatorFactory.create()

        val selectionManager = SelectionManagerFactory.create(subscriptionLimitValidator)
        val tagManager = TagManagerFactory.create()

        val menu = Menu(recipes, subscription, selectionManager, tagManager)
        menu.selectRecipe(recipe1)
        menu.selectRecipe(recipe2)
        menu.selectRecipe(recipe3)
        assertEquals(3, menu.getSelectedRecipeCount())

        menu.unselectRecipe(recipe2)
        assertEquals(listOf(recipe1, recipe3), menu.getSelectedRecipes())

        assertEquals(
            listOf(recipe2, recipe3),
            menu.getRecipesWithTag("quick")
        )
    }
}
