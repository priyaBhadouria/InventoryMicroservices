/**
 * 
 */
package com.ge.current.digitalinventoryservice.trendanalysis.entity;

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
@Table(name="demand_supply_demand_graph_view",schema="audit")
public class DemandSupplyDemandGraphDetailsEntity implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	private Integer id;
	
	@Column(name="demand_quantity")
	private Double demandQty;
	
	@Column(name="backup_date")
	private String date;

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	
	/**
	 * @return the demandQty
	 */
	public Double getDemandQty() {
		return demandQty;
	}

	/**
	 * @param demandQty the demandQty to set
	 */
	public void setDemandQty(Double demandQty) {
		this.demandQty = demandQty;
	}

	/**
	 * @return the date
	 */
	public String getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(String date) {
		this.date = date;
	}
	
}
