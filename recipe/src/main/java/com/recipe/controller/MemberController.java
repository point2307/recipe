package com.recipe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.recipe.dto.Member;
import com.recipe.service.MemberServiceImpl;

@Controller
@RequestMapping("/member/")
public class MemberController {

	@Autowired
	private MemberServiceImpl memServ;
	@Autowired
	private PasswordEncoder encoder;
	
	
	@GetMapping("register_form")
	public String registerForm() {
		return "/member/register";
	}
	
	@PostMapping("register")
	public String register(Member vo, String low_pass) {

		vo.setPassword(encoder.encode(low_pass));
		memServ.register(vo);
		return "/member/login";
	}	
}
