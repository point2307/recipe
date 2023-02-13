package com.recipe.service;

import com.recipe.dto.Funding;
import com.recipe.dto.FundingKit;
import com.recipe.dto.Mealkit;
import com.recipe.persistence.FundingKitRepo;
import com.recipe.persistence.FundingRepo;
import com.recipe.persistence.MealkitRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FundingServiceImpl implements FundIngService{

    @Autowired
    private FundingRepo fundingRepo;
    @Autowired
    private FundingKitRepo fundingKitRepo;

    @Override
    public Page<Funding> getFundingList(Pageable pageable) {
        return fundingRepo.getFundingList(pageable);
    }

    @Override
    @Transactional
    public void insertFunding(Funding vo, List<Mealkit> list) {
        fundingRepo.save(vo);
        for(Mealkit kit : list){
            FundingKit fk = new FundingKit();
            fk.setFunding(vo);
            fk.setMealkit(kit);
            fundingKitRepo.save(fk);
        }

    }

    @Override
    public void deleteFunding(Funding vo) {
        fundingRepo.delete(vo);
    }


    @Override
    public void updateFunding(Funding vo) {

    }

    @Override
    public Funding getFundingById(Funding vo) {
        return fundingRepo.findById(vo.getFundId()).get();
    }
}
