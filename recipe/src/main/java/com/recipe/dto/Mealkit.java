package com.recipe.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Setter
@ToString(exclude = {"funding", "recipe"})
public class Mealkit {
	@Id
	@SequenceGenerator(name = "kit_seq", sequenceName = "kit_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "kit_seq")
	private	Long	kitId;
	private	String	kitName;  //밀키트 이름
	private String exImg;  // 조리예 사진
	private String subTitle;   //간단한 설명
	private int price; // 구매비용
	@OneToOne
	@JsonIgnore
	private	Recipe	recipe;

	private	String	detail;  // 밀키트 상세 정보

	@OneToMany(mappedBy = "mealkit", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<FundingKit> funding = new ArrayList<>();

}
