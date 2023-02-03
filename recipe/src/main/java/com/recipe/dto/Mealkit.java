package com.recipe.dto;

import jakarta.persistence.*;
import org.springframework.web.multipart.MultipartFile;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Entity
@Getter
@Setter
@ToString
public class Mealkit {
	@Id
	@GeneratedValue
	private	Long	kit_id;
	private	String	kit_title;

	private int price;
	@OneToOne
	private	Recipe	kit_recipe;
	private	String	kit_subtitle;
	private	String	kit_detail;

}
