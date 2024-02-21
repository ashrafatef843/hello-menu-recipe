package com.hellofresh.task2.recipes.data.di

import com.hellofresh.task2.base.data.di.NetworkFactory
import com.hellofresh.task2.recipes.data.api.RecipesApi

object RecipesApiFactory {
    fun provideRecipesApi(): RecipesApi =
        NetworkFactory.retrofit.create(RecipesApi::class.java)
}
