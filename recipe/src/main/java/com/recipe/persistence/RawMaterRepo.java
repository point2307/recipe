package com.recipe.persistence;

import com.recipe.dto.RawMater;
import org.springframework.data.repository.CrudRepository;

public interface RawMaterRepo extends CrudRepository<RawMater, Long> {

}
