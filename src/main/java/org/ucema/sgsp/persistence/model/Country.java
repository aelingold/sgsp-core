package org.ucema.sgsp.persistence.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;

@Entity
@Table(name = "countries")
public class Country {

	@Id
	@GeneratedValue
	private Long id;
	private String code;
	private String description;
	@OneToMany(orphanRemoval=true)
	@Cascade(org.hibernate.annotations.CascadeType.ALL)
	@JoinColumn(name = "country_id", foreignKey = @ForeignKey(name = "fk_user_work_rate_user"))
	private List<State> states;

	public Country(Long id) {
		super();
		this.id = id;
	}

	public Country() {
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

	public List<State> getStates() {
		return states;
	}

	public void setStates(List<State> states) {
		this.states = states;
	}
	
	
}
