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
	@SequenceGenerator(name = "msg_seq", sequenceName = "msg_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "msg_seq")
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
