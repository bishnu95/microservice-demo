package com.amdocs.media.assignement.service;

import com.amdocs.media.model.UserProfileInfo;

public interface IAssignementService {

	UserProfileInfo createUserProfile(UserProfileInfo userProfileInfo);

	UserProfileInfo getUserProfile(Long id);

	void updateUserProfile(UserProfileInfo userProfileInfo);

	void removeUserProfile(Long userId);

}
