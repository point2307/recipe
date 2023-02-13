package com.recipe.dto;

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
@ToString(exclude = {"replyWriter", "board", "recipe", "funding"})
public class Reply {
	@Id
	@SequenceGenerator(name = "reply_seq", sequenceName = "reply_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reply_seq")
	private	Long	replyId;
	@OneToOne
	@JoinColumn(name = "writer")
	@JsonIgnore
	private Member	writer;
	private	String	content;
	
	@Temporal(TemporalType.DATE)
	@CreationTimestamp
	private	Date regdate;
	
	private int likeCount;

	@ManyToOne
	@JoinColumn(name = "boardId")
	@JsonIgnore
	private Board board;

	@ManyToOne
	@JoinColumn(name = "recipeId")
	@JsonIgnore
	private Recipe recipe;

	@ManyToOne
	@JoinColumn(name = "fundingId")
	@JsonIgnore
	private Funding funding;

	@PrePersist
	public void prePersist(){
		this.likeCount = 0;
	}
}
