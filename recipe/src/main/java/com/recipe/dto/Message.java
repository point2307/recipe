package com.recipe.dto;

import java.util.Date;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@Entity
@Getter
@Setter
@ToString
public class Message {
	@Id
	@GeneratedValue
	private String msg_id;
	@OneToOne
	private	Member	msg_sender;
	@OneToOne
	private	Member	msg_reciver;
	private	String	msg_content;
	
	@Temporal(TemporalType.DATE)
	private	Date	msg_regdate;

}
