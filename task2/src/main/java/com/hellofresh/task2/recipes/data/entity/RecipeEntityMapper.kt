package com.hellofresh.task2.recipes.data.entity

import com.hellofresh.task2.recipes.domain.Recipe

class RecipeEntityMapper {
    fun transform(recipeEntity: RecipeEntity): Recipe {
        return with(recipeEntity) {
            Recipe(
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