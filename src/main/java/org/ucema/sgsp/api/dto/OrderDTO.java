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
	private String workDateType;

	public OrderDTO() {
		super();
	}

	public static OrderDTO newInstance() {
		return new OrderDTO();
	}

	public OrderDTO build() {
		OrderDTO result = new OrderDTO();

		result.setId(id);
		result.setPendingNotify(pendingNotify);
		result.setPendingQuotes(pendingQuotes);
		result.setPlace(place);
		result.setTitle(title);
		result.setUserId(userId);
		result.setWorkAreaId(workAreaId);
		result.setWorkDate(workDate);
		result.setWorkDateType(workDateType);
		result.setWorkDescription(workDescription);
		result.setWorkProblem(workProblem);

		return result;
	}

	public OrderDTO withId(Long id) {
		this.id = id;
		return this;
	}
	
	public OrderDTO withUserId(Long userId) {
		this.userId = userId;
		return this;
	}
	
	public OrderDTO withWorkAreaId(Long workAreaId) {
		this.workAreaId = workAreaId;
		return this;
	}
	
	public OrderDTO withWorkDate(Date workDate) {
		this.workDate = workDate;
		return this;
	}
	
	public OrderDTO withWorkProblem(String workProblem) {
		this.workProblem = workProblem;
		return this;
	}	
	
	public OrderDTO withWorkDescription(String workDescription) {
		this.workDescription = workDescription;
		return this;
	}	
	
	public OrderDTO withWorkDateType(String workDateType) {
		this.workDateType = workDateType;
		return this;
	}	
	
	public OrderDTO withPendingNotify(Boolean pendingNotify) {
		this.pendingNotify = pendingNotify;
		return this;
	}
	
	public OrderDTO withPendingQuotes(Boolean pendingQuotes) {
		this.pendingQuotes = pendingQuotes;
		return this;
	}
	
	public OrderDTO withPlace(String place) {
		this.place = place;
		return this;
	}
	
	public OrderDTO withTitle(String title) {
		this.title = title;
		return this;
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

	public String getWorkDateType() {
		return workDateType;
	}

	public void setWorkDateType(String workDateType) {
		this.workDateType = workDateType;
	}
}
