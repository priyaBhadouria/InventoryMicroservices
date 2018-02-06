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
@Table(name="demand_supply_in_transit_graph_view",schema="audit")
public class DemandSupplyInTransitGraphDetailsEntity implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	private Integer id;
	
	@Column(name="intransit_quantity")
	private Double inTransitQty;
	
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
	 * @return the inTransitQty
	 */
	public Double getInTransitQty() {
		return inTransitQty;
	}

	/**
	 * @param inTransitQty the inTransitQty to set
	 */
	public void setInTransitQty(Double inTransitQty) {
		this.inTransitQty = inTransitQty;
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
