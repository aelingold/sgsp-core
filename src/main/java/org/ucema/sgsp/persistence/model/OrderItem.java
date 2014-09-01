package org.ucema.sgsp.persistence.model;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "order_items")
public class OrderItem {

	@Id
	@GeneratedValue
	private Long id;
	@ManyToOne
	@JoinColumn(name = "order_id", foreignKey = @ForeignKey(name = "fk_order_items_order"))
	private Order order;
	@OneToOne
	@JoinColumn(name = "work_area_item_id")
	private WorkAreaItem workAreaItem;
	private String value;
	
	public OrderItem(Long id) {
		super();
		this.id = id;
	}

	public OrderItem() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public WorkAreaItem getWorkAreaItem() {
		return workAreaItem;
	}

	public void setWorkAreaItem(WorkAreaItem workAreaItem) {
		this.workAreaItem = workAreaItem;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
