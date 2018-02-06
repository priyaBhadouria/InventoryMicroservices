package com.ge.current.digitalinventoryservice.divtrendanalysisservice.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "fsg_receipt_trend_details_view", schema = "validstage")
public class FSGReceivedAggregationSummaryEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private String Id;

	@Column(name = "receipt_date")
	private LocalDateTime receivedDate;
	
	@Column(name = "all_received_quantity")
	private Double allReceivedQuantity;
	
	@Column(name = "fsg_received_quantity")
	private Double fsgReceivedQuantity;
	
	@Column(name = "fsg")
	private String fsg;
	
	

	/**
	 * 
	 * @return Id
	 */
	public String getId() {
		return Id;
	}

	/**
	 * 
	 * @param id
	 */
	public void setId(String id) {
		Id = id;
	}

	/**
	 * 
	 * @return receivedDate
	 */
	public LocalDateTime getReceivedDate() {
		return receivedDate;
	}

	/**
	 * 
	 * @param receivedDate
	 */
	public void setReceivedDate(LocalDateTime receivedDate) {
		this.receivedDate = receivedDate;
	}

	/**
	 * 
	 * @return allReceivedQuantity
	 */
	public Double getAllReceivedQuantity() {
		return allReceivedQuantity;
	}

	/**
	 * 
	 * @param allReceivedQuantity
	 */
	public void setAllReceivedQuantity(Double allReceivedQuantity) {
		this.allReceivedQuantity = allReceivedQuantity;
	}

	/**
	 * 
	 * @return fsgReceivedQuantity
	 */
	public Double getFsgReceivedQuantity() {
		return fsgReceivedQuantity;
	}

	/**
	 * 
	 * @param fsgReceivedQuantity
	 */
	public void setFsgReceivedQuantity(Double fsgReceivedQuantity) {
		this.fsgReceivedQuantity = fsgReceivedQuantity;
	}

	/**
	 * 
	 * @return fsg
	 */
	public String getFsg() {
		return fsg;
	}

	/**
	 * 
	 * @param fsg
	 */
	public void setFsg(String fsg) {
		this.fsg = fsg;
	}

}
