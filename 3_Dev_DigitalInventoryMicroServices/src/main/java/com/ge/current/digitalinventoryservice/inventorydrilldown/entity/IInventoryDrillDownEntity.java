/**
 * 
 */
package com.ge.current.digitalinventoryservice.inventorydrilldown.entity;

/**
 * @author ncheredd
 *
 */
public interface IInventoryDrillDownEntity {

	/**
	 * @return the location
	 */
	public String getLocation();

	/**
	 * @param location
	 *            the location to set
	 */
	public void setLocation(String location);

	/**
	 * @return the locationType
	 */
	public String getLocationType();

	/**
	 * @param locationType
	 *            the locationType to set
	 */
	public void setLocationType(String locationType);

	/**
	 * @return the source
	 */
	public String getSource();

	/**
	 * @param source
	 *            the source to set
	 */
	public void setSource(String source);

	/**
	 * @return the sourceType
	 */
	public String getSourceType();

	/**
	 * @param sourceType
	 *            the sourceType to set
	 */
	public void setSourceType(String sourceType);

}
