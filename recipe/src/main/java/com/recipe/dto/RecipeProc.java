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
	@JoinColumn(name = "recipe")
	private Recipe procRcp;
	private int procNum;
	private String procPicName;
	private String procDetail;
	
}
