package com.recipe.persistence;

import com.recipe.dto.Material;
import com.recipe.dto.RawMater;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RawMaterRepo extends CrudRepository<RawMater, Long> {

    List<RawMater> findByMater(Material mater);
}
