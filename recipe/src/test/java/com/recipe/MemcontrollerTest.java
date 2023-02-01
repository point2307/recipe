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
		
		admin.setUserId("admin");
		admin.setPassword(encoder.encode("qwer"));
		admin.setNickName("관리자");
		admin.setName("관리자");
		admin.setAddress("서울시 관악구 신림동 사옥");
		admin.setAccount("777-00-333555");
		admin.setPhone("010-5534-8157");
		admin.setEmail("playjap35@gmail.com");
		admin.setRole(Role.ROLE_ADMIN);
		admin.setFax("02-0055-2222");
		admin.setProImg(null);
		

		memRepo.save(admin);
		
	}
}
