package com.recipe.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.recipe.repo.MemberRepo;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberRepo memRepo;
	
	
}
