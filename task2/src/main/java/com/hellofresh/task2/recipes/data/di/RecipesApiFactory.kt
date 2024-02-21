package com.hellofresh.task2.recipes.data.di

import com.hellofresh.task2.base.data.di.NetworkFactory
import com.hellofresh.task2.recipes.data.api.RecipesApi
import retrofit2.create

object RecipesApiFactory {

    fun provideRecipesApi(): RecipesApi =
        NetworkFactory.retrofit.create(RecipesApi::class.java)
}
