package com.recipe.persistence;

import org.springframework.data.repository.CrudRepository;

import com.recipe.dto.Member;

public interface MemberRepo extends CrudRepository<Member, String> {

}
