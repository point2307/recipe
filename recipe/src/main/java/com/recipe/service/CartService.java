package com.recipe.service;

import com.recipe.dto.Cart;
import com.recipe.dto.FundingKit;
import com.recipe.dto.Member;

import java.util.List;

public interface CartService {

    List<Cart> getCartList(Member member);

    Cart checkMemberKit(Member member, FundingKit mealkit);
}
