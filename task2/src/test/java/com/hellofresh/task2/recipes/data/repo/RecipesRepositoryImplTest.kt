package com.hellofresh.task2.recipes.data.repo

import com.hellofresh.task2.base.TestCoroutineDispatcher
import com.hellofresh.task2.base.data.erros.ConnectionException
import com.hellofresh.task2.data.entity.RecipeEntity
import com.hellofresh.task2.recipes.data.repo.datasource.RecipesRemoteDataSource
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test


class RecipesRepositoryImplTest {

    @MockK
    private lateinit var recipesRemoteDataSource: RecipesRemoteDataSource
    private lateinit var recipesRepositoryImpl: RecipesRepositoryImpl
    private val dispatchers = TestCoroutineDispatcher()

    @Before
    fun init() {
        MockKAnnotations.init(this)
        recipesRepositoryImpl = RecipesRepositoryImpl(recipesRemoteDataSource)
    }

    @Test
    fun `get items, item apis return list of items, return list of items`() =
        runTest(dispatchers.dispatcher) {
            // Prepare
            coEvery { recipesRemoteDataSource.getRecipes() } returns listOf()

            // Execute
            val items = recipesRepositoryImpl.getRecipes()

            //Assert
            assertEquals(listOf<RecipeEntity>(), items)
        }

    @Test(expected = ConnectionException::class)
    fun `get items, item apis throws exception, throws exception`() =
        runTest(dispatchers.dispatcher) {
            // Prepare
            coEvery { recipesRemoteDataSource.getRecipes() } throws  ConnectionException()

            // Execute
            recipesRepositoryImpl.getRecipes()
        }
}
