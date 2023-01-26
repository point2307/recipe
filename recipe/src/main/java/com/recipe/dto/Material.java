package com.recipe.dto;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
@Entity
@Data
public class Material {
	@Id
	private	String	mtrl_id;
	private	String	mtrl_name;
	private	Date	exp;

}
