package com.recipe.controller;

import com.recipe.dto.*;
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
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@SessionAttributes("member")
@Controller
public class MyPageController {

    @Autowired
    private MemberService memberService;
    @Autowired
    private MyPageService myPageService;
    @Autowired
    private CartService cartService;
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
        FundingKit item = myPageService.findFundingkit(funding, mealkit);
        cart.setFundingKit(item);
        if(user != null){
            Cart cart1 = cartService.checkMemberKit(user.getMember(), item);
            if(cart1 != null){
                cart.setCartId(cart1.getCartId());
                cart.setQuantity(cart.getQuantity() + cart1.getQuantity());
            } else {
                cart.setMember(user.getMember());
            }
        }
        myPageService.saveCart(cart);
        return "redirect:/common/getCart";
    }
    @PostMapping("/myPage/addBuy")
    public String addBuyDe(@RequestParam(name = "fundname")Long fund, Model model, BuyDetail buyDetail,
                           @AuthenticationPrincipal SecurityUser user, Long selectKit){
        if(user == null){
            return "system/login";
        } else {
            Funding funding = new Funding();
            funding.setFundId(fund);
            Mealkit mealkit = new Mealkit();
            mealkit.setKitId(selectKit);
            FundingKit item = myPageService.findFundingkit(funding, mealkit);
            buyDetail.setFundingkit(item);
            List<BuyDetail> buyDetailList = new ArrayList<>();
            buyDetailList.add(buyDetail);
            model.addAttribute("DetailList", buyDetailList);
            return "/myPage/getBuy";
        }
    }

    @PostMapping("/myPage/goBuy")
    public String goBuyDe(){

        return "/myPage/getBuy";
    }

}
