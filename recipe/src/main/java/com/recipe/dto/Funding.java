package com.recipe.dto;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
@Entity
@Data
public class Funding {
	@Id
	private	Long	funding_id;
	private	Mealkit	finding_mealkit;
	private	int	funding_goal;
	private	Date	funding_object_date;
	private	Contract	funding_contract;
	private	Member	funding_customer;

}
