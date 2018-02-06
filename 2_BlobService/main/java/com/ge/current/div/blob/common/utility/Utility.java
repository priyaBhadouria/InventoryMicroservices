package com.ge.current.div.blob.common.utility;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Utility {
	// List of all date formats that we want to parse.
	// Add your own format here.
	private static List<SimpleDateFormat> dateFormats = new ArrayList<SimpleDateFormat>() {{
			add(new SimpleDateFormat("yyyy-MM-dd"));
			add(new SimpleDateFormat("MM-dd-yyyy"));
			add(new SimpleDateFormat("yyyy/MM/dd"));
			add(new SimpleDateFormat("MM/dd/yyyy"));
		}
	};

	/**
	 * Convert String with various formats into java.util.Date
	 * 
	 * @param input
	 *            Date as a string
	 * @return java.util.Date object if input string is parsed 
	 * 			successfully else returns null
	 */
	public static Date convertToDate(String input) {
		Date date = null;
		if(null == input) {
			return null;
		}
		for (SimpleDateFormat format : dateFormats) {
			try {
				format.setLenient(false);
				date = format.parse(input);
			} catch (ParseException e) {
				//Shhh.. try other formats
			}
			if (date != null) {
				break;
			}
		}

		return date;
	}
}
