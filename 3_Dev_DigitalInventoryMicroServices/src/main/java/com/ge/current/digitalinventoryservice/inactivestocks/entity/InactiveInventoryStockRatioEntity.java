/**
 * 
 */
package com.ge.current.digitalinventoryservice.inactivestocks.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author ncheredd
 *
 */
@Entity
@Table(name = "inactive_stock_ratio_view", schema = "validstage")
public class InactiveInventoryStockRatioEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	private Double days_11_to_20;

	private Double days_21_to_30;

	private Double days_31_to_60;

	private Double days_60_plus;

	/**
	 * @return the days_11_to_20
	 */
	public Double getDays_11_to_20() {
		return days_11_to_20;
	}

	/**
	 * @param days_11_to_20
	 *            the days_11_to_20 to set
	 */
	public void setDays_11_to_20(Double days_11_to_20) {
		this.days_11_to_20 = days_11_to_20;
	}

	/**
	 * @return the days_21_to_30
	 */
	public Double getDays_21_to_30() {
		return days_21_to_30;
	}

	/**
	 * @param days_21_to_30
	 *            the days_21_to_30 to set
	 */
	public void setDays_21_to_30(Double days_21_to_30) {
		this.days_21_to_30 = days_21_to_30;
	}

	/**
	 * @return the days_31_to_60
	 */
	public Double getDays_31_to_60() {
		return days_31_to_60;
	}

	/**
	 * @param days_31_to_60
	 *            the days_31_to_60 to set
	 */
	public void setDays_31_to_60(Double days_31_to_60) {
		this.days_31_to_60 = days_31_to_60;
	}

	/**
	 * @return the days_60_plus
	 */
	public Double getDays_60_plus() {
		return days_60_plus;
	}

	/**
	 * @param days_60_plus
	 *            the days_60_plus to set
	 */
	public void setDays_60_plus(Double days_60_plus) {
		this.days_60_plus = days_60_plus;
	}

}
