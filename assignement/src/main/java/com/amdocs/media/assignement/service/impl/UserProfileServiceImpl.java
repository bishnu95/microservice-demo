package com.amdocs.media.assignement.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.amdocs.media.assignement.api.exception.ResourceNotFoundException;
import com.amdocs.media.assignement.api.util.IConstant;
import com.amdocs.media.assignement.service.UserProfileService;
import com.amdocs.media.assignement.service.remote.domain.RemoteServiceData;
import com.amdocs.media.assignement.service.remote.domain.ServiceRequestContext;
import com.amdocs.media.assignement.service.remote.domain.ServiceResponseContext;
import com.amdocs.media.model.UserProfileInfo;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Component
public class UserProfileServiceImpl implements UserProfileService {

	private static final Logger LOG = LoggerFactory.getLogger(UserProfileServiceImpl.class);

	private static final String SERVICE_NAME = "User-Profile-Service";

	private static final String SERVICE_INVOCATION_SUCCESS_LOG_MSG = "Received response from URI : {} having service data : {}";
	private static final String FALLBACK_ACTION_START_MSG = "Performing fallback action with service data : {}";
	private static final String FALLBACK_ACTION_END_MSG = "Fallback action completed with service data : {}";
	private static final String START_INVOCATION_LOG_MSG = "Invoking : {}";

	@Autowired
	@Qualifier(IConstant.BEAN_ID_LOAD_BALANCED_REST_TEMPLATE)
	private RestTemplate restTemplate;

	private final static java.lang.String BASE_URI = "http://user-profile-service";

	@Override
	@HystrixCommand(fallbackMethod = "createUserProfileFallback")
	public RemoteServiceData createUserProfile(RemoteServiceData serviceData) {

		try {
			serviceData.setName(SERVICE_NAME);
			serviceData.setOperation(HttpMethod.POST.name());

			// Prepare Request
			ServiceRequestContext serviceRequestContext = serviceData.getServiceRequestContext();
			String url = BASE_URI + "/users";
			serviceRequestContext
					.setUrl(UriComponentsBuilder.fromHttpUrl(url).queryParams(serviceRequestContext.getQueryParams())
							.buildAndExpand(serviceRequestContext.getPathParams()).toUriString());

			// Invoke Contact Service API
			serviceRequestContext.setMethod(HttpMethod.POST.name());
			LOG.info(START_INVOCATION_LOG_MSG, url);
			ResponseEntity<UserProfileInfo> responseEntity = restTemplate.exchange(serviceRequestContext.getUrl(),
					HttpMethod.POST,
					new HttpEntity<>(serviceRequestContext.getBody(), serviceRequestContext.getHttpHeaders()),
					UserProfileInfo.class);

			// Retrieve Response from Contact Service API
			ServiceResponseContext serviceResponseContext = serviceData.getServiceResponseContext();
			serviceResponseContext.setBody(responseEntity.getBody());
			serviceResponseContext.setHeaders(responseEntity.getHeaders());
			serviceResponseContext.setHttpStatus(responseEntity.getStatusCode());
			serviceData.setServiceResponseContext(serviceResponseContext);

			LOG.info(SERVICE_INVOCATION_SUCCESS_LOG_MSG, url, serviceData);

			return serviceData;

		} catch (Exception e) {
			// This will trigger fallback method invocation by HystrixCommand
			throw e;
		}
	}

	/**
	 * Fallback Method for createUserProfile
	 *
	 * @return RemoteServiceData
	 */
	@Override
	public RemoteServiceData createUserProfileFallback(RemoteServiceData serviceData, Throwable throwable) {

		LOG.info(FALLBACK_ACTION_START_MSG, serviceData);

		serviceData.setFallbackFlag(true);
		serviceData.setThrowable(throwable);
		serviceData.setServiceResponseContext(null);

		LOG.info(FALLBACK_ACTION_END_MSG, serviceData);

		return serviceData;
	}

	@Override
	@HystrixCommand(fallbackMethod = "getUserProfileFallback")
	public RemoteServiceData getUserProfile(RemoteServiceData serviceData) {

		try {
			serviceData.setName(SERVICE_NAME);
			serviceData.setOperation(HttpMethod.GET.name());

			// Prepare Request
			ServiceRequestContext serviceRequestContext = serviceData.getServiceRequestContext();
			String url = BASE_URI + "/users/{userId}";
			serviceRequestContext
					.setUrl(UriComponentsBuilder.fromHttpUrl(url).queryParams(serviceRequestContext.getQueryParams())
							.buildAndExpand(serviceRequestContext.getPathParams()).toUriString());

			// Invoke Contact Service API
			serviceRequestContext.setMethod(HttpMethod.GET.name());
			LOG.info(START_INVOCATION_LOG_MSG, url);
			ResponseEntity<UserProfileInfo> responseEntity = restTemplate.exchange(serviceRequestContext.getUrl(),
					HttpMethod.GET, new HttpEntity<>(serviceRequestContext.getHttpHeaders()), UserProfileInfo.class);

			// Retrieve Response from Contact Service API
			ServiceResponseContext serviceResponseContext = serviceData.getServiceResponseContext();
			serviceResponseContext.setBody(responseEntity.getBody());
			serviceResponseContext.setHeaders(responseEntity.getHeaders());
			serviceResponseContext.setHttpStatus(responseEntity.getStatusCode());
			serviceData.setServiceResponseContext(serviceResponseContext);

			LOG.info(SERVICE_INVOCATION_SUCCESS_LOG_MSG, url, serviceData);

			return serviceData;

		} catch (Exception e) {
			// This will trigger fallback method invocation by HystrixCommand
			if (e instanceof HttpClientErrorException.NotFound) {
				throw new ResourceNotFoundException(e.getLocalizedMessage());
			} else {
				throw e;
			}
		}
	}

	/**
	 * Fallback Method for getUserProfile
	 *
	 * @return RemoteServiceData
	 */
	@Override
	public RemoteServiceData getUserProfileFallback(RemoteServiceData serviceData, Throwable throwable) {

		LOG.info(FALLBACK_ACTION_START_MSG, serviceData);

		serviceData.setFallbackFlag(true);
		serviceData.setThrowable(throwable);
		serviceData.setServiceResponseContext(null);

		LOG.info(FALLBACK_ACTION_END_MSG, serviceData);

		return serviceData;
	}
}
