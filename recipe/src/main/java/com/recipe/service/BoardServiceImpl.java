package com.recipe.service;

import com.recipe.dto.Board;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.recipe.persistence.BoardRepo;


@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardRepo bRepo;
	@Override
	public Page<Board> getBoardList(Pageable paging){
		return bRepo.getBoardList(paging);
	}

	@Override
	public void insertBoard(Board vo) {
		bRepo.save(vo);
	}

	@Override
	public void updateBoard(Board vo) {
		Board update = bRepo.findById(vo.getBoardId()).get();
		update.setBoardTitle(vo.getBoardTitle());
		update.setBoardContent(vo.getBoardContent());

		bRepo.save(update);
	}

	@Override
	public void deleteBoard(Board vo) {
		bRepo.deleteById(vo.getBoardId());
	}

	@Override
	public Board getBoardById(Board vo) {
		return bRepo.findById(vo.getBoardId()).get();
	}
}
