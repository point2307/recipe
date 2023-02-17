package com.recipe.controller;

import com.recipe.dto.*;
import com.recipe.security.SecurityUser;
import com.recipe.service.RecipeServiceImpl;
import com.recipe.service.ReplyService;
import com.recipe.util.Search;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
public class RecipeController {

    @Autowired
    private RecipeServiceImpl recipeService;
    @Autowired
    private ReplyService replyService;
    // 좋아요 체크 메소드

    public void checklikeRecipe(Member member, Recipe recipe){
        if(recipeService.likedCheck(member,recipe) == 1){
            recipe.setCheckLike(1);
        }
    }

    @GetMapping("/common/recipeList")
    public String getRecipeList(@PageableDefault(size=6,sort = "recipeId", direction = Sort.Direction.DESC) Pageable pageable, Model model,
                                @AuthenticationPrincipal SecurityUser user, Search search){
        model.addAttribute("search", search);
        if(search.getSearchKeyword() == null){
            search.setSearchCondition("Title");
            search.setSearchKeyword("");
        }

        Page<Recipe> recipePage = recipeService.getRecipeList(pageable, search);
        if(user != null) {
            Member mem = user.getMember();
            for (Recipe recipe : recipePage.getContent()) {
                    checklikeRecipe(mem, recipe);
            }
        }
        model.addAttribute("recipeList",recipePage);
        return "/common/recipeMain";
    }

    @GetMapping("/recipe/makeRecipe")
    public String makeRecipeF(){
        return "/recipe/makeRecipe";
    }
    @PostMapping("/recipe/makeRecipe")
    public String makeRecipe(Recipe vo, MultipartFile eximage,
                             @RequestParam(value = "procDetail" , required = false) List<String> procDetail,
                             @RequestParam(value = "procImg", required = false) List<MultipartFile> procImg,
                             @RequestParam(value = "rawsize", required = false) List<String> size,
                             @RequestParam(value = "mater", required = false) List<String> mater,
    int hour, int minute, @AuthenticationPrincipal SecurityUser principal)
    throws Exception {
        List<RawMater> rawMaters = new ArrayList<>();
        List<RecipeProc> procList = new ArrayList<>();
        if(procDetail != null){
            for(int i = 0; i< procDetail.size(); i++){
                RecipeProc proc = new RecipeProc();
                proc.setProcDetail(procDetail.get(i));

                MultipartFile pic = procImg.get(i);
                if(pic.isEmpty()){
                    proc.setProcPicName("noProcImg.jpg");
                } else{
                    com.recipe.util.File file = new com.recipe.util.File(UUID.randomUUID().toString(), pic.getOriginalFilename(),
                            pic.getContentType());
                    java.io.File newFileName = new java.io.File(file.getUuid()+"_"+file.getOriginalName());
                    pic.transferTo(newFileName);
                    proc.setProcPicName(newFileName.toString());
                }

                proc.setRecipe(vo);
                procList.add(proc);
            }
        }
        if(eximage.isEmpty()){
            vo.setImage("noRecipeImg.jpg");
        } else{
            com.recipe.util.File file = new com.recipe.util.File(UUID.randomUUID().toString(), eximage.getOriginalFilename(),
                    eximage.getContentType());
            java.io.File newFileName = new java.io.File(file.getUuid()+"_"+file.getOriginalName());
            eximage.transferTo(newFileName);
            vo.setImage(newFileName.toString());
        }
        if(mater!=null ) {
            for (int i = 0; i < mater.size(); i++) {
                RawMater raws = new RawMater();
                raws.setMater(recipeService.searchMaterForRaw(mater.get(i)));
                raws.setAmount(size.get(i));
                raws.setRecipe(vo);
                rawMaters.add(raws);
            }
        }
        vo.setRawMaterList(rawMaters);
        vo.setRecipe_process(procList);
        vo.setCookingTime((hour*60) + (minute));
        vo.setWriter(principal.getMember());

        recipeService.makeRecipe(vo);
        return "redirect:/common/getRecipe?recipeId="+vo.getRecipeId();
    }

    @RequestMapping("/common/getRecipe")
    public String getRecipe(Recipe vo, Model model, @AuthenticationPrincipal SecurityUser user){
        Recipe recipe = recipeService.getRecipeById(vo);
        if(user!=null){
            checklikeRecipe(user.getMember(), recipe);
        }

        model.addAttribute("recipe", recipe);
        return "common/getRecipe";
    }

    @GetMapping("/recipe/searchMater")
    @ResponseBody
    public List<Material> showMaterlist(String value){
        return recipeService.searchMater(value);
    }

    @GetMapping("/recipe/likeRecipe")
    @ResponseBody
    public int likeRecipe(Long data, @AuthenticationPrincipal SecurityUser user) {
        Recipe recipe = new Recipe();
        recipe.setRecipeId(data);
        Recipe real = recipeService.getRecipeById(recipe);
        if(user == null){
            return 0;
        } else{
            recipeService.likeyRecipe(user.getMember(), real);
            return 1;
        }
    }

    @GetMapping("/recipe/notlikeRecipe")
    @ResponseBody
    public int notlikeRecipe(Long data, @AuthenticationPrincipal SecurityUser user){
        Recipe recipe = new Recipe();
        recipe.setRecipeId(data);
        Recipe real = recipeService.getRecipeById(recipe);
        recipeService.notlikeRecipe(user.getMember(), real);
        return 1;
    }

    @PostMapping("/recipe/makeReply")
    @ResponseBody
    public int writeRecipeReply(Long recipe, String content,
                                @AuthenticationPrincipal SecurityUser user){
        System.out.println(content);
        if(user==null){
            return 0;
        } else{
            Reply reply = new Reply();
            reply.setRecipe(recipeService.getRecipe(recipe));
            reply.setContent(content);
            reply.setWriter(user.getMember());
            recipeService.saveRecipeReply(reply);
            return 1;
        }
    }

    @PostMapping("/recipe/deleteReply")
    @ResponseBody
    public int deleteRecipeReply(Long id){
        replyService.deleteReply(id);
        return 1;
    }



    @GetMapping("/myPage/likeyRecipeList")
    public String getLikeyRecipeList(@PageableDefault(size=6,sort = "recipeId", direction = Sort.Direction.DESC) Pageable pageable, Model model, @AuthenticationPrincipal SecurityUser user
       ,Search search){
            model.addAttribute("search", search);
            if(search.getSearchKeyword() == null){
                search.setSearchCondition("Title");
                search.setSearchKeyword("");
            }
        Page<Recipe> recipePage = recipeService.likeyRecipe(user.getMember(), pageable);

        model.addAttribute("recipeList",recipePage);
        return "/common/recipeMain";
    }
}
