package com.recipe.controller;

import com.recipe.dto.Cart;
import com.recipe.dto.Member;
import com.recipe.security.SecurityUser;
import com.recipe.service.AdminService;
import com.recipe.service.CartService;
import com.recipe.service.MemberService;
import com.recipe.service.RecipeService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Controller
public class CommonController {

    @Autowired
    private MemberService memberService;
    @Autowired
    private RecipeService recipeService;
    @Autowired
    private CartService cartService;
    @Autowired
    private AdminService adminService;

    @RequestMapping({"/mainPage", "/"})
    public String mainPage(Model model, @AuthenticationPrincipal SecurityUser user){
        if(user != null){
            model.addAttribute("recipe", recipeService.mainPageRecipe(user.getMember()));
        }

        model.addAttribute("banner1", adminService.recipeBanner());
        model.addAttribute("banner2", adminService.fundingBanner());
        model.addAttribute("banner3", adminService.eventBanner());
        model.addAttribute("column", adminService.columnList());
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

        return "/mainPage";

    }

    @GetMapping("/realAlert")
    public SseEmitter realTimealert(@AuthenticationPrincipal SecurityUser user){
        SseEmitter emitter = new SseEmitter();
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        if(user!=null) {
            Member member = memberService.getMember(user.getMember().getUserId());
            if(member.getAlarm() > 0){
                System.out.println(member.getAlarm());
                executorService.execute(() -> {
                    try{
                        SseEmitter.SseEventBuilder event = SseEmitter.event()
                                .data("새로운 알림이 있습니다.");
                        emitter.send(event);
                        Thread.sleep(1000);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                });
            }
        }

        return emitter;
    }
}
