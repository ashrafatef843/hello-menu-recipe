package com.hellofresh.task2.recipes.data.repo

import com.hellofresh.task2.recipes.data.entity.RecipeEntityMapper
import com.hellofresh.task2.recipes.data.repo.datasource.RecipesRemoteDataSource
import com.hellofresh.task2.recipes.data.repo.datasource.RecipesRemoteDataSourceImpl
import com.hellofresh.task2.recipes.domain.repository.RecipesRepository
class RecipesRepositoryImpl(
    private val recipesRemoteDataSource: RecipesRemoteDataSource = RecipesRemoteDataSourceImpl(),
    private val recipeEntityMapper: RecipeEntityMapper = RecipeEntityMapper()
): RecipesRepository {
    override suspend fun getRecipes() = recipesRemoteDataSource.getRecipes()
        .map {
            recipeEntityMapper.transform(it)
        }
}
