package org.ucema.sgsp.api.dto;

import java.io.Serializable;
import java.util.Date;

public class OrderDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private Long userId;
	private String title;
	private Long workAreaId;
	private String workDescription;
	private String workProblem;
	private Date workDate;
	private String place;
	private Boolean pendingNotify = true;
	private Boolean pendingQuotes = true;

	public OrderDTO() {
		super();
	}

	public OrderDTO(Long id, Long userId, String title, Long workAreaId,
			String workDescription, String workProblem, Date workDate,
			String place, Boolean pendingNotify, Boolean pendingQuotes) {
		super();
		this.id = id;
		this.userId = userId;
		this.title = title;
		this.workAreaId = workAreaId;
		this.workDescription = workDescription;
		this.workProblem = workProblem;
		this.workDate = workDate;
		this.place = place;
		this.pendingNotify = pendingNotify;
		this.pendingQuotes = pendingQuotes;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getWorkAreaId() {
		return workAreaId;
	}

	public void setWorkAreaId(Long workAreaId) {
		this.workAreaId = workAreaId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getWorkDescription() {
		return workDescription;
	}

	public void setWorkDescription(String workDescription) {
		this.workDescription = workDescription;
	}

	public String getWorkProblem() {
		return workProblem;
	}

	public void setWorkProblem(String workProblem) {
		this.workProblem = workProblem;
	}

	public Date getWorkDate() {
		return workDate;
	}

	public void setWorkDate(Date workDate) {
		this.workDate = workDate;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public Boolean getPendingNotify() {
		return pendingNotify;
	}

	public void setPendingNotify(Boolean pendingNotify) {
		this.pendingNotify = pendingNotify;
	}

	public Boolean getPendingQuotes() {
		return pendingQuotes;
	}

	public void setPendingQuotes(Boolean pendingQuotes) {
		this.pendingQuotes = pendingQuotes;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("OrderDTO [id=");
		builder.append(id);
		builder.append(", userId=");
		builder.append(userId);
		builder.append(", title=");
		builder.append(title);
		builder.append(", workAreaId=");
		builder.append(workAreaId);
		builder.append(", workDescription=");
		builder.append(workDescription);
		builder.append(", workProblem=");
		builder.append(workProblem);
		builder.append(", workDate=");
		builder.append(workDate);
		builder.append(", place=");
		builder.append(place);
		builder.append(", pendingNotify=");
		builder.append(pendingNotify);
		builder.append(", pendingQuotes=");
		builder.append(pendingQuotes);
		builder.append("]");
		return builder.toString();
	}
}
