package com.recipe.persistence;

import com.recipe.dto.Recipe;
import com.recipe.dto.RecipeProc;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RecipeProcRepo extends CrudRepository<RecipeProc, Long>, QuerydslPredicateExecutor {

    public List<RecipeProc> findAllByRecipe(Long id);

}
