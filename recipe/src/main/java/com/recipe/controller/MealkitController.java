package com.recipe.controller;

import com.recipe.dto.Mealkit;
import com.recipe.dto.Recipe;
import com.recipe.service.MealkitServiceImpl;
import com.recipe.service.RecipeServiceImpl;
import com.recipe.util.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Controller
public class MealkitController {
    @Autowired
    private MealkitServiceImpl mealkitService;

    @Autowired
    private RecipeServiceImpl recipeService;

    @GetMapping("/admin/insertKit")
    public String insertKitForm(){
        return "/admin/insertKit";
    }

    @PostMapping("/admin/insertKit")
    public String insertKit(Mealkit vo, String recipeTitle, MultipartFile pic)  throws IllegalStateException, IOException {
        if(pic.isEmpty()){
            vo.setExImg("noPic.jpg");
        } else{
            File file = new File(UUID.randomUUID().toString(), pic.getOriginalFilename(),
                    pic.getContentType());
            java.io.File newFileName = new java.io.File(file.getUuid()+"_"+file.getOriginalName());
            pic.transferTo(newFileName);
            vo.setExImg(newFileName.toString());
        }

        vo.setRecipe(recipeService.getRecipeByTitle(recipeTitle));
        mealkitService.insertKit(vo);
        return "redirect:/common/getKit?kitId=" + vo.getKitId();
    }
    @GetMapping("/common/getKit")
    public String getKit(Mealkit vo, Model model){

        model.addAttribute("mealkit",mealkitService.getKit(vo));
        return "/common/getKit";
    }

    @PostMapping("/admin/famousRecipe")
    @ResponseBody
    public List<Recipe> famousRecipe(){
        List<Recipe> recipeList = recipeService.famousList(50).getContent();
        System.out.println(recipeList);
        return recipeList;
    }

}
