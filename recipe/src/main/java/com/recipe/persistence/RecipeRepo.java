package com.recipe.persistence;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

import com.recipe.dto.Recipe;

public interface RecipeRepo extends CrudRepository<Recipe, Long>, QuerydslPredicateExecutor {


    Recipe findByRecipeTitle(String recipeTitle);

}
