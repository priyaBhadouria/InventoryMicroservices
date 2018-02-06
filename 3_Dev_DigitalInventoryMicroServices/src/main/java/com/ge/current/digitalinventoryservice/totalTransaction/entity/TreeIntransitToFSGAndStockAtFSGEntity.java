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
@Table(name = "intransit_to_fsg_and_stock_at_fsg_view", schema = "analytics")
public class TreeIntransitToFSGAndStockAtFSGEntity {

	@Id
	@Column(name = "intransit_to_fsg")
	private int intransitToFSGQuantity;

	@Column(name = "stock_service_provider")
	private Integer stockAtFSGQuantity;

	private String location;

	public int getIntransitToFSGQuantity() {
		return intransitToFSGQuantity;
	}

	public void setIntransitToFSGQuantity(int intransitToFSGQuantity) {
		this.intransitToFSGQuantity = intransitToFSGQuantity;
	}

	public Integer getStockAtFSGQuantity() {
		return stockAtFSGQuantity;
	}

	public void setStockAtFSGQuantity(Integer stockAtFSGQuantity) {
		this.stockAtFSGQuantity = stockAtFSGQuantity;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

}
