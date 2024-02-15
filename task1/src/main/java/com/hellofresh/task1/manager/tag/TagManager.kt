package com.hellofresh.task1.manager.tag

import com.hellofresh.task1.model.Recipe

interface TagManager {
    fun getRecipesWithTag(recipes: List<Recipe>, tag: String): List<Recipe>
}

class DefaultTagManager : TagManager {
    override fun getRecipesWithTag(recipes: List<Recipe>, tag: String): List<Recipe> {
        return recipes.filter { tag in it.tags }
    }
}