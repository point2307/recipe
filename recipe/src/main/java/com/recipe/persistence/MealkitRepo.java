package com.recipe.persistence;

import org.springframework.data.repository.CrudRepository;

import com.recipe.dto.Mealkit;

public interface MealkitRepo extends CrudRepository<Mealkit, Long> {

}
