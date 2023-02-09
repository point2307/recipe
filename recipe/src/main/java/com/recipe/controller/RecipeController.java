package com.recipe.controller;

import com.recipe.dto.*;
import com.recipe.security.SecurityUser;
import com.recipe.service.RecipeServiceImpl;
import jakarta.servlet.http.HttpSession;
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
    // 좋아요 체크 메소드

    public void checklikeRecipe(Member member, Recipe recipe){
        if(recipeService.likedCheck(member,recipe) == 1){
            recipe.setCheckLike(1);
        }
    }

    @GetMapping("/common/recipeList")
    public String getRecipeList(@PageableDefault(size=6,sort = "recipeId", direction = Sort.Direction.DESC) Pageable pageable, Model model, @AuthenticationPrincipal SecurityUser user)
            throws AbstractMethodError{
        Page<Recipe> recipePage = recipeService.getRecipeList(pageable);
        if(user != null) {
            Member mem = user.getMember();
            if (mem != null) {
                for (Recipe recipe : recipePage.getContent()) {
                    checklikeRecipe(mem, recipe);
                }
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
                             @RequestParam(value = "procDetail" , required = true) List<String> procDetail,
                             @RequestParam(value = "procImg", required = true) List<MultipartFile> procImg,
                             @RequestParam(value = "rawsize", required = true) List<String> size,
                             @RequestParam(value = "mater", required = true) List<String> mater,
    int hour, int minute, int second,
                             @AuthenticationPrincipal SecurityUser principal)
    throws Exception {
        List<RawMater> rawMaters = new ArrayList<>();
        List<RecipeProc> procList = new ArrayList<>();
        System.out.println("어레이작성완료");

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
        if(eximage.isEmpty()){
            vo.setImage("noRecipeImg.jpg");
        } else{
            com.recipe.util.File file = new com.recipe.util.File(UUID.randomUUID().toString(), eximage.getOriginalFilename(),
                    eximage.getContentType());
            java.io.File newFileName = new java.io.File(file.getUuid()+"_"+file.getOriginalName());
            eximage.transferTo(newFileName);
            vo.setImage(newFileName.toString());
        }
        for(int i=0; i< mater.size(); i++){
            RawMater raws = new RawMater();
            raws.setMater(recipeService.searchMaterForRaw(mater.get(i)));
            raws.setAmount(size.get(i));
            raws.setRecipe(vo);
            rawMaters.add(raws);
        }
        System.out.println(procList);
        System.out.println(rawMaters);

        vo.setRawMaterList(rawMaters);
        vo.setRecipe_process(procList);
        vo.setRecipeCookingTime((hour*3600) + (minute*60) + (second));
        vo.setWriter(principal.getMember());

        recipeService.makeRecipe(vo);
        return "redirect:/common/getRecipe?recipeId="+vo.getRecipeId();
    }

    @RequestMapping("/common/getRecipe")
    public String getRecipe(Recipe vo, Model model){
        model.addAttribute("recipe", recipeService.getRecipeById(vo));

        return "common/getRecipe";
    }

    @GetMapping("/recipe/searchMater")
    @ResponseBody
    public List<Material> showMaterlist(String value){
        System.out.println(value);
        System.out.println(recipeService.searchMater(value));
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

}
