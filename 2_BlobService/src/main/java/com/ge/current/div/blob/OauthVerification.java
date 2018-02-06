package com.ge.current.div.blob;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
/**
 * 
 * @author predix -
 */
@Service
@ConfigurationProperties(prefix="endpoint")
public class OauthVerification {
	
	Messages messages = new Messages();

	private String uaaUrl;
	RestTemplate rsttmplt= new RestTemplate();

	public String getUaaUrl() {
		return uaaUrl;
	}

	public void setUaaUrl(String uaaUrl) {
		this.uaaUrl = uaaUrl;
	}

	@Bean
	private RestTemplate restTemplate(){
		return new RestTemplate();
	}

	/**
	 * @param toekn
	 *            token
	 * @param args
	 *            -
	 * @return boolean
	 */
	


	public boolean oAuthValidator(HttpServletRequest request, HttpServletResponse response) {
		
		

		String string = request.getHeader(Messages.getString("Header"));
		HttpHeaders httpHeader = new HttpHeaders();
		httpHeader.setContentType(org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED);
		MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
		
		httpHeader.add(Messages.getString("Authorization"), Messages.getString("SimpleServiceImpl.test_message")); //$NON-NLS-2$//$NON-NLS-1$


		map.add("token", string); 

		HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<MultiValueMap<String, String>>(map, httpHeader);

		
		ResponseEntity<String> response1 = this.rsttmplt.postForEntity(Messages.getString("UAAUrl"), httpEntity, String.class); //$NON-NLS-1$


		String str = response1.getBody();
		return str.contains("client_id"); //$NON-NLS-1$
	}
	
}

