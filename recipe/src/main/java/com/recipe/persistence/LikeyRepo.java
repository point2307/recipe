package com.recipe.persistence;

import com.recipe.dto.Likey;
import com.recipe.dto.Member;
import com.recipe.dto.Recipe;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LikeyRepo extends CrudRepository<Likey, Long>, QuerydslPredicateExecutor {

    Likey findByMemberAndRecipe(Member member, Recipe recipe);

    List<Likey> findAllByMember(Member member);
}
