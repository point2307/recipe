package com.recipe.persistence;

import org.springframework.data.repository.CrudRepository;

import com.recipe.dto.Contract;

public interface ContractRepo extends CrudRepository<Contract, Long> {

}
