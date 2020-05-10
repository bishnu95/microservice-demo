package com.amdocs.media.userprofile.dao;

import com.amdocs.media.userprofile.dao.entity.UserProfileEntity;

public interface IUserProfileDao {

	UserProfileEntity createUserProfile(UserProfileEntity userProfileEntity);

	UserProfileEntity getUserProfile(Long id);

	UserProfileEntity updateUserProfile(UserProfileEntity userProfileEntity);

	int removeUserProfile(Long id);
}
