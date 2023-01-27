package com.recipe.dto;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString(exclude = {"mem_material"})
public class Member {
	@Id
	private String	mem_id;
	private String	mem_password;
	private String	mem_nickname;
	private String	mem_name;
	private String	mem_address;
	private String	mem_account;
	private String	mem_phone;
	
	
	private List<Material>	mem_material;
	private MultipartFile	mem_pro_img;
	private Role role;
	private String mem_fax;
	
}
