package com.salesmanager.web.entity.shop;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

public class ContactForm {
	
	@NotEmpty
	private String name;
	@NotEmpty
	private String subject;
	@Email
	private String email;
	@NotEmpty
	private String comment;
	

	private String captchaResponseField;
	private String captchaChallengeField;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getCaptchaResponseField() {
		return captchaResponseField;
	}
	public void setCaptchaResponseField(String captchaResponseField) {
		this.captchaResponseField = captchaResponseField;
	}
	public String getCaptchaChallengeField() {
		return captchaChallengeField;
	}
	public void setCaptchaChallengeField(String captchaChallengeField) {
		this.captchaChallengeField = captchaChallengeField;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}


}
