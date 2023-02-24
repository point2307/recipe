package com.recipe.controller;

import com.recipe.dto.Token;
import com.recipe.service.TokenService;
import com.recipe.util.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.recipe.dto.Member;
import com.recipe.service.MemberServiceImpl;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Controller
@RequestMapping("/member/")
public class MemberController {

	@Autowired
	private MemberServiceImpl memServ;
	@Autowired
	private PasswordEncoder encoder;
	@Autowired
	private TokenService tokenService;
	
	@GetMapping("register_form")
	public String registerForm() {
		return "/member/register";
	}
	
	@PostMapping("register")
	public String register(Member vo, String row_pass, MultipartFile pic, String ema, String il
	,String zip, String bigAdd, String detailAdd) throws IllegalStateException, IOException {
		if(pic.isEmpty()){
			vo.setProImg("noPic.jpg");
		} else{
			File file = new File(UUID.randomUUID().toString(), pic.getOriginalFilename(),
					pic.getContentType());
			java.io.File newFileName = new java.io.File(file.getUuid()+"_"+file.getOriginalName());
			pic.transferTo(newFileName);
			vo.setProImg(newFileName.toString());
		}
		String address = "("+zip+")"+bigAdd+", "+detailAdd;
		vo.setAddress(address);
		vo.setPassword(encoder.encode(row_pass));
		vo.setEmail(ema+"@"+il);
		memServ.register(vo);
		tokenService.createTokenMail(vo.getUserId(), vo.getEmail());
		return "redirect:/system/login";
	}

	@GetMapping("/checkMailToken")
	public String comfirmToken(String token){
		System.out.println("토큰 들어옴"+token);
		tokenService.confirmMail(token);
		return "redirect:/system/login";
	}


	@GetMapping("idCheck")
	@ResponseBody
	public int idCheck(String id){
		return memServ.findId(id);
	}
}

