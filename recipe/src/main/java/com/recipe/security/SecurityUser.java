package com.recipe.security;

import com.recipe.dto.Member;

import java.util.Map;


public class SecurityUser extends SecurityUserDetail {

	private static final long serialVersionUID = 1L;
	private Member member;

	private Map<String, Object> attributes;

	public SecurityUser(Member member){
		super(member);
		this.member = member;
	}

	public SecurityUser(Member member, Map<String, Object> attributes){
		super(member, attributes);
		this.member = member;
		this.attributes = attributes;
	}

	
	public Member getMember() {
		return member;
	}

}
