package com.recipe.persistence;

import com.recipe.dto.Notice;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

public interface NoticeRepo extends CrudRepository<Notice, Long>, QuerydslPredicateExecutor {

}
