package com.hellofresh.task2.recipes.domain.usecase

import com.hellofresh.task2.recipes.data.repo.RecipesRepositoryImpl
import com.hellofresh.task2.recipes.domain.RecipesRepository

class FetchingRecipes(
    private val recipesRepository: RecipesRepository = RecipesRepositoryImpl()
) {
    suspend operator fun invoke() = recipesRepository.getRecipes()
}