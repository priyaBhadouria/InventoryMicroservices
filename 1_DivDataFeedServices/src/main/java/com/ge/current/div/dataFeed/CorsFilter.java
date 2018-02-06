package com.ge.current.div.dataFeed;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
/**
 * Filter to set headers for Angular requests.
 * @author 212421693
 *
 */
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
class CorsFilter implements Filter {

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		HttpServletResponse response = (HttpServletResponse) res;
		response.setHeader("Access-Control-Allow-Origin", "*"); //$NON-NLS-1$ //$NON-NLS-2$
		response.setHeader("Access-Control-All-Headers", "Origin, X-Requested-With, Content-Type, Accept,digitalInventoryAuthentication");
		response.setHeader("Access-Control-Allow-Credentials", "true");
		response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE ,PUT");  //$NON-NLS-1$//$NON-NLS-2$
		response.setHeader("Access-Control-Allow-Headers", "Access-Control-Allow-Headers,Origin,Accept,X-Requested-With,Content-Type,Access-Control-Request-Method,Access-Control-Request-Headers,Authorization,digitalInventoryAuthentication");  //$NON-NLS-1$//$NON-NLS-2$
		chain.doFilter(req, res);
	}
	@Override
	public void destroy() {
		
	}	
	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
	}

	
}