/**
 * 
 */
package com.ge.current.digitalinventoryservice.fsgoverview.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author ncheredd
 *
 */
@Entity
@Table(name = "fsg_material_consumption_rate_veiw_detail", schema = "analytics")
public class FSGInventoryTransitDemandShortageDrillDownEntity implements
		Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3283410866348368234L;

	@Id
	private Integer id;

	@Column(name = "fsg_location")
	private String fsgLocation;

	@Column(name = "materialid")
	private String material;

	@Column(name = "materialdesc")
	private String materialDescription;

	private Double inventory;

	private Double transit;

	private Double demand;

	private Double shortage;

	@Column(name = "fiscal_week")
	private Integer weekShort;

	private Integer year;

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the fsgLocation
	 */
	public String getFsgLocation() {
		return fsgLocation;
	}

	/**
	 * @param fsgLocation
	 *            the fsgLocation to set
	 */
	public void setFsgLocation(String fsgLocation) {
		this.fsgLocation = fsgLocation;
	}

	/**
	 * @return the material
	 */
	public String getMaterial() {
		return material;
	}

	/**
	 * @param material
	 *            the material to set
	 */
	public void setMaterial(String material) {
		this.material = material;
	}

	/**
	 * @return the materialDescription
	 */
	public String getMaterialDescription() {
		return materialDescription;
	}

	/**
	 * @param materialDescription
	 *            the materialDescription to set
	 */
	public void setMaterialDescription(String materialDescription) {
		this.materialDescription = materialDescription;
	}

	/**
	 * @return the inventory
	 */
	public Double getInventory() {
		return inventory;
	}

	/**
	 * @param inventory
	 *            the inventory to set
	 */
	public void setInventory(Double inventory) {
		this.inventory = inventory;
	}

	/**
	 * @return the transit
	 */
	public Double getTransit() {
		return transit;
	}

	/**
	 * @param transit
	 *            the transit to set
	 */
	public void setTransit(Double transit) {
		this.transit = transit;
	}

	/**
	 * @return the demand
	 */
	public Double getDemand() {
		return demand;
	}

	/**
	 * @param demand
	 *            the demand to set
	 */
	public void setDemand(Double demand) {
		this.demand = demand;
	}

	/**
	 * @return the shortage
	 */
	public Double getShortage() {
		return shortage;
	}

	/**
	 * @param shortage
	 *            the shortage to set
	 */
	public void setShortage(Double shortage) {
		this.shortage = shortage;
	}

	/**
	 * @return the weekShort
	 */
	public Integer getWeekShort() {
		return weekShort;
	}

	/**
	 * @param weekShort
	 *            the weekShort to set
	 */
	public void setWeekShort(Integer weekShort) {
		this.weekShort = weekShort;
	}

	/**
	 * @return the year
	 */
	public Integer getYear() {
		return year;
	}

	/**
	 * @param year
	 *            the year to set
	 */
	public void setYear(Integer year) {
		this.year = year;
	}

}
