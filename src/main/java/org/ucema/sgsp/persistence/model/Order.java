package org.ucema.sgsp.persistence.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cascade;
import org.ucema.sgsp.security.model.User;

@Entity
@Table(name = "orders")
public class Order {

	@Id
	@GeneratedValue
	private Long id;
	@ManyToOne
	@JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "fk_order_user"))
	private User user;
	@ManyToOne
	@JoinColumn(name = "work_area_id", foreignKey = @ForeignKey(name = "fk_order_work_area"))
	private WorkArea workArea;
	@Column(name = "work_description")
	private String workDescription;
	@Column(name = "work_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date workDate;
    @Enumerated(EnumType.STRING)
    @Column(name = "work_date_type", length = 20, nullable = false)	
	private WorkDateType workDateType;
	@Column(name = "pending_notify")
	private Boolean pendingNotify;
	@Column(name = "created_at")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;
	@Column(name = "updated_at")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedAt;
	@OneToMany(mappedBy = "order",orphanRemoval=true)
	@Cascade(org.hibernate.annotations.CascadeType.ALL)
	private List<OrderItem> orderItems;
	@OneToOne
	@JoinColumn(name = "state_id", foreignKey = @ForeignKey(name = "fk_order_state"))
	private State state;
	@OneToOne
	@JoinColumn(name = "city_id", foreignKey = @ForeignKey(name = "fk_order_city"))	
	private City city;
	@Column(name = "square_meters")
	private BigDecimal squareMeters;
	@Column(name = "air_conditioner_power")
	private Integer airConditionerPower;
	@OneToMany
	@JoinColumn(name = "order_id", foreignKey = @ForeignKey(name = "fk_order_quotes"))
	private List<Quote> quotes;
    @Enumerated(EnumType.STRING)
    @Column(name = "status_type")
	private OrderStatusType statusType;	

	public Order(Long id) {
		super();
		this.id = id;
	}

	public Order() {
		super();
	}
	
    @PrePersist
    public void prePersist() {
    	Date now = new Date();
        this.createdAt = now;
        this.updatedAt = now;
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = new Date();
    }	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public WorkArea getWorkArea() {
		return workArea;
	}

	public void setWorkArea(WorkArea workArea) {
		this.workArea = workArea;
	}

	public String getWorkDescription() {
		return workDescription;
	}

	public void setWorkDescription(String workDescription) {
		this.workDescription = workDescription;
	}

	public Date getWorkDate() {
		return workDate;
	}

	public void setWorkDate(Date workDate) {
		this.workDate = workDate;
	}

	public Boolean getPendingNotify() {
		return pendingNotify;
	}

	public void setPendingNotify(Boolean pendingNotify) {
		this.pendingNotify = pendingNotify;
	}

	public WorkDateType getWorkDateType() {
		return workDateType;
	}

	public void setWorkDateType(WorkDateType workDateType) {
		this.workDateType = workDateType;
	}

	public List<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public BigDecimal getSquareMeters() {
		return squareMeters;
	}

	public void setSquareMeters(BigDecimal squareMeters) {
		this.squareMeters = squareMeters;
	}

	public Integer getAirConditionerPower() {
		return airConditionerPower;
	}

	public void setAirConditionerPower(Integer airConditionerPower) {
		this.airConditionerPower = airConditionerPower;
	}

	public List<Quote> getQuotes() {
		return quotes;
	}

	public void setQuotes(List<Quote> quotes) {
		this.quotes = quotes;
	}

	public OrderStatusType getStatusType() {
		return statusType;
	}

	public void setStatusType(OrderStatusType statusType) {
		this.statusType = statusType;
	}
}
