package com.recipe.service;

import com.recipe.dto.Cart;
import com.recipe.dto.Funding;
import com.recipe.dto.FundingKit;
import com.recipe.dto.Mealkit;

public interface MyPageService {

    FundingKit findFundingkit(Funding funding, Mealkit mealkit);

    void saveCart(Cart vo);
}
