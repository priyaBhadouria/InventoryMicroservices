package com.ge.current.div.blob.Cloud;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
@Configuration
@Profile("cloud")
public class DatabaseConfigCloud extends DataSource implements EnvironmentAware{
	@Override
	public void setEnvironment(Environment env) {
		String dbServiceName = env.getProperty("db_service_name");
		if (dbServiceName != null) {
			String envURL = env.getProperty("vcap.services."+dbServiceName+".credentials.uri");
			setUrl(envURL);
			String envUserName = env.getProperty("vcap.services."+dbServiceName+".credentials.username");
			if (envUserName != null) {
				setUsername(envUserName);
			} 
			String envUserPassword = env.getProperty("vcap.services."+dbServiceName+".credentials.password");
			if (envUserPassword != null) {
				setPassword(envUserPassword);
			}
		}
	}
	
}
