package com.ge.current.div.blob.controller;



import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ge.current.div.blob.OauthVerification;
import com.ge.current.div.blob.dto.ProcessOnDemandStatusDTO;
import com.ge.current.div.blob.dto.UploadResponseDTO;
import com.ge.current.div.blob.service.BlobStoreService;

//@EnableAutoConfiguration
@Configuration
//@ComponentScan
@RestController
//@EnableDiscoveryClient
//@CrossOrigin
public class BlobController
{
	
	@Autowired
	OauthVerification oAuth;
	
	@Autowired
	private Environment env;
	
	@Autowired
	BlobStoreService blobStoreService;
	
	@Autowired
	BatchProcessController batchProcessController;
	
	@Autowired
	ServletContext context;

	private static final String SUCCESS = "SUCCESS";

	@RequestMapping(value="/getData")
	public String getData(){
		return "Hello World working";
	}
	
	@RequestMapping(value = "/processOnDemand", method = RequestMethod.GET)
	private void processOnDemand(HttpServletRequest request, HttpServletResponse response)
	{
		oAuth.oAuthValidator(request, response);
		batchProcessController.executeJobOnDemand();
		return ;
	}

	@RequestMapping(value = "/getTemplateDetails", method = RequestMethod.GET)
	private String getLoggerData(HttpServletRequest request, HttpServletResponse response)
	{
		oAuth.oAuthValidator(request, response);
		return blobStoreService.getTemplateDetails();
	}
	
	@RequestMapping(value = "/initiateProcessOnDemand", method = RequestMethod.GET)
	private void initiateProcessOnDemand(@Param("sso") String sso)
	{
		//oAuth.oAuthValidator(request, response);
		
		blobStoreService.initiateProcessOnDemand(sso);
	}
	
	@RequestMapping(value = "/enableProcessOnDemand", method = RequestMethod.GET)
	private ProcessOnDemandStatusDTO enableProcessOnDemand()
	{
		//oAuth.oAuthValidator(request, response);
		
		return blobStoreService.enableProcessOnDemand();
	}
	
	@RequestMapping(value = "/getUploadDetails", method = RequestMethod.GET)
	private String getUploadDetails(HttpServletRequest request, HttpServletResponse response)
	{
		oAuth.oAuthValidator(request, response);
		return blobStoreService.getUploadDetails();
	}
	
@RequestMapping(value = "/getLoggerData", method = RequestMethod.GET)
	private String getLoggerData(@RequestParam("uploadId") String uploadId,HttpServletRequest request, HttpServletResponse response)	
	{	
	  oAuth.oAuthValidator(request, response);
	return blobStoreService.getLoggerData(uploadId);
	}
	
	@RequestMapping(value = "/uploadMultiBlob", method = RequestMethod.POST)
	private List<UploadResponseDTO> putDocumentOnblob(@RequestParam("templateName") String templateName, @RequestParam("usercomments") String usercomments,@RequestParam("file") MultipartFile[] files,@RequestParam("SSO")String SSO,HttpServletRequest request, HttpServletResponse response)
	{
		boolean flag1 = false; 
		flag1=oAuth.oAuthValidator(request, response);
		
		List<UploadResponseDTO> responseDto= new ArrayList<UploadResponseDTO>();
		if(flag1==true){
		responseDto = blobStoreService.putDocumentOnblob(templateName, usercomments, files,SSO);

		//resp = new ResponseEntity<String>(message, HttpStatus.OK);
		
		
		}
		return responseDto;
		
	}
	
	
	@RequestMapping(value = "/uploadMultiBlob1", method = RequestMethod.POST)
	private List<UploadResponseDTO> putDocumentOnblob(@RequestParam("templateName") String templateName, @RequestParam("usercomments") String usercomments,@RequestParam("file") MultipartFile[] files,@RequestParam("SSO")String SSO)
	{
		
		List<UploadResponseDTO> responseDto= new ArrayList<UploadResponseDTO>();
		
		responseDto = blobStoreService.putDocumentOnblob(templateName, usercomments, files,SSO);

		//resp = new ResponseEntity<String>(message, HttpStatus.OK);
		
		

		return responseDto;
		
	}
	
	//code added for downloading excel file
/*	@RequestMapping(value = "/getExcelFile", method = RequestMethod.GET)
	 public void getExcelFile(@RequestParam(value = "directory") String directory, @RequestParam(value = "fileName") String filename, HttpServletResponse response) throws Exception {
	 try {
	 InputStream stream = getFile(directory,filename);
	 response.setContentType("application/vnd.ms-excel");
	 response.setHeader("Content-Disposition", "attachment;filename="+filename);
	org.apache.commons.io.IOUtils.copy(stream, response.getOutputStream());
	 response.flushBuffer(); 
	} 
	 catch (IOException ex) {
	  
	 throw new RuntimeException("IOError writing file to output stream");
	 }
	 }*/

	
	/* private InputStream getFile(String directory, String filename) throws Exception {
	 return blobStoreService.getFile(directory, filename);
	 }*/
}

