package com.recipe.dto;

import java.util.Date;
import java.util.List;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Getter
@Setter
@ToString(exclude = {"board_recipe", "board_mealkit", "replyList"})
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
	private	String	boardImage;  // 후기에만 사용 가능
	private	int 	boardLikedCount;
	private	int	    boardAlert;
	private int boardCnt;  // 조회수
	@ManyToOne
	@JoinColumn(name = "after_recipe")
	private Recipe recipe;
	@ManyToOne
	@JoinColumn(name = "after_kit")
	private Mealkit mealkit;
	@OneToMany(mappedBy = "board")
	private List<Reply> replyList;

	@PrePersist
	public void prePersist(){
		boardAlert = 0;
		boardLikedCount = 0;
		boardCnt = 0;
	}

}
