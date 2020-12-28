package com.example.hashtagcount.entities;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class MessageBean {

	@NotNull(message = "Message tweet cannot be blank")
	private String message;
	@NotEmpty(message = "UserName cannot be blank")
	private String userName;

	public String getUserName() {
		return userName;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}
