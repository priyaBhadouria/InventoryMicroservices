/**
 * 
 */
package com.ge.current.digitalinventoryservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author 842449
 *
 */

@SpringBootApplication
@EntityScan(basePackages = ("com.ge.current.digitalinventoryservice"))
@EnableJpaRepositories("com.ge.current.digitalinventoryservice.repository")
public class DigitalInventoryServicesApplication {

	
	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		SpringApplication.run(DigitalInventoryServicesApplication.class, args);
	}

}
