package com.ge.current.digitalinventoryservice.divtrendanalysisservice.dto;

import java.io.Serializable;

public class FSGTrendDrilldownSummaryDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String material;

	private String materialDescription;

	private Double fsgIssuedquantity = 0d;

	private Double fsgReceivedquantity = 0d;

	private String location;

	private String issuedLocation;

	private String date;

	/**
	 * 
	 * @return the material
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
	 * @return materialDescription
	 */
	public String getMaterialDescription() {
		return materialDescription;
	}

	/**
	 * 
	 * @param materialDescription
	 */
	public void setMaterialDescription(String materialDescription) {
		this.materialDescription = materialDescription;
	}

	/**
	 * 
	 * @return the fsgIssuedquantity
	 */
	public Double getFsgIssuedquantity() {
		return fsgIssuedquantity;
	}

	/**
	 * 
	 * @param fsgIssuedquantity
	 */
	public void setFsgIssuedquantity(Double fsgIssuedquantity) {
		this.fsgIssuedquantity = fsgIssuedquantity;
	}

	/**
	 * 
	 * @return
	 */
	public Double getFsgReceivedquantity() {
		return fsgReceivedquantity;
	}

	/**
	 * 
	 * @param fsgReceivedquantity
	 */
	public void setFsgReceivedquantity(Double fsgReceivedquantity) {
		this.fsgReceivedquantity = fsgReceivedquantity;
	}

	/**
	 * 
	 * @return date
	 */
	public String getDate() {
		return date;
	}

	/**
	 * 
	 * @param date
	 */
	public void setDate(String date) {
		this.date = date;
	}

	/**
	 * 
	 * @return location
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * 
	 * @param location
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	/**
	 * 
	 * @return issuedLocation
	 */
	public String getIssuedLocation() {
		return issuedLocation;
	}

	/**
	 * 
	 * @param issuedLocation
	 */
	public void setIssuedLocation(String issuedLocation) {
		this.issuedLocation = issuedLocation;
	}

}
