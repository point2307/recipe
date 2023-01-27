package com.recipe.dto;

import org.springframework.web.multipart.MultipartFile;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@Entity
@Getter
@Setter
@ToString
public class Mealkit {
	@Id
	private	Long	kit_seq;
	private	String	kit_title;
	private	Recipe	kit_recipe;
	private	String	kit_subtitle;
	private	MultipartFile	kit_detail;

}
