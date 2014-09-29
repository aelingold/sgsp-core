package org.ucema.sgsp.api.dto;


public class ReportUserDTO {

	private Integer userCount = 0;
	private Integer isProfessionalCount = 0;
	private String yearMonth;

	public Integer getUserCount() {
		return userCount;
	}

	public void setUserCount(Integer userCount) {
		this.userCount = userCount;
	}

	public Integer getIsProfessionalCount() {
		return isProfessionalCount;
	}

	public void setIsProfessionalCount(Integer isProfessionalCount) {
		this.isProfessionalCount = isProfessionalCount;
	}

	public String getYearMonth() {
		return yearMonth;
	}

	public void setYearMonth(String yearMonth) {
		this.yearMonth = yearMonth;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ReportUserDTO [userCount=");
		builder.append(userCount);
		builder.append(", isProfessionalCount=");
		builder.append(isProfessionalCount);
		builder.append(", yearMonth=");
		builder.append(yearMonth);
		builder.append("]");
		return builder.toString();
	}
}
