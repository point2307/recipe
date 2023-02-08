package com.recipe.persistence;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

import com.recipe.dto.Recipe;

import java.util.List;

public interface RecipeRepo extends CrudRepository<Recipe, Long>, QuerydslPredicateExecutor {

    @Query("select r from Recipe r")
    Page<Recipe> getAllRecipe(Pageable pageable);

    @Query("select r from Recipe r")
    List<Recipe> findAllBy();

    Recipe findByRecipeTitle(String recipeTitle);

}
