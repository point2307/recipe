package com.recipe.service;

import com.recipe.dto.*;
import com.recipe.util.Search;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RecipeService {
    void makeRecipe(Recipe vo);

    void updateRecipe(Recipe vo);

    void deleteRecipe(Recipe vo);

    Page<Recipe> getRecipeList(Pageable pageable, Search search);

    Recipe getRecipeById(Recipe vo);

    List<RecipeProc> processing(Recipe vo);

    Page<Recipe> famousList(int set);

    Recipe getRecipeByTitle(String recipeTitle);

    List<Material> searchMater(String value);

    Material searchMaterForRaw(String str);

    int likedCheck(Member member, Recipe recipe);

    void likeyRecipe(Member mem, Recipe recipe);

    void notlikeRecipe(Member mem, Recipe recipe);

    Recipe getRecipe(Long id);

    void saveRecipeReply(Reply reply);

    Page<Recipe> likeyRecipe(Member member, Pageable pageable);

    List<Recipe> mainPageRecipe(Member member);

    RecipeProc getProc(Long id);

    RawMater getRaws(Long id);
    Page<Recipe> myRecipeList(Member member);
}
