package com.recipe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.recipe.dto.Member;
import com.recipe.service.MemberServiceImpl;

@SessionAttributes("member")
@Controller
@RequestMapping("/member/")
public class MemberController {

	@Autowired
	private MemberServiceImpl memServ;
	
	@GetMapping("register_form")
	public String registerForm() {
		return "/member/register";
	}
	
	@PostMapping("register")
	public String register(Member vo) {
		
		memServ.register(vo);
		return "/member/login";
	}
	
}
