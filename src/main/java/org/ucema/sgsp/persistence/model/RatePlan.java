package org.ucema.sgsp.persistence.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "rate_plans")
public class RatePlan {

	@Id
	@GeneratedValue
	private Long id;
	private String code;	
	@Embedded
	private Amount amount;
    @Enumerated(EnumType.STRING)
    @Column(name = "package_type")
	private RatePlanPackageType packageType;	
    private Integer serviceQuantity;
    private BigDecimal percentageQuantity;
    @Enumerated(EnumType.STRING)
    @Column(name = "period_type")
	private RatePlanPeriodType periodType;
	@Column(name = "is_enabled")
	private Boolean isEnabled;
	@Column(name = "created_at")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;

	public RatePlan() {
		super();
	}

	public RatePlan(Long id) {
		super();
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Amount getAmount() {
		return amount;
	}

	public void setAmount(Amount amount) {
		this.amount = amount;
	}

	public RatePlanPackageType getPackageType() {
		return packageType;
	}

	public void setPackageType(RatePlanPackageType packageType) {
		this.packageType = packageType;
	}

	public Integer getServiceQuantity() {
		return serviceQuantity;
	}

	public void setServiceQuantity(Integer serviceQuantity) {
		this.serviceQuantity = serviceQuantity;
	}

	public BigDecimal getPercentageQuantity() {
		return percentageQuantity;
	}

	public void setPercentageQuantity(BigDecimal percentageQuantity) {
		this.percentageQuantity = percentageQuantity;
	}

	public RatePlanPeriodType getPeriodType() {
		return periodType;
	}

	public void setPeriodType(RatePlanPeriodType periodType) {
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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
