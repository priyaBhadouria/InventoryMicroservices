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
@Table(name = "intransit_to_customer_view", schema = "analytics")
public class TreeIntransitToCustomerEntity {

	@Id
	@Column(name = "intransit_to_customer")
	private int intransitToCustomerQuantity;

	private String location;

	public int getIntransitToCustomerQuantity() {
		return intransitToCustomerQuantity;
	}

	public void setIntransitToCustomerQuantity(int intransitToCustomerQuantity) {
		this.intransitToCustomerQuantity = intransitToCustomerQuantity;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
}
