package com.recipe.controller;

import com.recipe.dto.Member;
import com.recipe.security.SecurityUser;
import com.recipe.service.MemberServiceImpl;
import com.recipe.util.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.UUID;
@SessionAttributes("member")
@Controller
@RequestMapping("/mypage/")
public class MyPageController {

    @Autowired
    private MemberServiceImpl memberService;
    @Autowired
    private PasswordEncoder encoder;
    @GetMapping("updateMemberForm")
    public String updateMemberForm(@AuthenticationPrincipal SecurityUser prin, Model model){
        model.addAttribute("member", prin.getMember()) ;
        return "/mypage/updateMem";
    }
    @PostMapping("updateMember")
    public String updateMember(Member vo,String nopic, String row_pass, MultipartFile pic) throws IllegalStateException, IOException {
        if(pic.isEmpty()){
            vo.setProImg(nopic);
        } else {
            File file = new File(UUID.randomUUID().toString(), pic.getOriginalFilename(),
                    pic.getContentType());
            java.io.File newFileName = new java.io.File(file.getUuid()+"_"+file.getOriginalName());
            pic.transferTo(newFileName);
            vo.setProImg(newFileName.toString());
        }
        vo.setPassword(encoder.encode(row_pass));
        memberService.updateMem(vo);
        return "redirect:/mypage/mymain";
    }
    @GetMapping("mymain")
    public String myMainPage(){
        return "/mypage/mymain";
    }
}
