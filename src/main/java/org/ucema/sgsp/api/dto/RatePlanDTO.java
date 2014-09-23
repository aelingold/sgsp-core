package org.ucema.sgsp.api.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class RatePlanDTO implements Serializable {

	public static final String PLAN1 = "PLAN1";
	public static final String PLAN2 = "PLAN2";
	public static final String PLAN3 = "PLAN3";
	
	private static final long serialVersionUID = 1L;
	private Long id;
	private String code;
	private AmountDTO amount;
	private String packageType;	
    private BigDecimal percentageQuantity;
	private String periodType;
	private Boolean isEnabled;
	private Date createdAt;

	public static RatePlanDTO newInstance() {
		return new RatePlanDTO();
	}

	public RatePlanDTO build() {
		RatePlanDTO result = new RatePlanDTO();

		result.setCode(code);
		result.setId(id);
		result.setAmount(amount);
		result.setCreatedAt(createdAt);
		result.setIsEnabled(isEnabled);
		result.setPackageType(packageType);
		result.setPercentageQuantity(percentageQuantity);
		result.setPeriodType(periodType);

		return result;
	}	
	
	public RatePlanDTO withId(Long id) {
		this.id = id;
		return this;
	}	
	
	public RatePlanDTO withCode(String code) {
		this.code = code;
		return this;
	}	

	public RatePlanDTO() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public AmountDTO getAmount() {
		return amount;
	}

	public void setAmount(AmountDTO amount) {
		this.amount = amount;
	}

	public String getPackageType() {
		return packageType;
	}

	public void setPackageType(String packageType) {
		this.packageType = packageType;
	}

	public BigDecimal getPercentageQuantity() {
		return percentageQuantity;
	}

	public void setPercentageQuantity(BigDecimal percentageQuantity) {
		this.percentageQuantity = percentageQuantity;
	}

	public String getPeriodType() {
		return periodType;
	}

	public void setPeriodType(String periodType) {
		this.periodType = periodType;
	}

	public Boolean getIsEnabled() {
		return isEnabled;
	}

	public void setIsEnabled(Boolean isEnabled) {
		this.isEnabled = isEnabled;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	@Override
	public String toString() {
		return "RatePlanDTO [id=" + id + ", code=" + code + ", amount="
				+ amount + ", packageType=" + packageType
				+ ", percentageQuantity=" + percentageQuantity
				+ ", periodType=" + periodType + ", isEnabled=" + isEnabled
				+ ", createdAt=" + createdAt + "]";
	}
}
