package com.recipe.persistence;

import com.recipe.dto.Funding;
import com.recipe.dto.FundingKit;
import com.recipe.dto.Mealkit;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

public interface FundingKitRepo extends CrudRepository<FundingKit, Long>, QuerydslPredicateExecutor {

    FundingKit findByFundingAndMealkit(Funding funding, Mealkit mealkit);
}
