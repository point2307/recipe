package com.recipe.dto;

import java.util.List;

import jakarta.persistence.*;

import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
	
	@OneToMany(mappedBy = "member")
	private List<MyMaterial> material;  // 내가 가진 재료
	private String	proImg;
	
	@Enumerated(EnumType.STRING)
	private Role role;
	private String fax;

	public Member update(String name, String picture){
		this.name = name;
		this.proImg = picture;

		return this;
	}

}
