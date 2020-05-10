package com.amdocs.media.assignement.service;

import com.amdocs.media.assignement.service.remote.domain.RemoteServiceData;

public interface UserProfileService {

	RemoteServiceData getUserProfile(RemoteServiceData serviceData);

	RemoteServiceData getUserProfileFallback(RemoteServiceData serviceData, Throwable throwable);

	RemoteServiceData createUserProfile(RemoteServiceData serviceData);

	RemoteServiceData createUserProfileFallback(RemoteServiceData serviceData, Throwable throwable);

}
