package com.recipe.service;

import com.recipe.dto.Funding;
import com.recipe.persistence.FundingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Service;

@Service
public class FundingServiceImpl implements FundIngService{

    @Autowired
    private FundingRepo fundingRepo;


    @Override
    public Page<Funding> getFundingList(@PageableDefault(sort = "funding_id" ,direction = Sort.Direction.DESC) Pageable pageable) {
        return fundingRepo.getFundingList(pageable);
    }

    @Override
    public void insertFunding(Funding vo) {

    }

    @Override
    public void deleteFunding(Funding vo) {

    }


    @Override
    public void updateFunding(Funding vo) {

    }

    @Override
    public Funding getFundingById(Funding vo) {
        return fundingRepo.findById(vo.getFunding_id()).get();
    }
}
