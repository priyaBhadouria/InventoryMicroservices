/**
 * 
 */
package com.ge.current.digitalinventoryservice.dto;

import java.io.Serializable;

public class FSGMaterialDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7164711598611289657L;
	/**
	 * 
	 */

	private String fsgLocation;

	private String materialId;

	private String fiscalWeek;

	private String year;

	public String getFsgLocation() {
		return fsgLocation;
	}

	public void setFsgLocation(String fsgLocation) {
		this.fsgLocation = fsgLocation;
	}

	public String getMaterialId() {
		return materialId;
	}

	public void setMaterialId(String materialId) {
		this.materialId = materialId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getFiscalWeek() {
		return fiscalWeek;
	}

	public void setFiscalWeek(String fiscalWeek) {
		this.fiscalWeek = fiscalWeek;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}
}
