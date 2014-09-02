package org.ucema.sgsp.persistence.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "cities", uniqueConstraints = @UniqueConstraint(name = "uq_city_code", columnNames = { "code" }))
public class City {

	@Id
	@GeneratedValue
	private Long id;
	private String code;
	private String description;
	@Column(name = "is_enabled")
	private Boolean isEnabled;
	@ManyToOne
	@JoinColumn(name = "state_id", foreignKey = @ForeignKey(name = "fk_city_state"))
	private State state;	

	public City(Long id) {
		super();
		this.id = id;
	}

	public City() {
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getIsEnabled() {
		return isEnabled;
	}

	public void setIsEnabled(Boolean isEnabled) {
		this.isEnabled = isEnabled;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}
}
