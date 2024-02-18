package com.hellofresh.task2.data.api

import com.hellofresh.task2.data.entity.RecipeEntity
import retrofit2.http.GET

interface RecipesApi {
    @GET("https://hf-android-app.s3-eu-west-1.amazonaws.com/android-test/recipes.json")
    suspend fun getRecipes(): List<RecipeEntity>
}
