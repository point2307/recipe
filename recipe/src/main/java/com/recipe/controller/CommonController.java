package com.recipe.controller;

import com.recipe.dto.Cart;
import com.recipe.dto.Member;
import com.recipe.security.SecurityUser;
import com.recipe.service.AdminService;
import com.recipe.service.CartService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CommonController {

    @Autowired
    private CartService cartService;
    @Autowired
    private AdminService adminService;

    @RequestMapping({"/mainPage", "/"})
    public String mainPage(Model model){
        model.addAttribute("banner1", adminService.recipeBanner());
        model.addAttribute("banner2", adminService.fundingBanner());
        model.addAttribute("banner3", adminService.eventBanner());
        return "/mainPage";
    }

    @GetMapping("/common/adminMain")
    public String adminMain(){
      return "/admin/adminMain";
    }

    @RequestMapping("/common/getCart")
    public String getCart(Model model, @AuthenticationPrincipal SecurityUser user){
        int total = 0;
        List<Cart> cartList = new ArrayList<>();
        if(user != null){
            cartList = cartService.getCartList(user.getMember());
            for(Cart cart : cartList){
                total += cart.getFundingKit().getMealkit().getPrice() * cart.getQuantity();
                cart.setTotal(cart.getFundingKit().getMealkit().getPrice() * cart.getQuantity());
            }
        }
        model.addAttribute("cartList", cartList);
        model.addAttribute("total", total);
        return "/common/cartList";
    }

    @RequestMapping("/admin/adminMain")
    public String adminPage(){
        return "/admin/adminMain";
    }

    @GetMapping("/oauth2Suc")
    public String loginsuccess(Model model, HttpSession session){
        Member member = (Member) session.getAttribute("member");
        SecurityUser user = new SecurityUser(member);

        return "/mainPage";

    }
}
