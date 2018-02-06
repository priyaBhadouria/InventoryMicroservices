/**
 * 
 */
package com.ge.current.digitalinventoryservice.totalTransaction.entity;

import javax.persistence.Column;
import javax.persistence.Id;

/**
 * @author vs825995
 *
 */
public class TreeDataAtFSGLevelEntity {

	@Id
	@Column(name = "in_transit_to_fsg")
	private int intransitToFSGQuantity;

	@Column(name = "stock_at_fsg")
	private int stockAtFSGQuantity;

	@Column(name = "in_transit_to_customer")
	private int intransitToCustomerQuantity;

	private String location;

	public int getIntransitToFSGQuantity() {
		return intransitToFSGQuantity;
	}

	public void setIntransitToFSGQuantity(int intransitToFSGQuantity) {
		this.intransitToFSGQuantity = intransitToFSGQuantity;
	}

	public int getStockAtFSGQuantity() {
		return stockAtFSGQuantity;
	}

	public void setStockAtFSGQuantity(int stockAtFSGQuantity) {
		this.stockAtFSGQuantity = stockAtFSGQuantity;
	}

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
