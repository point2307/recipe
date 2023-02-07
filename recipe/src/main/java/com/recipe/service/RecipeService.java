package com.recipe.service;

import com.recipe.dto.Recipe;
import com.recipe.dto.RecipeProc;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RecipeService {
    void makeRecipe(Recipe vo);

    void updateRecipe(Recipe vo);

    void deleteRecipe(Recipe vo);

    Page<Recipe> getRecipeList(Pageable pageable);

    Recipe getRecipeById(Recipe vo);

    List<RecipeProc> processing(Recipe vo);

    Page<Recipe> famousList(int set);
}
