package com.ge.current.digitalinventoryservice.divtrendanalysisservice.dto;

import java.io.Serializable;

public class FSGReceiptSummaryDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Double actualQuantity = 0d;

	private long receiptDate;

	/**
	 * 
	 * @return the actualquantity
	 */
	public Double getActualQuantity() {
		return actualQuantity;
	}

	/**
	 * 
	 * @param actualquantity
	 */
	public void setActualQuantity(Double actualQuantity) {
		this.actualQuantity = actualQuantity;
	}

	/**
	 * 
	 * @return the receiptDate
	 */
	public long getReceiptDate() {
		return receiptDate;
	}

	/**
	 * 
	 * @param receiptDate
	 */
	public void setReceiptDate(long receiptDate) {
		this.receiptDate = receiptDate;
	}

	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof FSGReceiptSummaryDTO)) {
			return false;
		}
		return (this.receiptDate == ((FSGReceiptSummaryDTO) obj).receiptDate);
	}

	public int hashCode() {
		return (int) this.receiptDate;
	}

}
