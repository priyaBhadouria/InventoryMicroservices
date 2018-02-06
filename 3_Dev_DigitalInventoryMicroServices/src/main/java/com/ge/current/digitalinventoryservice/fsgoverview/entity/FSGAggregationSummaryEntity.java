/**
 * 
 */
package com.ge.current.digitalinventoryservice.fsgoverview.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author ncheredd
 *
 */
@Entity
@Table(name = "fsg_aggregation_summary_view", schema = "analytics")
public class FSGAggregationSummaryEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	private String fsg;

	private Double quantity;

	private String category;

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
	 * @return the quantity
	 */
	public Double getQuantity() {
		return quantity;
	}

	/**
	 * @param quantity
	 *            the quantity to set
	 */
	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}

	/**
	 * @return the category
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * @param category
	 *            the category to set
	 */
	public void setCategory(String category) {
		this.category = category;
	}
}
