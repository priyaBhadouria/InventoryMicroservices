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
@Table(name = "fsg_demand_drilldown_view", schema = "staging")
public class FSGDemandDrillDownEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "stage_seq_key")
	private Integer id;

	private String fsg;

	private String material;

	@Column(name = "material_description")
	private String materialDescription;

	@Column(name = "quantity")
	private Double demand;

	@Column(name = "weekly_bucket")
	private String weeklyBucket;

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
	 * @return the fsg
	 */
	public String getFsg() {
		return fsg;
	}

	/**
	 * @param fsg
	 *            the fsg to set
	 */
	public void setFsg(String fsg) {
		this.fsg = fsg;
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
	 * @return the weeklyBucket
	 */
	public String getWeeklyBucket() {
		return weeklyBucket;
	}

	/**
	 * @param weeklyBucket
	 *            the weeklyBucket to set
	 */
	public void setWeeklyBucket(String weeklyBucket) {
		this.weeklyBucket = weeklyBucket;
	}

}
