/**
 * 
 */
package com.ge.current.digitalinventoryservice.totalTransaction.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author vs825995
 *
 */
@Entity
@Table(name = "customer_summary_view", schema = "analytics")
public class ConsumptionSummaryEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	private String state;

	@Column(name = "current_qty")
	private double currentQty;

	@Column(name = "return_qty")
	private double returnQty;

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public double getReturnQty() {
		return returnQty;
	}

	public void setReturnQty(double returnQty) {
		this.returnQty = returnQty;
	}

	public double getCurrentQty() {
		return currentQty;
	}

	public void setCurrentQty(double currentQty) {
		this.currentQty = currentQty;
	}

}
