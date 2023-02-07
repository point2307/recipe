package com.recipe.controller;

import com.recipe.dto.Funding;
import com.recipe.service.FundingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FundingController {

    @Autowired
    private FundingServiceImpl fundingService;

    @RequestMapping("/common/fundingList")
    public String fundingList(@PageableDefault(size = 6, direction = Sort.Direction.DESC, sort = "funding_id") Pageable pageable, Model model){
        model.addAttribute("fundingList", fundingService.getFundingList(pageable));
        return "/common/fundingList";
    }

    @GetMapping("/admin/insertFunding")
    public String insertFundingForm(){

        return "/admin/insertFunding";
    }

    @PostMapping("/admin/insertFunding")
    public String insertFunding(Funding vo){

        return "redirect:/common/getFunding?funding_id="+vo.getFunding_id();
    }
    @GetMapping("/common/getFunding")
    public String getFunding(Funding vo, Model model){
        model.addAttribute("funding", fundingService.getFundingById(vo));
        return "/common/getFunding";
    }
}
