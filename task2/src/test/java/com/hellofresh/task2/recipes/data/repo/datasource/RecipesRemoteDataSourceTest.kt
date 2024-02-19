package com.hellofresh.task2.recipes.data.repo.datasource

import com.hellofresh.task2.base.TestCoroutineDispatcher
import com.hellofresh.task2.base.data.erros.ConnectionException
import com.hellofresh.task2.recipes.data.api.RecipesApi
import com.hellofresh.task2.recipes.data.entity.RecipeEntity
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.io.IOException

class RecipesRemoteDataSourceImplTest() {

    @MockK
    private lateinit var recipesApi: RecipesApi
    private lateinit var recipesRemoteDataSource: RecipesRemoteDataSource
    private val dispatchers = TestCoroutineDispatcher()

    @Before
    fun init() {
        MockKAnnotations.init(this)
        recipesRemoteDataSource = RecipesRemoteDataSourceImpl(recipesApi, dispatchers)
    }

    @Test
    fun `get recipes, recipes apis return list of recipes, return list of recipes`() =
        runTest(dispatchers.dispatcher) {
            // Prepare
            coEvery { recipesApi.getRecipes() } returns listOf()

            // Execute
            val items = recipesApi.getRecipes()

            //Assert
            assertEquals(listOf<RecipeEntity>(), items)
        }

    @Test(expected = ConnectionException::class)
    fun `get items, item apis throws exception, throws exception`() =
        runTest(dispatchers.dispatcher) {
            // Prepare
            coEvery { recipesApi.getRecipes() } throws IOException()

            // Execute
            recipesRemoteDataSource.getRecipes()
        }
}
