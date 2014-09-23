package org.ucema.sgsp.api.transformation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.ucema.sgsp.api.dto.RatePlanDTO;
import org.ucema.sgsp.persistence.model.RatePlan;

@Component
public class RatePlanTransformation {

	@Autowired
	private AmountTransformation amountTransformation;

	public List<RatePlanDTO> transformToApi(List<RatePlan> ratePlans) {
		List<RatePlanDTO> result = new ArrayList<RatePlanDTO>();

		for (RatePlan ratePlan : ratePlans) {
			result.add(transformToApi(ratePlan));
		}

		return result;
	}

	public RatePlanDTO transformToApi(RatePlan ratePlan) {
		RatePlanDTO result = new RatePlanDTO();

		result.setCode(ratePlan.getCode());
		result.setId(ratePlan.getId());
		result.setAmount(amountTransformation.buildAmount(ratePlan.getAmount()));
		result.setCreatedAt(ratePlan.getCreatedAt());
		result.setIsEnabled(ratePlan.getIsEnabled());
		result.setPackageType(ratePlan.getPackageType().name());
		result.setPercentageQuantity(ratePlan.getPercentageQuantity());
		result.setPeriodType(ratePlan.getPeriodType().name());
		result.setServiceQuantity(ratePlan.getServiceQuantity());

		return result;
	}
}
