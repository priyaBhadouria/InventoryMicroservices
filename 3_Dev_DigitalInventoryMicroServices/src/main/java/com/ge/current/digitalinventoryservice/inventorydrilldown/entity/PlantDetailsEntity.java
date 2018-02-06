/**
 * 
 */
package com.ge.current.digitalinventoryservice.inventorydrilldown.entity;

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
@Table(name = "plant_names_view", schema = "validstage")
public class PlantDetailsEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "plant_name")
	private String plantName;

	/**
	 * @return the plantName
	 */
	public String getPlantName() {
		return plantName;
	}

	/**
	 * @param plantName
	 *            the plantName to set
	 */
	public void setPlantName(String plantName) {
		this.plantName = plantName;
	}

}
