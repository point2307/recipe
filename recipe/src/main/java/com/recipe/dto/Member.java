package com.recipe.dto;

import java.util.List;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString(exclude = {"material"})
public class Member {
	@Id
	private String	userId;
	private String	password;
	private String	nickName;
	private String	name;
	private String	address;
	private String	phone;
	private String email;
	private char agree; // 약관 동의 여부
	private String	account;
	
	@OneToMany
	private List<Material>	material;  // 내가 가진 재료
	private String	proImg;
	
	@Enumerated(EnumType.STRING)
	private Role role;
	private String fax;
	
}
