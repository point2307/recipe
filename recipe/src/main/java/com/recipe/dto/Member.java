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
	private String	mem_id;
	private String	mem_password;
	private String	mem_nickname;
	private String	mem_name;
	private String	mem_address;
	private String	mem_phone;
	private String mem_email;
	
	private String	mem_account;
	
	@OneToMany
	@JoinColumn(name = "my_material")
	private List<Material>	mem_material;
	private String	mem_pro_img;
	
	@Enumerated(EnumType.STRING)
	private Role role;
	private String mem_fax;
	
}
