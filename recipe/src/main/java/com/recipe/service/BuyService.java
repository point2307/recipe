package com.recipe.service;

import com.recipe.dto.Buy;
import com.recipe.dto.BuyDetail;

public interface BuyService {

    void insertDetail(BuyDetail detail);

    BuyDetail getDetail(Long id);

    void insertBuy(Buy buy);

    Buy getBuy(Long id);

    void paymentDetails(Long id);
}
