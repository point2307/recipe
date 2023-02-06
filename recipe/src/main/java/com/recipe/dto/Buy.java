package com.recipe.dto;

import java.util.Date;
import java.util.List;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Getter
@Setter
@ToString
public class Buy {
	@Id
	@GeneratedValue
	private Long buyId;
	
	@OneToOne
	@JoinColumn(name = "buyer")
	private Member buyer;
	@CreationTimestamp
	@Temporal(TemporalType.DATE)
	private	Date	buy_date;
	@OneToMany
	private List<Cart> cart;
	private	String totalPrice;

	private String processing;

}
