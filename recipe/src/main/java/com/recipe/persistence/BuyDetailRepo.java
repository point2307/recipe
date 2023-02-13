package com.recipe.persistence;

import com.recipe.dto.BuyDetail;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

public interface BuyDetailRepo extends CrudRepository<BuyDetail, Long>, QuerydslPredicateExecutor {


}
