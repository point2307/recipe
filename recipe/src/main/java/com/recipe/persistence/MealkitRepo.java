package com.recipe.persistence;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

import com.recipe.dto.Mealkit;

import java.util.List;

public interface MealkitRepo extends CrudRepository<Mealkit, Long>, QuerydslPredicateExecutor<Mealkit> {
    @Query("select m from Mealkit m")
    Page<Mealkit> getMealkitList(Pageable pageable);

    Mealkit findBykitName(String str);

}
