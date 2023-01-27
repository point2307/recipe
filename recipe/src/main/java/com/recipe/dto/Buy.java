package com.recipe.dto;

import java.util.Date;

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
public class Buy {
	@Id
	private	Member	buy_buyer;
	private	Date	buy_date;
	private	Mealkit	buy_kit;
	private	int	buy_quantity;
	private	String	buy_process;
	private	String	buy_condition;

}
