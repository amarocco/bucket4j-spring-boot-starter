package com.giffing.bucket4j.spring.boot.starter.springboot2.actuator;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Configuration;

import com.giffing.bucket4j.spring.boot.starter.context.Bucket4jConfigurationHolder;

@Configuration
@ConditionalOnClass(Endpoint.class)
@Endpoint(id = "bucket4j")
public class Bucket4jEndpoint {

	@Autowired(required = false)
	@Qualifier("SERVLET")
	private Bucket4jConfigurationHolder servletConfigs;
	
	@Autowired(required = false)
	@Qualifier("ZUUL")
	private Bucket4jConfigurationHolder zuulConfigs;
	
	@ReadOperation
	public Map<String, Object> bucket4jConfig() {
		Map<String, Object> result = new HashMap<>();
		if(servletConfigs != null) {
			result.put("servlet", servletConfigs.getFilterConfiguration());
		}
		if(zuulConfigs != null) {
			result.put("zuul", zuulConfigs.getFilterConfiguration());
		}
		return result;
	}

}
