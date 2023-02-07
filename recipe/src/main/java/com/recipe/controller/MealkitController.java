package com.recipe.controller;

import com.recipe.dto.Mealkit;
import com.recipe.service.MealkitServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MealkitController {
    @Autowired
    private MealkitServiceImpl mealkitService;

    @GetMapping("/admin/insertKit")
    public String insertKitForm(){
        return "/admin/insertKit";
    }

    @PostMapping("/admin/insertKit")
    public String insertKit(Mealkit vo){
        mealkitService.insertKit(vo);
        return "redirect:/common/getKit" + vo.getKitId();
    }
    @GetMapping("/common/getKit")
    public String getKit(Mealkit vo, Model model){

        model.addAttribute("mealkit",mealkitService.getKit(vo));
        return "/common/getKit";
    }

}
