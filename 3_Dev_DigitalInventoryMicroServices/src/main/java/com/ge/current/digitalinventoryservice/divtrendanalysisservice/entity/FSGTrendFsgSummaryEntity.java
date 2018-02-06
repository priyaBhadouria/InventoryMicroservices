package com.ge.current.digitalinventoryservice.divtrendanalysisservice.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "fsg_trend_valid_stage_fsg_view", schema = "validstage")
public class FSGTrendFsgSummaryEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private String Id;

	@Column(name = "fsg_issued")
	private Double fsgIssued;

	@Column(name = "fsg_received")
	private Double fsgReceived;

	@Column(name = "material")
	private String material;

	@Column(name = "issued_date")
	private LocalDate date;

	@Column(name = "fsg_received_loc")
	private String receivedlocation;

	@Column(name = "fsg_issued_loc")
	private String issuedlocation;

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
	 * @return fsgIssued
	 */
	public Double getFsgIssued() {
		return fsgIssued;
	}

	/**
	 * 
	 * @param fsgIssued
	 */
	public void setFsgIssued(Double fsgIssued) {
		this.fsgIssued = fsgIssued;
	}

	/**
	 * 
	 * @return fsgReceived
	 */
	public Double getFsgReceived() {
		return fsgReceived;
	}

	/**
	 * 
	 * @param fsgReceived
	 */
	public void setFsgReceived(Double fsgReceived) {
		this.fsgReceived = fsgReceived;
	}

	/**
	 * 
	 * @return material
	 */
	public String getMaterial() {
		return material;
	}

	/**
	 * 
	 * @param material
	 */
	public void setMaterial(String material) {
		this.material = material;
	}

	/**
	 * 
	 * @return date
	 */
	public LocalDate getDate() {
		return date;
	}

	/**
	 * 
	 * @param date
	 */
	public void setDate(LocalDate date) {
		this.date = date;
	}

	/**
	 * 
	 * @return receivedlocation
	 */
	public String getReceivedlocation() {
		return receivedlocation;
	}

	/**
	 * 
	 * @param location
	 */
	public void setReceivedlocation(String receivedlocation) {
		this.receivedlocation = receivedlocation;
	}

	/**
	 * 
	 * @return issuedlocation
	 */
	public String getIssuedlocation() {
		return issuedlocation;
	}

	/**
	 * 
	 * @param issuedlocation
	 */
	public void setIssuedlocation(String issuedlocation) {
		this.issuedlocation = issuedlocation;
	}

}
