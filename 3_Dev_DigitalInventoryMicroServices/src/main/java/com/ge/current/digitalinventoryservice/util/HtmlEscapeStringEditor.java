package com.ge.current.digitalinventoryservice.util;

import java.beans.PropertyEditorSupport;

import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;

@Service
public class HtmlEscapeStringEditor extends PropertyEditorSupport{

    @Override
	public void setAsText(String text) throws IllegalArgumentException {
	String out = "";
	if(null != text)
	out = HtmlUtils.htmlEscape(text.trim());
	
	setValue(out);
}

	@Override
	public String getAsText() {
	String out = (String) getValue();
	if(out == null)
	out = "";
	return out;
	}

}

