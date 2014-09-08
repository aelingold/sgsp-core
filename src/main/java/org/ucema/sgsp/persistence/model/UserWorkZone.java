package org.ucema.sgsp.persistence.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cascade;
import org.ucema.sgsp.security.model.User;

@Entity
@Table(name = "user_work_zones")
public class UserWorkZone {

	@Id
	@GeneratedValue
	private Long id;
	@ManyToOne
	@JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "fk_user_work_zone_user"))
	private User user;
	@ManyToOne
	@JoinColumn(name = "state_id", foreignKey = @ForeignKey(name = "fk_user_work_zone_state"))
	private State state;
	@OneToMany(orphanRemoval = true)
	@Cascade(org.hibernate.annotations.CascadeType.ALL)
	@JoinTable(name = "user_work_zone_cities", joinColumns = @JoinColumn(name = "user_work_zone_id"), inverseJoinColumns = @JoinColumn(name = "city_id"))
	private List<City> cities;
	@Column(name = "all_cities")
	private Boolean allCities;
	@Column(name = "created_at")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public List<City> getCities() {
		return cities;
	}

	public void setCities(List<City> cities) {
		this.cities = cities;
	}

	public Boolean getAllCities() {
		return allCities;
	}

	public void setAllCities(Boolean allCities) {
		this.allCities = allCities;
	}
}
