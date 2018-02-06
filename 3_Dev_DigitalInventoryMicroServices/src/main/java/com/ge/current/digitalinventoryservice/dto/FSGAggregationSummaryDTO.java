/**
 * 
 */
package com.ge.current.digitalinventoryservice.dto;

import java.io.Serializable;

/**
 * @author ncheredd
 *
 */
public class FSGAggregationSummaryDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Double inventory = 0d;

	private Double inTransit = 0d;

	private Double demand = 0d;

	private Double shortage = 0d;

	/**
	 * @return the inventory
	 */
	public Double getInventory() {
		return inventory;
	}

	/**
	 * @param inventory
	 *            the inventory to set
	 */
	public void setInventory(Double inventory) {
		this.inventory = inventory;
	}

	/**
	 * @return the inTransit
	 */
	public Double getInTransit() {
		return inTransit;
	}

	/**
	 * @param inTransit
	 *            the inTransit to set
	 */
	public void setInTransit(Double inTransit) {
		this.inTransit = inTransit;
	}

	/**
	 * @return the demand
	 */
	public Double getDemand() {
		return demand;
	}

	/**
	 * @param demand
	 *            the demand to set
	 */
	public void setDemand(Double demand) {
		this.demand = demand;
	}

	/**
	 * @return the shortage
	 */
	public Double getShortage() {
		return shortage;
	}

	/**
	 * @param shortage
	 *            the shortage to set
	 */
	public void setShortage(Double shortage) {
		this.shortage = shortage;
	}

}
