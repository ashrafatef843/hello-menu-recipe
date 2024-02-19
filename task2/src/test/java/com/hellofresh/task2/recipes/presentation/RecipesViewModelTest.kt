package com.hellofresh.task2.recipes.presentation

import com.hellofresh.task2.base.TestCoroutineDispatcher
import com.hellofresh.task2.base.data.erros.ConnectionException
import com.hellofresh.task2.base.presentation.Fail
import com.hellofresh.task2.base.presentation.Loading
import com.hellofresh.task2.base.presentation.Success
import com.hellofresh.task2.recipes.domain.usecase.FetchingRecipes
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class RecipesViewModelTest {
    @MockK
    private lateinit var fetchingRecipes: FetchingRecipes
    private lateinit var recipesViewModel: RecipesViewModel
    private val dispatchers = TestCoroutineDispatcher()

    @Before
    fun init() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `get items, repo return list of items, submit items`() {
        // Prepare
        coEvery { fetchingRecipes() } returns listOf()

        //Execute
        // init viewmodel invoke get items inside init block
        recipesViewModel = RecipesViewModel(
            fetchingRecipes,
            dispatchers = dispatchers
        )

        //Assert
        assertTrue(recipesViewModel.recipes is Loading)
        dispatchers.testCoroutineScheduler.runCurrent()
        assertEquals(
            Success(listOf<RecipeView>()),
            recipesViewModel.recipes
        )
    }

    @Test
    fun `get items, repo throws connection exception, submit exception`() {
        // Prepare
        coEvery { fetchingRecipes() } throws ConnectionException()

        //Execute
        // init viewmodel invoke get items inside init block
        recipesViewModel = RecipesViewModel(
            fetchingRecipes,
            dispatchers = dispatchers
        )

        //Assert
        assertTrue(recipesViewModel.recipes is Loading)
        dispatchers.testCoroutineScheduler.runCurrent()
        with (recipesViewModel.recipes) {
            assertTrue(this is Fail)
            assertTrue((this as Fail<List<RecipeView>>).error is ConnectionException)
        }
    }
}
