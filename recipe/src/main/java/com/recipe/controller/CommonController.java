package com.recipe.controller;

import com.recipe.dto.Cart;
import com.recipe.dto.Member;
import com.recipe.security.SecurityUser;
import com.recipe.service.CartService;
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

    @RequestMapping("/mainPage")
    public String mainPage(){

        return "/mainPage";
    }
    @RequestMapping("/")
    public String mainPage2(){

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
        System.out.println(cartList);
        model.addAttribute("cartList", cartList);
        model.addAttribute("total", total);
        return "/common/cartList";
    }

    @RequestMapping("/admin/adminMain")
    public String adminPage(){
        return "/admin/adminMain";
    }
}
