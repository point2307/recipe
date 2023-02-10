package com.recipe.persistence;

import com.recipe.dto.Cart;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

public interface CartRepo extends CrudRepository<Cart, Long>, QuerydslPredicateExecutor<Cart> {

}
