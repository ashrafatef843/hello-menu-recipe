package com.hellofresh.task1

import com.hellofresh.task1.manager.selection.SelectionManagerFactory
import com.hellofresh.task1.manager.tag.TagManagerFactory
import com.hellofresh.task1.model.Recipe
import com.hellofresh.task1.model.Subscription
import com.hellofresh.task1.validator.SubscriptionLimitValidatorFactory


fun main() {
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

    // Output: 3
    println("Selected recipe count: ${menu.getSelectedRecipeCount()}")

    menu.unselectRecipe(recipe2)

    println("Selected recipes:")
    // Output: Spaghetti Carbonara, Vegetable Stir-Fry
    menu.getSelectedRecipes().forEach { println(it.title) }

    // Output: Caesar Salad, Vegetable Stir-Fry
    println("Recipes with tag 'quick': ${menu.getRecipesWithTag("quick").joinToString { it.title }}")
}