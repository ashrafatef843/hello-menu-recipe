package com.hellofresh.task2.recipes.domain.repository

import com.hellofresh.task2.recipes.domain.Recipe

interface RecipesRepository {
    suspend fun getRecipes(): List<Recipe>
}