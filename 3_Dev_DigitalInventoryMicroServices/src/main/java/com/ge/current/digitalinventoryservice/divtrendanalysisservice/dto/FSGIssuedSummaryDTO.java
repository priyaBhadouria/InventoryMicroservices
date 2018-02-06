package com.ge.current.digitalinventoryservice.divtrendanalysisservice.dto;

import java.io.Serializable;

public class FSGIssuedSummaryDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Double quantity = 0d;

	private long issuedDate;

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
	 * @return issuedDate
	 */
	public long getIssuedDate() {
		return issuedDate;
	}

	/**
	 * 
	 * @param issuedDate
	 */
	public void setIssuedDate(long issuedDate) {
		this.issuedDate = issuedDate;
	}

	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof FSGIssuedSummaryDTO)) {
			return false;
		}
		return (this.issuedDate == ((FSGIssuedSummaryDTO) obj).issuedDate);
	}

	public int hashCode() {
		return (int) this.issuedDate;
	}

}
