package com.recipe;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.multipart.MultipartFile;

import com.recipe.dto.Member;
import com.recipe.dto.Role;
import com.recipe.repo.MemberRepo;

@SpringBootTest
public class MemcontrollerTest {

	@Autowired
	private MemberRepo memRepo;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Test
	public void makeAdmin() {
		Member admin = new Member();
		
		admin.setMem_id("admin");
		admin.setMem_password(encoder.encode("qwer"));
		admin.setMem_nickname("관리자");
		admin.setMem_name("관리자");
		admin.setMem_address("서울시 관악구 신림동 사옥");
		admin.setMem_account("777-00-333555");
		admin.setMem_phone("010-5534-8157");
		admin.setMem_email("playjap35@gmail.com");
		admin.setRole(Role.ROLE_ADMIN);
		admin.setMem_fax("02-0055-2222");
		admin.setMem_pro_img(null);
		

		memRepo.save(admin);
		
	}
}
