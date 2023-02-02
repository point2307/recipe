package com.recipe.service;

import com.recipe.dto.Board;
import org.springframework.data.domain.Page;

public interface BoardService {
    Page<Board> getBoardList();

    void insertBoard(Board vo);

    void updateBoard(Board vo);

    void deleteBoard(Board vo);

    Board getBoardById(Board vo);
}
