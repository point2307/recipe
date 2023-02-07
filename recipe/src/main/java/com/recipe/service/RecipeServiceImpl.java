package com.recipe.service;

import com.querydsl.core.BooleanBuilder;
import com.recipe.dto.QRecipe;
import com.recipe.dto.Recipe;
import com.recipe.dto.RecipeProc;
import com.recipe.persistence.RecipeProcRepo;
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

    @Autowired
    private RecipeProcRepo recipeProcRepo;

    @Override
    public void makeRecipe(Recipe vo) {
        recipeRepo.save(vo);
        for(RecipeProc proc : vo.getRecipe_process()){
            recipeProcRepo.save(proc);
        }
    }

    @Override
    public void updateRecipe(Recipe vo) {
        if(recipeRepo.findById(vo.getRecipeId()).isPresent()) {
            Recipe update = recipeRepo.findById(vo.getRecipeId()).get();

        update.setRecipeTitle(vo.getRecipeTitle());
        update.setRawMaterList(vo.getRawMaterList());
        update.setRecipe_process(vo.getRecipe_process());

        recipeRepo.save(update);

        }
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

    @Override
    public Recipe getRecipeById(Recipe vo) {
        return recipeRepo.findById(vo.getRecipeId()).get();
    }


    @Override
    public List<RecipeProc> processing(Recipe vo) {
        return recipeProcRepo.findAllByRecipe(vo.getRecipeId());
    }

    @Override
    public Page<Recipe> famousList(int set) {
        BooleanBuilder builder = new BooleanBuilder();

        QRecipe qrecipe = QRecipe.recipe;
        builder.and(qrecipe.likeCount.goe(set));
        Pageable paging = PageRequest.of(0, 12, Sort.Direction.DESC, "likeCount");

        return recipeRepo.findAll(builder, paging);
    }
}
