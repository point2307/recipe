package com.recipe.dto;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Entity
@Getter
@Setter
@ToString
public class Mealkit {
	@Id
	@GeneratedValue
	private	Long	kitId;
	private	String	kitName;  //밀키트 이름
	private String exImg;  // 조리예 사진
	private String subTitle;   //간단한 설명
	private int price; // 구매비용
	@OneToOne
	private	Recipe	Recipe;
	private	String	detail;  // 밀키트 상세 정보

	@ManyToOne
	@JoinColumn(name = "funding")
	private Funding funding;

}
