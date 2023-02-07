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
	private String title;
	private String detail;
	private String fundImage;
	@OneToMany(mappedBy = "funding")
	private	List<Mealkit> mealkit;
	private int fund;   // 현재까지 모인금액
	private	int	 goal;         // 목표 금액
	private	Date finalDate;   // 해당 펀딩 완료 날자

	@PrePersist
	public void prePersist(){
		this.fund = 0;
	}


}
