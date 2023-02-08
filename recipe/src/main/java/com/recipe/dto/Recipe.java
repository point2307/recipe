package com.recipe.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Getter
@Setter
@ToString(exclude = {"rawMaterList", "recipe_process", "writer", "replyList"})
public class Recipe {
	@Id
	@SequenceGenerator(name = "recipe_seq", sequenceName = "recipe_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "recipe_seq")
	private	Long		recipeId;
	private	String	recipeTitle;
	private	int		recipeCookingTime;

	private	int	amount;
	private	String image;

	private int likeCount;  //좋아요 개수
	private String recipeDetail;
	@OneToMany(mappedBy = "rawId")
	@JsonIgnore
	private List<RawMater> rawMaterList = new ArrayList<>();
	
	@OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL)
	@JsonIgnore
	private  List<RecipeProc> recipe_process = new ArrayList<>();
	@ManyToOne
	@JoinColumn(name = "writer")
	@JsonIgnore
	private	Member writer;
	@CreationTimestamp
	private	Date	recipeRegedit;
	private	int		recipeAlert;

	@OneToMany(mappedBy = "replyId",fetch = FetchType.EAGER)
	private List<Reply> replyList;

	@PrePersist
	public void prePersist(){
		this.likeCount = 0;
	}
}
