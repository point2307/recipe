package com.recipe.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@Entity
@Getter
@Setter
@ToString
public class Admin {
	@Id
	private	String	adm_id;
	private	String	adm_password;
	private	String	adm_name;
	private	String	adm_account;
	private	String	adm_phone;
	private	Role	role;
	private	String	adm_fax;

}
