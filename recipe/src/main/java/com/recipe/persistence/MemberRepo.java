package com.recipe.persistence;

import org.springframework.data.repository.CrudRepository;

import com.recipe.dto.Member;

import java.util.Optional;

public interface MemberRepo extends CrudRepository<Member, String> {

    Optional findByEmail(String email);

}
