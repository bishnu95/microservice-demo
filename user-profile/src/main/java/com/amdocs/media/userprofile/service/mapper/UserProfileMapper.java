package com.amdocs.media.userprofile.service.mapper;

import java.util.Objects;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.amdocs.media.model.UserProfileInfo;
import com.amdocs.media.userprofile.dao.entity.UserProfileEntity;

@Component
public class UserProfileMapper {

	@Autowired
	private ModelMapper modelMapper;

	public UserProfileInfo convertToModel(UserProfileEntity userProfileEntity) {
		if (Objects.nonNull(userProfileEntity)) {
			return modelMapper.map(userProfileEntity, UserProfileInfo.class);
		}
		return null;
	}

	public UserProfileEntity convertToEntity(UserProfileInfo userProfileInfo) {
		if (Objects.nonNull(userProfileInfo)) {
			return modelMapper.map(userProfileInfo, UserProfileEntity.class);
		}
		return null;
	}

}
