package com.recipe.dto;

import java.util.Date;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Getter
@Setter
@ToString(exclude = {"board_recipe", "board_mealkit"})
public class Board {
	@Id
	@SequenceGenerator(name = "board_seq", sequenceName = "board_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "board_seq")
	private	Long 	boardId;
	
	@ManyToOne
	@JoinColumn(name = "writer")
	private	Member	boardWriter;
	private	String	boardKind;
	private	String	boardTitle;
	private	String	boardContent;
	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	private	Date	boardRegedit;
	private	String	boardImage;
	private	int 	boardLikedCount;
	private	int	    boardAlert;
	private int boardCnt;  // 조회수
	@ManyToOne
	@JoinColumn(name = "after_recipe")
	private Recipe recipe;
	@ManyToOne
	@JoinColumn(name = "after_kit")
	private Mealkit mealkit;


}
