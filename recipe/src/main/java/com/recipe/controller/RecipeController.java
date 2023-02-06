package com.recipe.controller;

import com.recipe.dto.Recipe;
import com.recipe.dto.RecipeProc;
import com.recipe.security.SecurityUser;
import com.recipe.service.RecipeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
public class RecipeController {

    @Autowired
    private RecipeServiceImpl recipeService;

    @GetMapping("/common/recipeMain")
    public String getRecipeList(Pageable pageable, Model model){
        model.addAttribute("recipeList",recipeService.getRecipeList(pageable));
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
    int hour, int minute, int second, int procCount, @AuthenticationPrincipal SecurityUser principal)
    throws Exception {
        System.out.println("전송완료");
        List<RecipeProc> procList = new ArrayList<>();
        System.out.println("어레이작성완료");

        for(String s : procDetail){
            System.out.println(s);
        }

        for(int i = 0; i<procCount; i++){
            RecipeProc proc = new RecipeProc();
            proc.setProcDetail(procDetail.get(i));

            MultipartFile pic = procImg.get(i);
            if(pic.isEmpty()){
                proc.setProcPicName("noPic.jpg");
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
        if(eximage == null){
            vo.setImage("noPic.jpg");
        } else{
            com.recipe.util.File file = new com.recipe.util.File(UUID.randomUUID().toString(), eximage.getOriginalFilename(),
                    eximage.getContentType());
            java.io.File newFileName = new java.io.File(file.getUuid()+"_"+file.getOriginalName());
            eximage.transferTo(newFileName);
            vo.setImage(newFileName.toString());
        }
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

}
