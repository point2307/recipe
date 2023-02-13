package com.recipe.service;

import com.recipe.dto.Cart;
import com.recipe.dto.FundingKit;
import com.recipe.dto.Member;

import java.util.List;

public interface CartService {

    List<Cart> getCartList(Member member);

    Cart checkMemberKit(Member member, FundingKit mealkit);

    void changeCartQuan(Cart vo);

    void deleteCart(Cart vo);

    Cart getCartById(Long cartId);

    void deleteCartByMember(Member member);
}
