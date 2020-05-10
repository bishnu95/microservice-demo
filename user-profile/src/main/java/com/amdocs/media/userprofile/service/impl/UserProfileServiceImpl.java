package com.amdocs.media.userprofile.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.amdocs.media.model.UserProfileInfo;
import com.amdocs.media.userprofile.dao.IUserProfileDao;
import com.amdocs.media.userprofile.service.IUserProfileService;
import com.amdocs.media.userprofile.service.mapper.UserProfileMapper;

@Service
public class UserProfileServiceImpl implements IUserProfileService {

	@Autowired
	private IUserProfileDao iUserProfileDao;

	@Autowired
	private UserProfileMapper userProfileMapper;

	@Override
	public UserProfileInfo createUserProfile(UserProfileInfo userProfileInfo) {
		return userProfileMapper
				.convertToModel(iUserProfileDao.createUserProfile(userProfileMapper.convertToEntity(userProfileInfo)));
	}

	@Override
	public UserProfileInfo getUserProfile(String id) {
		return userProfileMapper.convertToModel(iUserProfileDao.getUserProfile(Long.valueOf(id)));
	}

	@Override
	@KafkaListener(topics = "user_profile_update_topic", groupId = "user_profile", containerFactory = "kafkaListnerFactory")
	public void updateUserProfile(UserProfileInfo userProfileInfo) {
		iUserProfileDao.updateUserProfile(userProfileMapper.convertToEntity(userProfileInfo));
	}

	@Override
	@KafkaListener(topics = "user_profile_delete_topic", groupId = "user_profile", containerFactory = "kafkaListnerFactory")
	public void removeUserProfile(UserProfileInfo userProfileInfo) {
		iUserProfileDao.removeUserProfile(userProfileInfo.getId());
	}

}
