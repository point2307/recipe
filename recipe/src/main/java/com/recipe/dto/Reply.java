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
@ToString(exclude = {"replyWriter"})
public class Reply {
	@Id
	@SequenceGenerator(name = "reply_seq", sequenceName = "reply_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reply_seq")
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
	@JoinColumn(name = "boardId")
	private Board board;

	@ManyToOne
	@JoinColumn(name = "recipeId")
	private Recipe recipe;

	@ManyToOne
	@JoinColumn(name = "fundingId")
	private Funding funding;
}
