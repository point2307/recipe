package com.recipe.service;

import com.recipe.dto.Board;
import com.recipe.util.Search;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BoardService {
    Page<Board> getBoardList(Pageable paging, Search search);

    void insertBoard(Board vo);

    void updateBoard(Board vo);

    void deleteBoard(Board vo);

    Board getBoardById(Board vo);
}
