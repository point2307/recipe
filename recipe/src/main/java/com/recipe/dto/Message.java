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
	private String msgId;
	@OneToOne
	@JoinColumn(name = "sender")
	private	Member	sender;
	@OneToOne
	@JoinColumn(name = "receiver")
	private	Member	receiver;
	private	String	msgContent;
	
	@Temporal(TemporalType.DATE)
	private	Date	msgRegdate;

}
