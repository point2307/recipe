package com.recipe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@RequestMapping("/system")
@SessionAttributes("member")
@Controller
public class SecurityController {

	
	@GetMapping("/login")
	public void loginform() {
		
	}

	
	@GetMapping("/accessDenied")
	public void accessDenied() {
	}
	
	@GetMapping("/logout")
	public void logout() { }
	
	@GetMapping("/admin/adminPage")
	public void adminPage() { }
}


