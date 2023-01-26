package com.recipe.dto;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
@Entity
@Data
public class Reply {
	@Id
	private	Long	reply_id;
	private Member	reply_writer;
	private	String	reply_content;
	private	Date	reply_regedit;
	private	Member	reply_like;

}
