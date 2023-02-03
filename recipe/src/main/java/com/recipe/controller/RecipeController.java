package com.recipe.controller;

import com.recipe.service.RecipeService;
import com.recipe.service.RecipeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RecipeController {

    @Autowired
    private RecipeServiceImpl recipeService;

    @GetMapping("/common/recipeMain")
    public String getRecipeList(){
        return "/common/recipeMain";
    }

    @GetMapping("/recipe/makeRecipeF")
    public String makeRecipeF(){
        return "/recipe/makeRecipe";
    }
}
