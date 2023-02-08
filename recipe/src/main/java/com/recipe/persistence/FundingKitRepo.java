package com.recipe.persistence;

import com.recipe.dto.FundingKit;
import org.springframework.data.repository.CrudRepository;

public interface FundingKitRepo extends CrudRepository<FundingKit, Long> {

}
