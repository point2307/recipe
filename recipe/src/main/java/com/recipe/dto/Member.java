package com.recipe.dto;

import org.springframework.web.multipart.MultipartFile;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Member {
	@Id
	private String	mem_id;
	private String	mem_password;
	private String	mem_nickname;
	private String	mem_name;
	private String	mem_address;
	private String	mem_account;
	private String	mem_phone;
	private Material	mem_material;
	private Recipe	mem_recipe;
	private Mealkit	mem_mealkit;
	private Funding	mem_funding;
	private MultipartFile	mem_pro_img;
	private Role role;

	
}
