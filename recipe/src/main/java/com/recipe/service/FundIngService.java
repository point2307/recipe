package com.recipe.service;

import com.recipe.dto.Funding;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FundIngService {

    Page<Funding> getFundingList(Pageable pageable);

    void insertFunding(Funding vo);

    void deleteFunding(Funding vo);

    void updateFunding(Funding vo);

    Funding getFundingById(Funding vo);
}
