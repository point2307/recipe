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
@ToString(exclude = {"replyWriter" , "likey"})
public class Reply {
	@Id
	@GeneratedValue
	private	Long	replyId;
	@OneToOne
	@JoinColumn(name = "writer")
	private Member	replyWriter;
	private	String	replyContent;
	
	@Temporal(TemporalType.DATE)
	@CreationTimestamp
	private	Date regdate;
	
	private int likeCount;

	@ManyToOne
	@JoinColumn(name = "board_id")
	private Board board;

	@ManyToOne
	@JoinColumn(name = "recipe_id")
	private Recipe recipe;

	@ManyToOne
	@JoinColumn(name = "funding_id")
	private Funding funding;
}
