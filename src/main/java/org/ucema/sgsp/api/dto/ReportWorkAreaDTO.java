package org.ucema.sgsp.api.dto;


public class ReportWorkAreaDTO {

	private Integer count = 0;
	private String workAreaDescription;
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public String getWorkAreaDescription() {
		return workAreaDescription;
	}
	public void setWorkAreaDescription(String workAreaDescription) {
		this.workAreaDescription = workAreaDescription;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ReportWorkAreaDTO [count=");
		builder.append(count);
		builder.append(", workAreaDescription=");
		builder.append(workAreaDescription);
		builder.append("]");
		return builder.toString();
	}
}
