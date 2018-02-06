/**
 * 
 */
package com.ge.current.digitalinventoryservice.totalTransaction.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author vs825995
 *
 */
@Entity
@Table(name = "tree_data_aggrigate_for_customer_level_view", schema = "analytics")
public class TreeAggrigateDataForCustomerLevelEntity {

	@Id
	private String customer;

	private Double quantity;

	@Column(name = "intransit_to_customer")
	private Double intransitToCustomer;

	public Double getIntransitToCustomer() {
		return intransitToCustomer;
	}

	public void setIntransitToCustomer(Double intransitToCustomer) {
		this.intransitToCustomer = intransitToCustomer;
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public Double getQuantity() {
		return quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}

}
