package com.recipe.service;

import com.recipe.dto.Member;

public interface MemberService {

	void register(Member vo);

	int findId(String id);

	void updateMem(Member vo);

	Member getMember(String id);

	void deleteMem(Member vo);
}
