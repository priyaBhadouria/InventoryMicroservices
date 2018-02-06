/**
 * 
 */
package com.ge.current.digitalinventoryservice.heatmap.entity;

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
@Table(name = "heatmap_fsg_to_fsg_connection_details_view", schema = "analytics")
public class FsgToFsgDetailsEntity implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	private Long id;
	
	@Column(name = "from_location")
	private String fromFsgName;
	
	@Column(name = "to_location")
	private String toFsgName;
	
	private Double quantity;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the fromFsgName
	 */
	public String getFromFsgName() {
		return fromFsgName;
	}

	/**
	 * @param fromFsgName the fromFsgName to set
	 */
	public void setFromFsgName(String fromFsgName) {
		this.fromFsgName = fromFsgName;
	}

	/**
	 * @return the toFsgName
	 */
	public String getToFsgName() {
		return toFsgName;
	}

	/**
	 * @param toFsgName the toFsgName to set
	 */
	public void setToFsgName(String toFsgName) {
		this.toFsgName = toFsgName;
	}

	/**
	 * @return the quantity
	 */
	public Double getQuantity() {
		return quantity;
	}

	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}
}
