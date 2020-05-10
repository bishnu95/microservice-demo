package com.amdocs.media.userprofile.service;

import com.amdocs.media.model.UserProfileInfo;

public interface IUserProfileService {

	UserProfileInfo createUserProfile(UserProfileInfo userProfileInfo);

	UserProfileInfo getUserProfile(String id);

	void updateUserProfile(UserProfileInfo userProfileInfo);

	void removeUserProfile(UserProfileInfo userProfileInfo);

}
