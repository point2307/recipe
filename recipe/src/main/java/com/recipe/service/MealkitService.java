package com.recipe.service;

import com.recipe.dto.Funding;
import com.recipe.dto.Mealkit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MealkitService {

    void insertKit(Mealkit vo);

    void updateKit(Mealkit vo);

    void deleteKit(Mealkit vo);

    Mealkit getKit(Mealkit vo);

    Page<Mealkit> getKitList(Pageable pageable);

    Page<Mealkit> getKitByFunding(Pageable pageable, Funding vo);
    Mealkit getKitforFunding(String str);
}
