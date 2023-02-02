package com.recipe.persistence;

import org.springframework.data.repository.CrudRepository;

import com.recipe.dto.Buy;

public interface BuyRepo extends CrudRepository<Buy, Long> {

}
