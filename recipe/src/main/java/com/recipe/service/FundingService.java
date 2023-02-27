package com.recipe.service;

import com.recipe.dto.Funding;
import com.recipe.dto.Mealkit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FundingService {

    Page<Funding> getFundingList(Pageable pageable);

    void insertFunding(Funding vo, List<Mealkit> list);

    void deleteFunding(Funding vo);

    void updateFunding(Funding vo);

    Funding getFundingById(Funding vo);
}
