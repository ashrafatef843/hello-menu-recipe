package com.hellofresh.task2.recipes.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.hellofresh.task2.R
import com.hellofresh.task2.base.presentation.CircularLoading
import com.hellofresh.task2.base.presentation.Fail
import com.hellofresh.task2.base.presentation.Loading
import com.hellofresh.task2.base.presentation.RetryItem
import com.hellofresh.task2.base.presentation.Success
import com.hellofresh.task2.recipes.presentation.theme.ui.HellofreshTestTheme


@Composable
fun RecipesScreen(
    recipesViewModel: RecipesViewModel
) {
    val recipes = recipesViewModel.recipes

    Box(Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = if (recipes().isNullOrEmpty())
                Modifier
                    .wrapContentSize()
                    .align(Alignment.Center)
            else
                Modifier.fillMaxSize()
        ) {
            when (recipes) {
                is Loading -> item { CircularLoading() }

                is Success -> {
                    if (recipes().isEmpty())
                        item {
                            EmptyRecipeItem(recipesViewModel)
                        }
                    else
                        items(recipes().size) {
                            RecipeItem(recipe = recipes()[it])
                        }
                }

                is Fail -> {
                    item {
                        RetryItem(recipes.error) {
                            loadMore(recipesViewModel)
                        }
                    }
                }

                else -> {
                    // do nothing
                }
            }
        }

    }
}

@Composable
fun RecipeItem(
    recipe: RecipeView
) {
    Card(
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        ),
        modifier = Modifier.padding(16.dp).fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            AsyncImage(
                model = recipe.image,
                stringResource(R.string.title_recipe),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(15.dp))
            Text(
                text = recipe.name,
                fontSize = 20.sp,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(15.dp))
            Text(recipe.headline)
        }
    }
}

@Composable
fun EmptyRecipeItem(recipesViewModel: RecipesViewModel) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = stringResource(R.string.msg_empty_movies_list))
        Button(
            modifier = Modifier
                .wrapContentSize()
                .padding(start = 20.dp, top = 8.dp, end = 20.dp, bottom = 8.dp),
            onClick = {
                loadMore(recipesViewModel)
            }
        ) {
            Text(text = stringResource(R.string.title_reload), color = Color.White)
        }
    }
}

private fun loadMore(recipesViewModel: RecipesViewModel) {
    recipesViewModel.getRecipes()
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    HellofreshTestTheme {
        RecipeItem(
            RecipeView(
                "1",
                "Pizza",
                "15 calories",
                "3 C",
                "Chicken Ranch Pizza",
                1,
                "",
                "",
                "https://img.hellofresh.com/f_auto,q_auto,w_300/hellofresh_s3/image/533143aaff604d567f8b4571.jpg",
                "",
                "",
                ""
            )
        )
    }
}
