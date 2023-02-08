package com.recipe.dto;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString(exclude = "recipe")
public class RecipeProc {


	@Id
	@SequenceGenerator(name = "proc_seq", sequenceName = "proc_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "proc_seq")
	private Long procId;
	
	@ManyToOne
	@JoinColumn(name = "recipe")
	private Recipe recipe;
	private String procPicName;
	private String procDetail;

	public RecipeProc() {
	}
}
