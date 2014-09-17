package org.ucema.sgsp.api.dto;


public class ReportUserDTO {

	private Integer userCount = 0;
	private Integer isProfessionalCount = 0;

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

	@Override
	public String toString() {
		return "ReportUserDTO [userCount=" + userCount
				+ ", isProfessionalCount=" + isProfessionalCount + "]";
	}
}
