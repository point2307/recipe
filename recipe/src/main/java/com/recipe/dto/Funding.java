package com.recipe.dto;

import java.util.*;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@Entity
@Getter
@Setter
@ToString
public class Funding {
	@Id
	@GeneratedValue
	private	Long	funding_id;
	
	@OneToOne
	@JoinColumn(name = "funding_kit")
	private	Mealkit	funding_mealkit;
	private	int	 goal;         // 목표 금액
	private	Date finalDate;   // 해당 펀딩 완료 날자
	
	@OneToOne
	@JoinColumn(name = "Contract_id")
	private	Contract contract;
	
	@OneToMany
	@JoinColumn(name = "donation")
	private	List<Member> donation;    // 구매자 목록

}
