package com.ge.current.digitalinventoryservice.divtrendanalysisservice.dto;

import java.io.Serializable;

public class CustomerReturnDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Double quantity = 0d;

	private long returnDate;

	/**
	 * 
	 * @return the quantity
	 */
	public Double getQuantity() {
		return quantity;
	}

	/**
	 * 
	 * @param quantity
	 */
	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}

	/**
	 * 
	 * @return the returnDate
	 */
	public long getReturnDate() {
		return returnDate;
	}

	/**
	 * 
	 * @param returnDate
	 */
	public void setReturnDate(long returnDate) {
		this.returnDate = returnDate;
	}

	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof CustomerConsumptionDTO)) {
			return false;
		}
		return (this.returnDate == ((CustomerReturnDTO) obj).returnDate);
	}

	public int hashCode() {
		return (int) this.returnDate;
	}

}
