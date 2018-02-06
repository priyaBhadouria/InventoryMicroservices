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
@Table(name="demand_supply_shortage_graph_view",schema="audit")
public class DemandSupplyShortageGraphDetailsEntity implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	private Integer id;
	
	@Column(name="shortage_quantity")
	private Double shortageQty;
	
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
	 * @return the shortageQty
	 */
	public Double getShortageQty() {
		return shortageQty;
	}

	/**
	 * @param shortageQty the shortageQty to set
	 */
	public void setShortageQty(Double shortageQty) {
		this.shortageQty = shortageQty;
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
