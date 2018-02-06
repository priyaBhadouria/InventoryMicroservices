package com.ge.current.digitalinventoryservice.divtrendanalysisservice.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "fsg_issued_valid_stage_drilldown_loc_view", schema = "validstage")
public class FSGTrendDrilldownLocSummaryEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	private String Id;

	@Column(name = "FSGISSUED")
	private Double fsgIssuedquantity;

	@Column(name = "FSGRECEIVED")
	private Double fsgReceivedquantity;

	@Column(name = "material")
	private String material;

	@Column(name = "material_description")
	private String materialDescription;

	@Column(name = "issued_from")
	private String fsg;
	
	@Column(name = "issued_date")
	private LocalDate date;

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
	 * @return fsgIssuedquantity
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
	 * @return fsgReceivedquantity
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
}
