package com.recipe.service;

import com.querydsl.core.BooleanBuilder;
import com.recipe.dto.Board;
import com.recipe.dto.QBoard;
import com.recipe.util.Search;
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
	public Page<Board> getBoardList(Pageable paging, Search search){
		BooleanBuilder builder = new BooleanBuilder();

		QBoard qboard = QBoard.board;
		if(search.getSearchCondition().equals("Title")){
			builder.and(qboard.boardTitle.like("%" + search.getSearchKeyword()+ "%"));
		} else if(search.getSearchCondition().equals("content")){
			builder.and(qboard.boardContent.like("%"+search.getSearchKeyword()+ "%"));
		} else if(search.getSearchCondition().equals("Writer")){
			builder.and(qboard.boardWriter.nickName.like("%"+search.getSearchKeyword()+"%"));
		}
		return bRepo.findAll(builder, paging);
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
