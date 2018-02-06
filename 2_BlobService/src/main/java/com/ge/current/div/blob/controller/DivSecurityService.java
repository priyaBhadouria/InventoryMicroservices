package com.ge.current.div.blob.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ge.current.div.blob.OauthVerification;
import com.ge.current.div.blob.common.utility.IDivSecurityRepository;
import com.ge.current.div.blob.dto.DivSecurityDTO;



@Configuration
@RestController
public class DivSecurityService {

	@Autowired
	OauthVerification oAuth;
	@Autowired IDivSecurityRepository divSecurityRepository;
	
	@RequestMapping(value = "/security", method = RequestMethod.GET)
	public String getMessage() {

		return "Welcome To Digital Inventory Visibility Security Part.... :)";
	}
	@RequestMapping(value="/getRole")
	public @ResponseBody DivSecurityDTO getRole(@Param("sso") String sso)
	{
		DivSecurityDTO securityDTO = new DivSecurityDTO();
	securityDTO.setSso(sso);
	securityDTO.setGroupName(divSecurityRepository.getRole(sso)); 
		return securityDTO;
	}
}
