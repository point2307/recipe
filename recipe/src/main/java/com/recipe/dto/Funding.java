package com.recipe.dto;

import java.util.*;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Getter
@Setter
@ToString(exclude = "fundingKit")
public class Funding {
	@Id
	@SequenceGenerator(name = "fund_seq", sequenceName = "fund_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "fund_seq")
	private	Long fundId;
	private String title;

	private String subtitle;
	private String detail;
	private String fundImage;
	private int fund;   // 현재까지 모인금액
	private	int	 goal;         // 목표 금액
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private	Date finalDate;   // 해당 펀딩 완료 날자
	@OneToMany(mappedBy = "funding", cascade = CascadeType.ALL)
	private List<FundingKit> fundingKit = new ArrayList<>();
	private int likeCount;

	@Transient
	private int checkLike;

	@PrePersist
	public void prePersist(){
		this.fund = 0;
		this.likeCount = 0;
		this.checkLike = 0;
	}


}
