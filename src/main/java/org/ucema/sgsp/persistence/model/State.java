package org.ucema.sgsp.persistence.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;

@Entity
@Table(name = "states")
public class State {

	@Id
	@GeneratedValue
	private Long id;
	private String code;
	private String description;
	@OneToMany(orphanRemoval=true)
	@Cascade(org.hibernate.annotations.CascadeType.ALL)
	@JoinColumn(name = "state_id", foreignKey = @ForeignKey(name = "fk_states_cities"))
	private List<City> cities;	
	@Column(name = "is_enabled")
	private Boolean isEnabled;
	@ManyToOne
	@JoinColumn(name = "country_id", foreignKey = @ForeignKey(name = "fk_state_country"))
	private Country country;	

	public State(Long id) {
		super();
		this.id = id;
	}

	public State() {
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

	public List<City> getCities() {
		return cities;
	}

	public void setCities(List<City> cities) {
		this.cities = cities;
	}

	public Boolean getIsEnabled() {
		return isEnabled;
	}

	public void setIsEnabled(Boolean isEnabled) {
		this.isEnabled = isEnabled;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}
}
