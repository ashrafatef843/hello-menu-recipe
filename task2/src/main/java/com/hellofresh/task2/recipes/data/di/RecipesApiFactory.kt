package com.hellofresh.task2.data.di

import com.hellofresh.task2.base.data.di.NetworkFactory

object RecipesApiFactory {

    fun provideRecipesApi() = NetworkFactory.providesRecipesApi()
}
