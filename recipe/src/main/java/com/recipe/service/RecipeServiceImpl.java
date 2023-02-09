package com.recipe.service;

import com.querydsl.core.BooleanBuilder;
import com.recipe.dto.*;
import com.recipe.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecipeServiceImpl implements RecipeService {
    @Autowired
    private RecipeRepo recipeRepo;

    @Autowired
    private RecipeProcRepo recipeProcRepo;

    @Autowired
    private MaterialRepo materialRepo;

    @Autowired
    private LikeyRepo likeyRepo;

    @Override
    public void makeRecipe(Recipe vo) {
        recipeRepo.save(vo);


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
        Pageable paging = PageRequest.of(0, 6, Sort.Direction.DESC, "likeCount");

        return recipeRepo.findAll(builder, paging);
    }

    @Override
    public Recipe getRecipeByTitle(String recipeTitle) {
        return recipeRepo.findByRecipeTitle(recipeTitle);
    }

    @Override
    public List<Material> searchMater(String value) {
        return materialRepo.findByMaterNameContaining(value);
    }

    @Override
    public Material searchMaterForRaw(String str) {
        return materialRepo.findByMaterName(str);
    }

    @Override
    public int likedCheck(Member member, Recipe recipe) {
        int result = 0;
        Likey liked = likeyRepo.findByMemberAndRecipe(member, recipe);
        if(liked != null){
            result = 1;
        }
        return result;
    }

    @Override
    public void likeyRecipe(Member mem, Recipe recipe) {
        Likey likey = new Likey();
        likey.setRecipe(recipe);
        likey.setMember(mem);
        likeyRepo.save(likey);
        recipe.setLikeCount(recipe.getLikeCount()+1);
        recipeRepo.save(recipe);
    }

    @Override
    public void notlikeRecipe(Member mem, Recipe recipe) {
        Likey likey = likeyRepo.findByMemberAndRecipe(mem, recipe);
        likeyRepo.delete(likey);
        recipe.setLikeCount(recipe.getLikeCount()-1);
        recipeRepo.save(recipe);
    }

}
