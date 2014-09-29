package org.ucema.sgsp.api.dto;

public class ReportWorkAreaDTO {

	private Integer count = 0;
	private String workAreaDescription;
	private String date;

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

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "ReportWorkAreaDTO [count=" + count + ", workAreaDescription="
				+ workAreaDescription + ", date=" + date + "]";
	}
}
