package com.recipe.repo;

import org.springframework.data.repository.CrudRepository;

import com.recipe.dto.Reply;

public interface ReplyRepo extends CrudRepository<Reply, Long> {

}
