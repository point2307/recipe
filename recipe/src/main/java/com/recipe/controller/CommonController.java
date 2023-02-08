package com.recipe.controller;

import com.recipe.dto.Member;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CommonController {

    @RequestMapping("/mainPage")
    public String mainPage(){

        return "mainPage";
    }
    @RequestMapping("/")
    public String mainPage2(){

        return "mainPage";
    }
}
