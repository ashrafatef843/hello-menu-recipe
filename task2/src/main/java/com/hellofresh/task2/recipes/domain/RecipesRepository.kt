package com.hellofresh.task2.recipes.domain

interface RecipesRepository {
    suspend fun getRecipes(): List<Recipe>
}