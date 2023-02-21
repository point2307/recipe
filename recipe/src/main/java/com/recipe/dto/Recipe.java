package com.recipe.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Getter
@Setter
@ToString(exclude = {"rawMaterList", "recipe_process", "writer", "replyList", "likedList"})
public class Recipe {
	@Id
	@SequenceGenerator(name = "recipe_seq", sequenceName = "recipe_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "recipe_seq")
	private	Long		recipeId;
	private	String	recipeTitle;
	private	int		cookingTime;

	private	int	amount;
	private	String image;

	private int likeCount;  //좋아요 개수
	private String recipeDetail;
	@OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL)
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
	@Column(updatable = false)
	private	Date	recipeRegedit;
	private	int		recipeAlert;

	@OneToMany(mappedBy = "recipe",fetch = FetchType.LAZY)
	@JsonIgnore
	@OrderBy("regdate")
	private List<Reply> replyList;

	@OneToMany(mappedBy = "recipe")
	@JsonIgnore
	private List<Likey> likeyList;

	@Transient
	private int checkLike;

	@PrePersist
	public void prePersist(){
		this.likeCount = 0;
		this.checkLike = 0;
	}
}
