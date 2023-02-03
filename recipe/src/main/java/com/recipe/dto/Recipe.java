package com.recipe.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Getter
@Setter
@ToString
public class Recipe {
	@Id
	@GeneratedValue
	private	Long		recipeId;
	private	String	recipeTitle;
	private	int		recipeCookingTime;

	private	int	amount;
	private	String image;

	@OneToMany(mappedBy = "rawId",fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<RawMater> rawMaterList = new ArrayList<>();
	
	@OneToMany(mappedBy = "procId", fetch = FetchType.EAGER)
	private  List<RecipeProc> recipe_process = new ArrayList<>();
	@OneToOne
	@JoinColumn(name = "writer")
	private	Member	writer;
	@CreationTimestamp
	private	Date	recipeRegedit;
	@OneToMany
	@JoinColumn(name = "like_mem")
	private	List<Member>	recipeLiked;
	private	int		recipeAlert;

	@OneToMany(mappedBy = "reply_id",fetch = FetchType.EAGER)
	private List<Reply> replyList;
}
