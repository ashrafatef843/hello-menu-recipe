package com.hellofresh.task2.recipes.data.api

import com.hellofresh.task2.data.entity.RecipeEntity
import retrofit2.http.GET

interface RecipesApi {
    @GET("recipes.json")
    suspend fun getRecipes(): List<RecipeEntity>
}
