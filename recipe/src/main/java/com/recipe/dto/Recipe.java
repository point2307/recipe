package com.recipe.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@Entity
@Getter
@Setter
@ToString
public class Recipe {
	@Id
	@GeneratedValue
	private	Long		recipe_id;
	private	String	recipe_title;
	private	int		recipe_ready_time;
	private	int		recipe_cooking_time;
	@OneToMany
	@JoinColumn(name = "material")
	private	List<Material>		recipe_material;
	private	int	amount;
	private	String image;
	
	@OneToMany(mappedBy = "proc_id", fetch = FetchType.EAGER)
	private  List<RecipeProc> recipe_process = new ArrayList<>();
	@OneToOne
	@JoinColumn(name = "writer")
	private	Member	writer;
	private	Date	recipe_regedit;
	@OneToMany
	@JoinColumn(name = "like_mem")
	private	List<Member>	recipe_liked;
	private	int		recipe_alert;

	@OneToMany
	@JoinColumn(name = "after_recipe")
	private List<Board> recipeBoard;
}
