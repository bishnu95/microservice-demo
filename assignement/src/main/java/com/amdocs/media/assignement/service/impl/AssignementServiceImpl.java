package com.amdocs.media.assignement.service.impl;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.amdocs.media.assignement.service.IAssignementService;
import com.amdocs.media.assignement.service.UserProfileService;
import com.amdocs.media.assignement.service.remote.domain.RemoteServiceData;
import com.amdocs.media.model.UserProfileInfo;

@Service
public class AssignementServiceImpl implements IAssignementService {

	@Autowired
	private KafkaTemplate<String, UserProfileInfo> kafkaTemplate;

	@Value("${profile.update.topic}")
	private String profileUpdateTopic;

	@Value("${profile.delete.topic}")
	private String profileRemoveTopic;

	@Autowired
	private UserProfileService userProfileService;

	@Override
	public UserProfileInfo createUserProfile(UserProfileInfo userProfileInfo) {
		RemoteServiceData serviceData = new RemoteServiceData();
		serviceData.getServiceRequestContext().setBody(userProfileInfo);
		serviceData = userProfileService.createUserProfile(serviceData);
		if (Objects.nonNull(serviceData) && Objects.nonNull(serviceData.getServiceResponseContext())
				&& serviceData.getServiceResponseContext().getHttpStatus().is2xxSuccessful()) {
			return (UserProfileInfo) serviceData.getServiceResponseContext().getBody();
		}
		return null;
	}

	@Override
	public UserProfileInfo getUserProfile(Long id) {
		RemoteServiceData serviceData = new RemoteServiceData();
		serviceData.getServiceRequestContext().getPathParams().put("userId", String.valueOf(id));
		serviceData = userProfileService.getUserProfile(serviceData);
		if (Objects.nonNull(serviceData) && Objects.nonNull(serviceData.getServiceResponseContext())
				&& serviceData.getServiceResponseContext().getHttpStatus().is2xxSuccessful()) {
			return (UserProfileInfo) serviceData.getServiceResponseContext().getBody();
		}
		return null;
	}

	@Override
	public void updateUserProfile(UserProfileInfo userProfileInfo) {
		kafkaTemplate.send(profileUpdateTopic, userProfileInfo);
	}

	@Override
	public void removeUserProfile(Long userId) {
		UserProfileInfo userProfileInfo = new UserProfileInfo();
		userProfileInfo.setId(userId);
		kafkaTemplate.send(profileRemoveTopic, userProfileInfo);
	}
}
