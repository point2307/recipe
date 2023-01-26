package com.recipe.dto;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
@Entity
@Data
public class Recipe {
	@Id
	private	Long	recipe_seq;
	private	String	recipe_title;
	private	int	recipe_ready_time;
	private	int	recipe_cooking_time;
	private	Material	recipe_material;
	private	int	recipe_amount;
	private	MultipartFile	recipe_image;
	private	Member	recipe_writer;
	private	Date	recipe_regedit;
	private	Member	recipe_liked;
	private	int	recipe_alert;

}
