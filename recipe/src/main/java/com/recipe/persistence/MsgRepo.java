package com.recipe.persistence;

import org.springframework.data.repository.CrudRepository;

import com.recipe.dto.Message;

public interface MsgRepo extends CrudRepository<Message, String> {

}
