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
}
