package com.recipe.repo;

import org.springframework.data.repository.CrudRepository;

import com.recipe.dto.Board;

public interface BoardRepo extends CrudRepository<Board, Long> {

}
