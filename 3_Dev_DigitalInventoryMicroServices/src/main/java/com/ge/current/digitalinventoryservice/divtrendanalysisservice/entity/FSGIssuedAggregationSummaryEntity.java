package com.ge.current.digitalinventoryservice.divtrendanalysisservice.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "fsg_issued_trend_details_view", schema = "validstage")
public class FSGIssuedAggregationSummaryEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private String Id;

	@Column(name = "issued_date")
	private LocalDateTime issuedDate;

	@Column(name = "all_quantity")
	private Double allQuantity;

	@Column(name = "FSG_QUANTITY")
	private Double fsgQuantity;

	@Column(name = "FSG")
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
	 * @return issuedDate
	 */
	public LocalDateTime getIssuedDate() {
		return issuedDate;
	}

	/**
	 * 
	 * @param issuedDate
	 */
	public void setIssuedDate(LocalDateTime issuedDate) {
		this.issuedDate = issuedDate;
	}

	/**
	 * 
	 * @return allQuantity
	 */
	public Double getAllQuantity() {
		return allQuantity;
	}

	/**
	 * 
	 * @param allQuantity
	 */
	public void setAllQuantity(Double allQuantity) {
		this.allQuantity = allQuantity;
	}

	/**
	 * 
	 * @return fsgQuantity
	 */
	public Double getFsgQuantity() {
		return fsgQuantity;
	}

	/**
	 * 
	 * @param fsgQuantity
	 */
	public void setFsgQuantity(Double fsgQuantity) {
		this.fsgQuantity = fsgQuantity;
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