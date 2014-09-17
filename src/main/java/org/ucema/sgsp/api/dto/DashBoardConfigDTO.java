package org.ucema.sgsp.api.dto;

import java.util.ArrayList;
import java.util.List;

public class DashBoardConfigDTO {

	private List<String> cityCodes = new ArrayList<String>();

	public List<String> getCityCodes() {
		return cityCodes;
	}

	public void setCityCodes(List<String> cityCodes) {
		this.cityCodes = cityCodes;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("DashBoardConfigDTO [cityCodes=");
		builder.append(cityCodes);
		builder.append("]");
		return builder.toString();
	}
}
