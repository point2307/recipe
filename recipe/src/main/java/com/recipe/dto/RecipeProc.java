package com.recipe.dto;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString(exclude = "proc_rcp")
public class RecipeProc {

	@Id
	@GeneratedValue
	private Long proc_id;
	
	@ManyToOne
	@JoinColumn
	private Recipe proc_rcp;
	private int proc_num;
	private String proc_picName;
	private String proc_detail;
	
}
