package com.recipe.service;

import com.recipe.dto.*;
import com.recipe.persistence.BuyDetailRepo;
import com.recipe.persistence.BuyRepo;
import com.recipe.persistence.FundingKitRepo;
import com.recipe.persistence.FundingRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BuyServiceImpl implements BuyService{

    @Autowired
    private BuyRepo buyRepo;
    @Autowired
    private BuyDetailRepo buyDetailRepo;
    @Autowired
    private FundingRepo fundingRepo;
    @Autowired
    private FundingKitRepo fundingKitRepo;



    @Override
    public void insertDetail(BuyDetail detail) {
        buyDetailRepo.save(detail);
    }

    @Override
    public BuyDetail getDetail(Long id) {
        return buyDetailRepo.findById(id).get();
    }

    @Override
    @Transactional
    public void insertBuy(Buy buy) {
        buyRepo.save(buy);
        for(BuyDetail detail : buy.getBuyDetails()){
            detail.setBuy(buy);
            buyDetailRepo.save(detail);
        }
    }

    @Override
    public Buy getBuy(Long id) {
        return buyRepo.findById(id).get();
    }

    @Override
    @Transactional
    public void paymentDetails(Long id) {
        BuyDetail detail = buyDetailRepo.findById(id).get();
        FundingKit fundingKit = detail.getFundingkit();
        Funding funding = fundingKit.getFunding();
        int total = detail.getQuantity() * fundingKit.getMealkit().getPrice();
        fundingKit.setSelling(fundingKit.getSelling() + detail.getQuantity());
        funding.setFund(funding.getFund() + total);
        fundingRepo.save(funding);
        fundingKitRepo.save(fundingKit);
        detail.setProcess("결제 완료");
        buyDetailRepo.save(detail);
    }
}
