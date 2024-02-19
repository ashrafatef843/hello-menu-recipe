package com.hellofresh.task2.recipes.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hellofresh.task2.base.CoroutineDispatchers
import com.hellofresh.task2.base.coroutineDispatcher
import com.hellofresh.task2.base.presentation.AsyncState
import com.hellofresh.task2.base.presentation.Fail
import com.hellofresh.task2.base.presentation.Loading
import com.hellofresh.task2.base.presentation.Success
import com.hellofresh.task2.base.presentation.Uninitialized
import com.hellofresh.task2.recipes.domain.usecase.FetchingRecipes
import com.hellofresh.task2.recipes.presentation.mapper.RecipeViewMapper
import kotlinx.coroutines.launch

class RecipesViewModel(
    private val fetchingRecipes: FetchingRecipes = FetchingRecipes(),
    private val recipeViewMapper: RecipeViewMapper = RecipeViewMapper(),
    private val dispatchers: CoroutineDispatchers = coroutineDispatcher
) : ViewModel() {
    var recipes by mutableStateOf<AsyncState<List<RecipeView>>>(Uninitialized)
        private set

    init {
        getRecipes()
    }

    fun getRecipes() {
        recipes = Loading()

        viewModelScope.launch(dispatchers.Main) {
            recipes = try {
                val recipes = fetchingRecipes()
                    .map { recipeViewMapper.transform(it) }
                Success(recipes)
            } catch (e: Exception) {
                Fail(e)
            }
        }
    }
}
