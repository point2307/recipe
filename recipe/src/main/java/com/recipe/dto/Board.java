package com.recipe.dto;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
@Entity
@Data
public class Board {
	@Id
	private	Long	board_seq;
	private	Member	board_writer;
	private	String	board_kind;
	private	String	board_title;
	private	String	board_content;
	private	Date	board_regedit;
	private	MultipartFile	board_image;
	private	Member	board_liked;
	private	int	board_alert;

}
