package com.hellofresh.task1.manager.tag

import com.hellofresh.task1.model.Recipe
import org.junit.Test
import org.junit.Assert.*

class DefaultTagManagerTest {
    private val tagManager = DefaultTagManager()

    @Test
    fun `getRecipesWithTag, recipes is empty, return empty list`() {
        val recipes = emptyList<Recipe>()
        val tag = "test"
        val filteredRecipes = tagManager.getRecipesWithTag(recipes, tag)
        assertEquals(0, filteredRecipes.size)
    }

    @Test
    fun `getRecipesWithTag, recipes doesn't contain match, return empty`() {
        val recipes = listOf(
            Recipe(1, "Recipe 1", listOf("tag1")),
            Recipe(2, "Recipe 2", listOf("tag2"))
        )
        val tag = "test"
        val filteredRecipes = tagManager.getRecipesWithTag(recipes, tag)
        assertEquals(0, filteredRecipes.size)
    }

    @Test
    fun `getRecipesWithTag, recipes contains match, return matched recipes`() {
        val recipes = listOf(
            Recipe(1, "Recipe 1", listOf("tag1", "test")),
            Recipe(2, "Recipe 2", listOf("tag2")),
            Recipe(3, "Recipe 3", listOf("tag1", "test")),
            Recipe(4, "Recipe 4", listOf("tag3", "test"))
        )
        val tag = "test"
        val filteredRecipes = tagManager.getRecipesWithTag(recipes, tag)
        assertEquals(3, filteredRecipes.size)
        assertTrue(filteredRecipes.all { tag in it.tags })
    }
}