package com.hellofresh.task2.recipes.data.repo.datasource

import com.hellofresh.task2.base.CoroutineDispatchers
import com.hellofresh.task2.base.coroutineDispatcher
import com.hellofresh.task2.base.data.erros.handleHttpException
import com.hellofresh.task2.recipes.data.api.RecipesApi
import com.hellofresh.task2.data.di.RecipesApiFactory
import com.hellofresh.task2.data.entity.RecipeEntity
import kotlinx.coroutines.withContext
import java.lang.Exception

interface RecipesRemoteDataSource {
    suspend fun getRecipes(): List<RecipeEntity>
}

class RecipesRemoteDataSourceImpl(
    private val recipesApi: RecipesApi = RecipesApiFactory.provideRecipesApi(),
    private val dispatchers: CoroutineDispatchers = coroutineDispatcher
): RecipesRemoteDataSource {
    override suspend fun getRecipes(): List<RecipeEntity> {
        return withContext(dispatchers.IO) {
            try {
                recipesApi.getRecipes()
            } catch (e: Exception) {
                throw e.handleHttpException()
            }
        }
    }
}
