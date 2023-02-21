package com.recipe;


import com.recipe.dto.Recipe;
import com.recipe.persistence.RecipeRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.recipe.dto.Member;
import com.recipe.dto.Role;
import com.recipe.persistence.MemberRepo;

@SpringBootTest
public class MemcontrollerTest {

	@Autowired
	private MemberRepo memRepo;
	
	@Autowired
	private PasswordEncoder encoder;

	@Autowired
	private RecipeRepo recipeRepo;
	@Test
	public void makeAdmin() {
		for(int i = 0; i<80; i++){
			Recipe recipe = new Recipe();
			recipe.setRecipeId(100l+i);
			recipe.setRecipeTitle("dummyRecipe"+i);
			recipe.setCookingTime(45);
			recipeRepo.save(recipe);
		}
		
	}
}
