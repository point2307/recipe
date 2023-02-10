package com.recipe.controller;

import com.recipe.dto.Cart;
import com.recipe.dto.Funding;
import com.recipe.dto.Mealkit;
import com.recipe.dto.Member;
import com.recipe.security.SecurityUser;
import com.recipe.service.*;
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
public class MyPageController {

    @Autowired
    private MemberServiceImpl memberService;
    @Autowired
    private MyPageService myPageService;

    @Autowired
    private PasswordEncoder encoder;
    @GetMapping("/myPage/updateMemberForm")
    public String updateMemberForm(@AuthenticationPrincipal SecurityUser prin, Model model){
        model.addAttribute("member", prin.getMember()) ;
        return "/mypage/updateMem";
    }
    @PostMapping("/myPage/updateMember")
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
        return "redirect:/myPage/mymain";
    }
    @GetMapping("/myPage/mymain")
    public String myMainPage(){
        return "/myPage/mymain";
    }

    @PostMapping("addCart")
    public String addCart(@RequestParam(name = "fundname")Long fund, Cart cart,
                          @AuthenticationPrincipal SecurityUser user, Long selectKit ){
        Funding funding = new Funding();
        funding.setFundId(fund);
        Mealkit mealkit = new Mealkit();
        mealkit.setKitId(selectKit);
        cart.setFundingKit(myPageService.findFundingkit(funding, mealkit));
        if(user != null){
            cart.setMember(user.getMember());
        }
        myPageService.saveCart(cart);

        return "redirect:/common/getCart?cartId="+cart.getCartId();
    }
    @PostMapping("/myPage/addBuy")
    public String addBuyDe(@RequestParam(name = "fundname")Long fund, Cart cart,
                           @AuthenticationPrincipal SecurityUser user, Long selectKit){

        if(user == null){
            return "redirect:system/login";
        } else {
            return "/mypage/getBuy";
        }
    }
}
