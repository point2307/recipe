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
public class Contract {
	@Id
	private	Long	contract_id;
	private	String	contract_proc;
	private	Member	contract_partner;
	private	String	contract_form;
	private	int	contract_price;
	private	int	contract_percent;
	private	MultipartFile	contract_image;

}
