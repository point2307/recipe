package com.recipe.persistence;

import com.recipe.dto.Material;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MaterialRepo extends CrudRepository<Material, Long>, QuerydslPredicateExecutor<Material> {


    List<Material> findByMaterNameContaining(String value);

    Material findByMaterName(String str);
}
