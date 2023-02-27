package com.recipe.controller;

import com.recipe.dto.*;
import com.recipe.security.SecurityUser;
import com.recipe.service.*;
import com.recipe.util.File;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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
    @Autowired
    private BuyService buyService;
    @GetMapping("/myPage/updateMemberForm")
    public String updateMemberForm(@AuthenticationPrincipal SecurityUser prin, Model model){

        model.addAttribute("member", prin.getMember()) ;
        return "/myPage/updateMem";
    }
    @PostMapping("/myPage/updateMember")
    @Transactional
    public String updateMember(Member vo,String nopic, String row_pass, MultipartFile pic, Model model) throws IllegalStateException, IOException {
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
        model.addAttribute("member", memberService.getMember(vo.getUserId()));
        return "redirect:/myPage/mymain";
    }
    @GetMapping("/member/deleteSelf")
    public String deleteSelf(@AuthenticationPrincipal SecurityUser user){
        if(user !=null){
            memberService.deleteMem(user.getMember());

            // 시큐리티 인증정보 없애기
            SecurityContextHolder.getContext().setAuthentication(null);
        }

        return "redirect:/";
    }

    @GetMapping("/myPage/mymain")
    public String myMainPage(){
        return "/myPage/mymain";
    }

    @PostMapping("/addCart")
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
                cart = cartService.checkMemberKit(user.getMember(), item);
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
            buyService.insertDetail(buyDetail);
            model.addAttribute("detailList", buyDetailList);
            model.addAttribute("member", user.getMember());
            model.addAttribute("total", item.getMealkit().getPrice()*buyDetail.getQuantity());
            return "/myPage/getBuy";
        }
    }

    @PostMapping("/myPage/goBuy")
    public String goBuyDe(@RequestParam(name = "cart",required = false) List<Long> list,
                          @AuthenticationPrincipal SecurityUser user, int carttotal,
                          Model model){
        System.out.println(list);
        List<BuyDetail> details = new ArrayList<>();
        if(user == null) {
            return "/system/login";
        }else{
            for (Long cartId : list) {
                Cart cart = cartService.getCartById(cartId);
                BuyDetail buy = new BuyDetail();
                buy.setFundingkit(cart.getFundingKit());
                buy.setQuantity(cart.getQuantity());
                buyService.insertDetail(buy);
                details.add(buy);

            }
            model.addAttribute("member", user.getMember());
            model.addAttribute("total", carttotal);
            model.addAttribute("detailList", details);
            return "/myPage/getBuy";
        }
    }

    @PostMapping("/myPage/changeCart")
    @ResponseBody
    public int changeCartQuan(Cart vo){
        System.out.println(vo);
        cartService.changeCartQuan(vo);
        return 0;
    }

    @PostMapping("/myPage/deleteCart")
    @ResponseBody
    public int deleteCart(Cart vo){
        System.out.println(vo);
        cartService.deleteCart(vo);
        return 0;
    }

    @PostMapping("/myPage/insertBuy")
    public String insertBuy(Buy buy, @AuthenticationPrincipal SecurityUser user,Model model, String payment,
                            @RequestParam(name = "details", required = false)List<Long> details){
        List<BuyDetail> buyDetailList = new ArrayList<>();
        for(Long id : details){
            BuyDetail detail = buyService.getDetail(id);
            buyDetailList.add(detail);
        }
        buy.setBuyer(user.getMember());
        buy.setBuyDetails(buyDetailList);
        buyService.insertBuy(buy);
        model.addAttribute("buy", buy);
        if(payment.equals("account")) {
            return "/myPage/paymentInfo";
        } else{
            return "/myPage/apiPayment";
        }
    }

    @PostMapping("/myPage/completeBuy")
    public String paymentSuccess(Long buyId, String userId,
                                 @RequestParam(name = "details", required = false)List<Long> details){
        Buy buy = buyService.getBuy(buyId);
        buy.setProcessing("결제 완료");
        buyService.insertBuy(buy);
        for(Long id : details){
            buyService.paymentDetails(id);
        }
        cartService.deleteCartByMember(memberService.getMember(userId));
        return "redirect:/myPage/buyList?userId="+userId;
    }

    @GetMapping("/myPage/categoryMater")
    @ResponseBody
    public List<Material> materials(Long data){
        System.out.println(data);
        System.out.println(myPageService.listMater(data));
        return myPageService.listMater(data);
    }

    @GetMapping("/myPage/insertMyMater")
    public String insertMyMater(Model model, @AuthenticationPrincipal SecurityUser user){
        model.addAttribute("list", myPageService.mymaterList(user.getMember()));
        return "/myPage/insertMyMater";
    }
    @GetMapping("/myPage/insertMater")
    @ResponseBody
    public int insertMater(String data, @AuthenticationPrincipal SecurityUser user){
        if(user==null){
            return 0;
        } else {
            Member member = user.getMember();
            myPageService.insertMater(member, data);
            return 1;
        }

    }
    @GetMapping("/myPage/deleteMater")
    @ResponseBody
    public int deleteMater(Long Id){
        myPageService.deleteMyMater(Id);
        return 1;
    }

    @GetMapping("/myPage/checkAlarm")
    @ResponseBody
    public int checkAlarm(@AuthenticationPrincipal SecurityUser user){
        Member mem = user.getMember();
        mem.setAlarm(0);
        memberService.register(mem);
        return 0;
    }

}
