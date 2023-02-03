package com.recipe.dto;

import java.util.Date;
import java.util.List;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@Entity
@Getter
@Setter
@ToString
public class Buy {
	@Id
	@GeneratedValue
	private Long buy_id;
	
	@OneToOne
	@JoinColumn(name = "buyer")
	private Member buyer;
	
	@Temporal(TemporalType.DATE)
	private	Date	buy_date;
	
	@OneToOne
	@JoinColumn(name = "buy_kit")
	private	Mealkit	buy_kit;
	private	int		buy_quantity;
	private	String	buy_process;
	private	String	buy_condition;

}
