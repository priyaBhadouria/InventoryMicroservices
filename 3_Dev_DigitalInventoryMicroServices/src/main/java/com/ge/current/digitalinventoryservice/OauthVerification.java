package com.ge.current.digitalinventoryservice;

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
	 * @param token
	 *            
	 * @param args
	 *            -
	 * @return boolean
	 */
	/*public boolean oAuthValidator() {
		HttpHeaders httpHeader = new HttpHeaders();
		
		httpHeader.setContentType(org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED);
		MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
		httpHeader.add("Authorization", "Basic Z2VDdXJyZW50RElWRW50ZXJwcmlzZTpjbGllbnRwQHNzd0Bk"); //client Id and client secret encoded with base64(eg currentDigitalTwin:clientp@ssw@d) 
		map.add("token", "eyJhbGciOiJSUzI1NiJ9.eyJqdGkiOiJlZDgzN2FhOC1hZmFiLTQ1MDctYTYyNi03ZDM0OWZjMmFjOTIiLCJzdWIiOiJjdXJyZW50RGlnaXRhbFR3aW4iLCJzY29wZSI6WyJ1YWEucmVzb3VyY2UiLCJwcmVkaXgtYXNzZXQuem9uZXMuNzg2MGFmNDItOTIxMi00OTcxLTkwNDMtNDhlM2IzYTJiNGY1LnVzZXIiXSwiY2xpZW50X2lkIjoiY3VycmVudERpZ2l0YWxUd2luIiwiY2lkIjoiY3VycmVudERpZ2l0YWxUd2luIiwiYXpwIjoiY3VycmVudERpZ2l0YWxUd2luIiwiZ3JhbnRfdHlwZSI6ImNsaWVudF9jcmVkZW50aWFscyIsInJldl9zaWciOiJkYWExNWM5NSIsImlhdCI6MTQ2NDI0ODU0MywiZXhwIjoxNDY0MjkxNzQzLCJpc3MiOiJodHRwczovLzhjOGRlYTRhLTdlZWItNDM4NC05ZTU4LWY0Y2I0NzNmNzRmZi5wcmVkaXgtdWFhLnJ1bi5hc3YtcHIuaWNlLnByZWRpeC5pby9vYXV0aC90b2tlbiIsInppZCI6IjhjOGRlYTRhLTdlZWItNDM4NC05ZTU4LWY0Y2I0NzNmNzRmZiIsImF1ZCI6WyJjdXJyZW50RGlnaXRhbFR3aW4iLCJ1YWEiLCJwcmVkaXgtYXNzZXQuem9uZXMuNzg2MGFmNDItOTIxMi00OTcxLTkwNDMtNDhlM2IzYTJiNGY1Il19.bGvg_7xfPNQmHp8hMHKAlYQUJDjdFodkAH3yySNNTYqH2vvO327EaXscrJwpY27w-D6qQQYnROgXEieRO1EMhw5P6buqI7-vusgmBhVJsicnepv_WKXcbl7K5iulLH1xYP7xZiXR50c1_rYCxlarExgOmxZDOYPHG1NFG33MXt-vn4MhlIjBoiQtYsEPOYbGw7SFOJ_9X7dNtsotVoKrTln7TRzUTzBMZKjMcInZu9nqGcpjE7WwIz0Bqz7vbHnBNVW53m4yOcPLlAmtch4MVZjrIK65LnH5xpOgXuJl3YTsKqww8OhGw0TSYzkl-sfaEvPfTkMuckWe2zXfuxrvXg"); //$NON-NLS-1$

		HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<MultiValueMap<String, String>>(
				map, httpHeader);
		ResponseEntity<String> response = this.rsttmplt.postForEntity("https://8c8dea4a-7eeb-4384-9e58-f4cb473f74ff.predix-uaa.run.asv-pr.ice.predix.io/check_token", httpEntity, String.class); //$NON-NLS-1$
		String str = response.getBody();
		return str.contains("client_id"); //$NON-NLS-1$
	}*/


	public boolean oAuthValidator1(HttpServletRequest request, HttpServletResponse response) {
		
		

		String string = request.getHeader(Messages.getString("Header"));
		HttpHeaders httpHeader = new HttpHeaders();
		httpHeader.setContentType(org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED);
		MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
		
		httpHeader.add(Messages.getString("Authorization"), Messages.getString("SimpleServiceImpl.test_message")); //$NON-NLS-2$//$NON-NLS-1$


		map.add("token", string); 

		HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<MultiValueMap<String, String>>(map, httpHeader);

		
		ResponseEntity<String> response1 = this.rsttmplt.postForEntity(Messages.getString("UAAUrl"), httpEntity, String.class); //$NON-NLS-1$


		String str = response1.getBody();
		return str.contains("client_id");
	}
	
}

