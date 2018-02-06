/**
 * 
 */
package com.ge.current.digitalinventoryservice.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.ge.current.digitalinventoryservice.dto.TIShipmentTransitConsumedDetailsDTO;


/**
 * @author ncheredd
 *
 */
public class UtilManager 
{
	
	// Used in totalInventoryTransaction
	//start

	public static String isJsonValidString(String value)
	{
		if(value != null)
			return value;
		return DigitalInventoryConstants.EMPTY_STRING;
	}
	public static String isJsonValidLong(Long value)
	{
		if(value != null)
			return value.toString();
		return DigitalInventoryConstants.EMPTY_STRING;
	}
	
	public static Integer setDefaultValueToJsonInteger(Integer value)
	{
		if(value != null)
			return value;
		return DigitalInventoryConstants.QUANTITY_DEFAULT_INTEGER_VALUE;
	}
	
	public static Double setDefaultValueToJsonDouble(Double value)
	{
		if(value != null)
			return value;
		return DigitalInventoryConstants.PRODUCT_COST_DEFAULT_VALUE;
	}
	
	//end
	
	
	
	public static Integer convertDoubleToInteger(Double doubleValue) 
	{
		if(isValidInteget(doubleValue))
			return doubleValue.intValue();
		return DigitalInventoryConstants.QUANTITY_DEFAULT_INTEGER_VALUE;
	}
	
	public static Double formatProductCost(Double productCost)
	{
		if(isValidInteget(productCost))
			return productCost;
		return DigitalInventoryConstants.PRODUCT_COST_DEFAULT_VALUE;
	}
	
	public static String setDefaultValueForFWandYear(Integer value)
	{
		if(value != null)
			return value.toString();
		return DigitalInventoryConstants.QUANTITY_DEFAULT_STRING_VALUE;
	}
	
	public static Date convertStringToDate(String date)
	{
		Date formatedDate = null;
		try {
			formatedDate = new SimpleDateFormat(DigitalInventoryConstants.DATE_FORMAT).parse(date);
		} catch (ParseException e) {
			
		}
		return formatedDate;
	}
	
	public static long convertStringDateToMilliSeconds(String date)
	{
		long milliSeconds = 0;
		milliSeconds = convertStringToDate(date).getTime();		
		return milliSeconds;
	}
	
	public static void setFromLocation(TIShipmentTransitConsumedDetailsDTO transitDetailsDTO,	String fromLocation) 
	{
		if(fromLocation != null && fromLocation.charAt(0) == DigitalInventoryConstants.DATA_SOURCE_S)
			transitDetailsDTO.setReceivedFrom(DigitalInventoryConstants.FROM_LOCATION_GE_LED);
		else if(fromLocation != null && fromLocation.charAt(0) == DigitalInventoryConstants.DATA_SOURCE_C)
			transitDetailsDTO.setReceivedFrom(DigitalInventoryConstants.FROM_LOCATION_GE_CORE);
		else
			transitDetailsDTO.setReceivedFrom(fromLocation);
	}
	
	
	public static boolean isValidString(String value)
	{
		if(value != null && value != "")
			return true;
		return false;
	}
		
	public static boolean isValidInteget(Double value)
	{
		if(value != null && value != 0)
			return true;
		return false;
	}
	
	public static String convertNumberToMillions(Double dollarValue)
	{
		
		if(isValidInteget(dollarValue))
		{
			return String.format("%.2f", dollarValue / Math.pow(1000, 2),"kMGTPE".charAt(2-1));
		}else
			return "0";
	}
	
	public static String convertNumberToThousands(Double dollarValue)
	{
		
		if(isValidInteget(dollarValue))
		{
			return String.format("%.6f", dollarValue / Math.pow(1000, 1),"kMGTPE".charAt(2-1));
		}else
			return "0";
	}
	
	
	
}
