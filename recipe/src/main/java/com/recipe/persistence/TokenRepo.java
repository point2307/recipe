package com.recipe.persistence;

import com.recipe.dto.Token;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TokenRepo extends JpaRepository<Token, String> {

    Optional<Token> findByIdAndExpired(String Id, boolean expired);
}
