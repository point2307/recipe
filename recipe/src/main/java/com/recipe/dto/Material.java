package com.recipe.dto;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@Entity
@Getter
@Setter
@ToString
public class Material {
	@Id
	private	String	mtrl_id;
	private	String	mtrl_name;
	
	@Temporal(TemporalType.DATE)
	private	Date	exp;

}
