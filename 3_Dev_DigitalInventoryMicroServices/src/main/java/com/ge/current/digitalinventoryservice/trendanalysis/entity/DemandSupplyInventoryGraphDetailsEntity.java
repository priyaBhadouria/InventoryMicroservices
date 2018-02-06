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
@Table(name="demand_supply_inventory_graph_view",schema="audit")
public class DemandSupplyInventoryGraphDetailsEntity implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	private Integer id;
	
	@Column(name="stock_quantity")
	private Double stockQty;
	
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
	 * @return the stockQty
	 */
	public Double getStockQty() {
		return stockQty;
	}

	/**
	 * @param stockQty the stockQty to set
	 */
	public void setStockQty(Double stockQty) {
		this.stockQty = stockQty;
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
