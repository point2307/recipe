package com.recipe.service;

import com.recipe.dto.Cart;
import com.recipe.dto.Funding;
import com.recipe.dto.FundingKit;
import com.recipe.dto.Mealkit;
import com.recipe.persistence.CartRepo;
import com.recipe.persistence.FundingKitRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MyPageServiceImpl implements MyPageService {

    @Autowired
    private FundingKitRepo fundingKitRepo;
    @Autowired
    private CartRepo cartRepo;

    @Override
    public FundingKit findFundingkit(Funding funding, Mealkit mealkit) {

        return fundingKitRepo.findByFundingAndMealkit(funding, mealkit);
    }

    @Override
    public void saveCart(Cart vo) {
        cartRepo.save(vo);
    }
}
