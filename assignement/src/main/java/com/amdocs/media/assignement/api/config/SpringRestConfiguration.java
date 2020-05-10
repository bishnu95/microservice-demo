package com.amdocs.media.assignement.api.config;

import static com.amdocs.media.assignement.api.util.IConstant.BEAN_ID_DEFAULT_REST_TEMPLATE;
import static com.amdocs.media.assignement.api.util.IConstant.BEAN_ID_LOAD_BALANCED_REST_TEMPLATE;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class SpringRestConfiguration {
	private static final Logger LOG = LoggerFactory.getLogger(SpringRestConfiguration.class);

	/**
	 * Load balanced RestTemplate
	 */
	@Bean(BEAN_ID_LOAD_BALANCED_REST_TEMPLATE)
	@LoadBalanced
	public RestTemplate loadBalancedRestTemplate() {
		LOG.debug("Load RestTemplate bean id - {}", BEAN_ID_LOAD_BALANCED_REST_TEMPLATE);
		return new RestTemplate();
	}

	/**
	 * Default Rest Template
	 */
	@Bean(BEAN_ID_DEFAULT_REST_TEMPLATE)
	public RestTemplate defaultRestTemplate() {
		LOG.debug("Default RestTemplate bean id - {}", BEAN_ID_DEFAULT_REST_TEMPLATE);
		return new RestTemplate();
	}

}
