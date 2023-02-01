package com.recipe.dto;

import java.util.List;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.DialectOverride;

@Entity
@Getter
@Setter
@ToString(exclude = {"mem_material"})
public class Member {
	@Id
	private String	userId;
	private String	password;
	private String	nickName;
	private String	name;
	private String	address;
	private String	phone;
	private String email;
	
	private String	account;
	
	@OneToMany
	@JoinColumn(name = "my_material")
	private List<Material>	material;
	private String	proImg;
	
	@Enumerated(EnumType.STRING)
	private Role role;
	private String fax;
	
}
