/**
 * 
 */
package com.ge.current.digitalinventoryservice.fsgoverview.entity;

import java.io.Serializable;

import javax.persistence.Id;

/**
 * @author ncheredd
 *
 */
public class FSGShortageDrillDownEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	private String material;

	private String materialDescription;

	private Double shortage;

	private String weekShort;

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
	public String getWeekShort() {
		return weekShort;
	}

	/**
	 * @param weekShort
	 *            the weekShort to set
	 */
	public void setWeekShort(String weekShort) {
		this.weekShort = weekShort;
	}

}
