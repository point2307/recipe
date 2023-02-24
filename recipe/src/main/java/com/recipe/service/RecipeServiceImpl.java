package com.recipe.service;

import com.querydsl.core.BooleanBuilder;
import com.recipe.dto.*;
import com.recipe.persistence.*;
import com.recipe.util.Search;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    @Autowired
    private ReplyRepo replyRepo;
    @Autowired
    private MyMaterialRepo myMaterialRepo;
    @Autowired
    private RawMaterRepo rawMaterRepo;
    @Autowired
    private MemberRepo memberRepo;

    @Override
    public void makeRecipe(Recipe vo) {
        recipeRepo.save(vo);


    }

    @Override
    @Transactional
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
    public Page<Recipe> getRecipeList(Pageable pageable, Search search) {
        BooleanBuilder builder = new BooleanBuilder();
        QRecipe qrecipe = QRecipe.recipe;
        if(search.getSearchCondition().equals("Title")){
            builder.and(qrecipe.recipeTitle.like("%" + search.getSearchKeyword()+ "%"));
        } else if(search.getSearchCondition().equals("mater")){
            builder.and(qrecipe.rawMaterList.any().mater.materName.like("%"+search.getSearchKeyword()+ "%"));
        }

        return recipeRepo.findAll(builder, pageable);
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
        Pageable paging = PageRequest.of(0, 25, Sort.Direction.DESC, "likeCount");

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
    @Transactional
    public void likeyRecipe(Member mem, Recipe recipe) {
        Likey likey = new Likey();
        likey.setRecipe(recipe);
        likey.setMember(mem);
        likeyRepo.save(likey);
        recipe.setLikeCount(recipe.getLikeCount()+1);
        recipeRepo.save(recipe);
    }

    @Override
    @Transactional
    public void notlikeRecipe(Member mem, Recipe recipe) {
        Likey likey = likeyRepo.findByMemberAndRecipe(mem, recipe);
        likeyRepo.delete(likey);
        recipe.setLikeCount(recipe.getLikeCount()-1);
        recipeRepo.save(recipe);
    }

    @Override
    public Recipe getRecipe(Long id) {
        return recipeRepo.findById(id).get();
    }

    @Override
    public void saveRecipeReply(Reply reply) {
        replyRepo.save(reply);
        Recipe recipe = reply.getRecipe();
        recipe.setRecipeAlert(recipe.getRecipeAlert()+1);
        recipeRepo.save(recipe);
        Member writer = recipe.getWriter();
        writer.setAlarm(writer.getAlarm()+1);
        memberRepo.save(writer);
    }

    @Override
    public Page<Recipe> likeyRecipe(Member member, Pageable pageable) {
        List<Likey> likeyList = likeyRepo.findAllByMember(member);
        List<Recipe> recipeList = new ArrayList<>();
        for(Likey likey : likeyList){
            if(likey.getRecipe()!=null) {
                Recipe recipe = likey.getRecipe();
                recipe.setCheckLike(1);
                recipeList.add(recipe);
            }
        }

        Page<Recipe> page = new PageImpl<>(recipeList,pageable,recipeList.size());

        return page;
    }

    @Override
    public List<Recipe> mainPageRecipe(Member mem){
        List<MyMaterial> list = myMaterialRepo.findByMember(mem);
        List<Recipe> recipeList = new ArrayList<>();
        if(list.isEmpty()){

        } else {
            Material mater = list.get((int) (Math.random() * list.size())).getMaterial();
            List<RawMater> list1 = rawMaterRepo.findByMater(mater);
            for (RawMater rawMater : list1) {
                recipeList.add(rawMater.getRecipe());
                if (recipeList.size() == 3) {
                    break;
                }
            }
        }

        return recipeList;
    }
    @Override
    public RecipeProc getProc(Long id){
        return recipeProcRepo.findById(id).get();
    }

    @Override
    public RawMater getRaws(Long id){
        Optional<RawMater> optional =  rawMaterRepo.findById(id);
        if(optional.isPresent()){
            return optional.get();
        } else{
            return null;
        }

    }

    public Page<Recipe> myRecipeList(Member member){
        Pageable pageable = PageRequest.of(0, 120, Sort.Direction.DESC, "recipeRegedit");
        return recipeRepo.findByWriter(member, pageable);
    }
}
