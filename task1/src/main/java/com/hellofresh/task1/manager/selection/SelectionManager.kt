package com.hellofresh.task1.manager.selection

import com.hellofresh.task1.model.Recipe
import com.hellofresh.task1.model.Subscription
import com.hellofresh.task1.validator.SubscriptionLimitValidator

interface SelectionManager {
    fun selectRecipe(subscription: Subscription, vararg recipes: Recipe)
    fun unselectRecipe(vararg recipes: Recipe)
    fun getSelectedRecipeCount(): Int
    fun getSelectedRecipes(): List<Recipe>
}

class DefaultSelectionManager(
    private val subscriptionLimitValidator: SubscriptionLimitValidator
) : SelectionManager {
    private val selectedRecipes = mutableListOf<Recipe>()
    override fun selectRecipe(subscription: Subscription, vararg recipes: Recipe) {
        if (!subscriptionLimitValidator.isSelectionAllowed(
                selectedRecipes.size, subscription)
            ) {
            throw IllegalArgumentException(
                "You cannot select more than ${
                    subscriptionLimitValidator.getSelectionLimit(subscription)
                } recipes."
            )
        }
        recipes.forEach {
            selectedRecipes.add(it)
        }
    }

    override fun unselectRecipe(vararg recipes: Recipe) {
        recipes.forEach {
            selectedRecipes.remove(it)
        }
    }

    override fun getSelectedRecipeCount(): Int {
        return selectedRecipes.size
    }

    override fun getSelectedRecipes(): List<Recipe> {
        return selectedRecipes.toList()
    }
}
