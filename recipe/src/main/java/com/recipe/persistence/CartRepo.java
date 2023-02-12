package com.recipe.persistence;

import com.recipe.dto.Cart;
import com.recipe.dto.FundingKit;
import com.recipe.dto.Member;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CartRepo extends CrudRepository<Cart, Long>, QuerydslPredicateExecutor<Cart> {

    List<Cart> findAllByMember(Member member);

    Cart findByMemberAndFundingKit(Member member, FundingKit fundingKit);
}
