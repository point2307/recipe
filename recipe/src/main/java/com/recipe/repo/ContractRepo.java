package com.recipe.repo;

import org.springframework.data.repository.CrudRepository;

import com.recipe.dto.Contract;

public interface ContractRepo extends CrudRepository<Contract, Long> {

}
