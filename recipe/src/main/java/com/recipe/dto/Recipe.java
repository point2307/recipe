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
	private	Long		recipeId;
	private	String	recipeTitle;
	private	int		recipeReadyTime;
	private	int		recipeCookingTime;
	@OneToMany
	@JoinColumn(name = "material")
	private	List<Material>	Material;
	private	int	amount;
	private	String image;
	
	@OneToMany(mappedBy = "proc_id", fetch = FetchType.EAGER)
	private  List<RecipeProc> recipe_process = new ArrayList<>();
	@OneToOne
	@JoinColumn(name = "writer")
	private	Member	writer;
	private	Date	recipeRegedit;
	@OneToMany
	@JoinColumn(name = "like_mem")
	private	List<Member>	recipeLiked;
	private	int		recipeAlert;

	@OneToMany
	@JoinColumn(name = "after_recipe")
	private List<Board> recipeBoard;
}
