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
	@SequenceGenerator(name = "buy_seq", sequenceName = "buy_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "buy_seq")
	private Long buyId;
	
	@ManyToOne
	@JoinColumn(name = "buyer")
	private Member buyer;
	@CreationTimestamp
	@Temporal(TemporalType.DATE)
	private	Date	buyDate;
	@OneToMany
	private List<BuyDetail> buyDetails;
	private	String totalPrice;
	private String processing;

}
