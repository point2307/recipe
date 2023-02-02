package com.recipe.persistence;

import com.recipe.dto.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

public interface BoardRepo extends CrudRepository<Board, Long>, QuerydslPredicateExecutor<Board> {

    @Query("select b from Board b")
    Page<Board> getBoardList(Pageable paging);
}
