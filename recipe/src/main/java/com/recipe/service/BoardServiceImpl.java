package com.recipe.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.recipe.repo.BoardRepo;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardRepo bRepo;
	
	
}
