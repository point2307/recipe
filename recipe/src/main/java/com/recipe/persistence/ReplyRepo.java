package com.recipe.persistence;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

import com.recipe.dto.Reply;

public interface ReplyRepo extends CrudRepository<Reply, Long>, QuerydslPredicateExecutor {

}
