package com.hellofresh.task1

import com.hellofresh.task1.manager.selection.SelectionManager
import com.hellofresh.task1.manager.selection.SelectionManagerFactory
import com.hellofresh.task1.manager.tag.TagManager
import com.hellofresh.task1.manager.tag.TagManagerFactory
import com.hellofresh.task1.model.Recipe
import com.hellofresh.task1.model.Subscription

class Menu(
    private val recipes: List<Recipe>,
    private val subscription: Subscription,
    private val selectionManager: SelectionManager,
    private val tagManager: TagManager
) {
    fun selectRecipe(recipe: Recipe) {
        selectionManager.selectRecipe(subscription, recipe)
    }

    fun unselectRecipe(recipe: Recipe) {
        selectionManager.unselectRecipe(recipe)
    }

    fun getSelectedRecipeCount(): Int {
        return selectionManager.getSelectedRecipeCount()
    }

    fun getSelectedRecipes(): List<Recipe> {
        return selectionManager.getSelectedRecipes()
    }

    fun getRecipesWithTag(tag: String): List<Recipe> {
        return tagManager.getRecipesWithTag(recipes, tag)
    }
}
