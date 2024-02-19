package com.hellofresh.task2.recipes.presentation.mapper

import com.hellofresh.task2.recipes.domain.Recipe
import com.hellofresh.task2.recipes.presentation.RecipeView

class RecipeViewMapper {
    fun transform(recipe: Recipe): RecipeView {
        return with(recipe) {
            RecipeView(
                id,
                name,
                calories,
                carbos,
                description,
                difficulty,
                fats,
                headline,
                image,
                proteins,
                thumb,
                time
            )
        }
    }
}
