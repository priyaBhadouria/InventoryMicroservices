/**
 * 
 */
package com.ge.current.digitalinventoryservice.totalinventory.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author ncheredd
 *
 */
@Entity
@Table(name="total_inventory_dollar_value_summary_view", schema="analytics")
public class TotalInventoryDollarValueSummaryEntity implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="total_quantity")
	private Double totalQuantity;
	
	@Column(name="transit_to_fsg")
	private Double transitToFSGQuantity;
	
	@Column(name="at_fsg")
	private Double atFSGQuantity;
	
	@Column(name="transit_to_customer")
	private Double transitToCustomerQuantity;
	
	@Column(name="at_customer")
	private Double atCustomerQuantity;
	
	@Column(name="total_consuption")
	private Double customerConsumedQuantity;

	@Column(name="customer_demand")
	private Double customerDemand;
	
	/**
	 * @return the totalQuantity
	 */
	public Double getTotalQuantity() {
		return totalQuantity;
	}

	/**
	 * @param totalQuantity the totalQuantity to set
	 */
	public void setTotalQuantity(Double totalQuantity) {
		this.totalQuantity = totalQuantity;
	}

	/**
	 * @return the transitToFSGQuantity
	 */
	public Double getTransitToFSGQuantity() {
		return transitToFSGQuantity;
	}

	/**
	 * @param transitToFSGQuantity the transitToFSGQuantity to set
	 */
	public void setTransitToFSGQuantity(Double transitToFSGQuantity) {
		this.transitToFSGQuantity = transitToFSGQuantity;
	}

	/**
	 * @return the atFSGQuantity
	 */
	public Double getAtFSGQuantity() {
		return atFSGQuantity;
	}

	/**
	 * @param atFSGQuantity the atFSGQuantity to set
	 */
	public void setAtFSGQuantity(Double atFSGQuantity) {
		this.atFSGQuantity = atFSGQuantity;
	}

	/**
	 * @return the transitToCustomerQuantity
	 */
	public Double getTransitToCustomerQuantity() {
		return transitToCustomerQuantity;
	}

	/**
	 * @param transitToCustomerQuantity the transitToCustomerQuantity to set
	 */
	public void setTransitToCustomerQuantity(Double transitToCustomerQuantity) {
		this.transitToCustomerQuantity = transitToCustomerQuantity;
	}

	/**
	 * @return the atCustomerQuantity
	 */
	public Double getAtCustomerQuantity() {
		return atCustomerQuantity;
	}

	/**
	 * @param atCustomerQuantity the atCustomerQuantity to set
	 */
	public void setAtCustomerQuantity(Double atCustomerQuantity) {
		this.atCustomerQuantity = atCustomerQuantity;
	}

	/**
	 * @return the customerConsumedQuantity
	 */
	public Double getCustomerConsumedQuantity() {
		return customerConsumedQuantity;
	}

	/**
	 * @param customerConsumedQuantity the customerConsumedQuantity to set
	 */
	public void setCustomerConsumedQuantity(Double customerConsumedQuantity) {
		this.customerConsumedQuantity = customerConsumedQuantity;
	}

	/**
	 * @return the customerDemand
	 */
	public Double getCustomerDemand() {
		return customerDemand;
	}

	/**
	 * @param customerDemand the customerDemand to set
	 */
	public void setCustomerDemand(Double customerDemand) {
		this.customerDemand = customerDemand;
	}

}
