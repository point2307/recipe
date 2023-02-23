package com.recipe.persistence;

import com.recipe.dto.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

import com.recipe.dto.Recipe;

public interface RecipeRepo extends CrudRepository<Recipe, Long>, QuerydslPredicateExecutor {


    Recipe findByRecipeTitle(String recipeTitle);

    Page<Recipe> findByWriter(Member member, Pageable pageable);
}
