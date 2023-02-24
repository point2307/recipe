package com.recipe.persistence;

import com.recipe.dto.Notice;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface NoticeRepo extends CrudRepository<Notice, Long>, QuerydslPredicateExecutor {

    Notice findFirstByKindOrderByRegDate(int kind);

    List<Notice> findTop3ByKindOrderByRegDate(int kind);
}
