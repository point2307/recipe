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
	private	Long 	board_id;
	
	@ManyToOne
	@JoinColumn(name = "writer")
	private	Member	board_writer;
	private	String	board_kind;
	private	String	board_title;
	private	String	board_content;
	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	private	Date	board_regedit;
	private	String	board_image;
	private	int 	board_liked;
	private	int	    board_alert;
	@ManyToOne
	@JoinColumn(name = "after_recipe")
	private Recipe board_recipe;
	@ManyToOne
	@JoinColumn(name = "after_kit")
	private Mealkit board_mealkit;

}
