package com.recipe.dto;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString(exclude = "procRcp")
public class RecipeProc {


	@Id
	@GeneratedValue
	private Long procId;
	
	@ManyToOne
	private Recipe recipe;
	private String procPicName;
	private String procDetail;

	public RecipeProc() {
	}
}
