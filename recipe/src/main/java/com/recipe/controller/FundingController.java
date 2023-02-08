package com.recipe.controller;

import com.recipe.dto.Funding;
import com.recipe.dto.Mealkit;
import com.recipe.dto.Recipe;
import com.recipe.service.FundingServiceImpl;
import com.recipe.service.MealkitServiceImpl;
import com.recipe.util.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
public class FundingController {

    @Autowired
    private FundingServiceImpl fundingService;
    @Autowired
    private MealkitServiceImpl mealkitService;

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
    public String insertFunding(Funding vo, @RequestParam(value = "kit", required = true) List<String> kit,
                                MultipartFile pic) throws IOException {
        List<Mealkit> list = new ArrayList<>();
        System.out.println(vo);
        System.out.println(kit);
        for(String str : kit){
            Mealkit meal = mealkitService.getKitforFunding(str);
            list.add(meal);
        }
        System.out.println(list);
        if(pic.isEmpty()){
            vo.setFundImage("noFundingPic.jpg");
        } else{
            File file = new File(UUID.randomUUID().toString(), pic.getOriginalFilename(),
                    pic.getContentType());
            java.io.File newFileName = new java.io.File(file.getUuid()+"_"+file.getOriginalName());
            pic.transferTo(newFileName);
            vo.setFundImage(newFileName.toString());
        }

        fundingService.insertFunding(vo, list);

        return "redirect:/common/getFunding?funding_id="+vo.getFundId();
    }
    @GetMapping("/common/getFunding")
    public String getFunding(Funding vo, Model model){
        model.addAttribute("funding", fundingService.getFundingById(vo));
        return "/common/getFunding";
    }

    @PostMapping("/admin/selectKit")
    @ResponseBody
    public List<Mealkit> selectKit(
            @PageableDefault(direction = Sort.Direction.DESC, sort = "kitId") Pageable pageable){
        List<Mealkit> kitList = mealkitService.getKitList(pageable).getContent();
        System.out.println(kitList);
        return kitList;
    }
}
