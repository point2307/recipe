package com.recipe.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.recipe.dto.Member;
import com.recipe.repo.MemberRepo;

import java.util.Optional;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberRepo memRepo;

	@Override
	public void register(Member vo) {
		memRepo.save(vo);
	}

	@Override
	public int findId(String id) {
		Optional result = memRepo.findById(id);
		if(result.isPresent()){
			return 1;
		} else return 0;
	}


}
