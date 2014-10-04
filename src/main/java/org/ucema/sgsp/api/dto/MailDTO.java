package org.ucema.sgsp.api.dto;

import java.io.Serializable;
import java.util.Map;

public class MailDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private String statusType;
    private String mailTo;
    private String mailFrom;
    private String subject;
	private String templatePath;
	private Map<String,Object> model; 

	public static MailDTO newInstance() {
		return new MailDTO();
	}

	public MailDTO build() {
		MailDTO result = new MailDTO();

		result.setId(id);

		return result;
	}	
		
	public MailDTO withId(Long id) {
		this.id = id;
		return this;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStatusType() {
		return statusType;
	}

	public void setStatusType(String statusType) {
		this.statusType = statusType;
	}

	public String getMailTo() {
		return mailTo;
	}

	public void setMailTo(String mailTo) {
		this.mailTo = mailTo;
	}

	public String getMailFrom() {
		return mailFrom;
	}

	public void setMailFrom(String mailFrom) {
		this.mailFrom = mailFrom;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getTemplatePath() {
		return templatePath;
	}

	public void setTemplatePath(String templatePath) {
		this.templatePath = templatePath;
	}

	public Map<String, Object> getModel() {
		return model;
	}

	public void setModel(Map<String, Object> model) {
		this.model = model;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MailDTO [id=");
		builder.append(id);
		builder.append(", statusType=");
		builder.append(statusType);
		builder.append(", mailTo=");
		builder.append(mailTo);
		builder.append(", mailFrom=");
		builder.append(mailFrom);
		builder.append(", subject=");
		builder.append(subject);
		builder.append(", templatePath=");
		builder.append(templatePath);
		builder.append(", model=");
		builder.append(model);
		builder.append("]");
		return builder.toString();
	}
}
