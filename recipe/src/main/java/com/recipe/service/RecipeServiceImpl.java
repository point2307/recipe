package com.recipe.service;

import com.recipe.dto.Recipe;
import com.recipe.persistence.RecipeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecipeServiceImpl implements RecipeService {
    @Autowired
    private RecipeRepo recipeRepo;


    @Override
    public void makeRecipe(Recipe vo) {
        recipeRepo.save(vo);
    }

    @Override
    public void updateRecipe(Recipe vo) {
        Recipe update = recipeRepo.findById(vo.getRecipeId()).get();

        update.setRecipeTitle(vo.getRecipeTitle());
        update.setRawMaterList(vo.getRawMaterList());
        update.setRecipe_process(vo.getRecipe_process());

        recipeRepo.save(update);
    }

    @Override
    public void deleteRecipe(Recipe vo) {
        recipeRepo.delete(vo);
    }

    @Override
    public Page<Recipe> getRecipeList(Pageable pageable) {
        if(pageable == null){
            pageable = PageRequest.of(0, 10, Sort.Direction.DESC, "board_id");
        }
        return recipeRepo.getAllRecipe(pageable);
    }
}
