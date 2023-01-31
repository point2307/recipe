package com.recipe.dto;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@Entity
@Getter
@Setter
@ToString
public class Contract {
	@Id
	@GeneratedValue
	private	Long	contract_id;
	private	String	contract_proc;    // 계약 과정 신청/심사중/요청/완료
	
	@OneToOne
	@JoinColumn(name = "recipe_id")
	private	Recipe contract_recipe;    // 계약 대상 레시피
	private	String	contract_form;   // 계약의 형태 혼합/정액/정률
	private	int		contract_price;           // 계약금
	private	int		contract_percent;       // 이익 분배 비율
	private	String	contract_image;   // 계약서 원본 사진

}
