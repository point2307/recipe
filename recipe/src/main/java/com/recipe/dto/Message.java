package com.recipe.dto;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
@Entity
@Data
public class Message {
	@Id
	private String msg_seq;
	private	Member	msg_sender;
	private	Member	msg_recevier;
	private	String	msg_content;
	private	Date	msg_regdate;

}
