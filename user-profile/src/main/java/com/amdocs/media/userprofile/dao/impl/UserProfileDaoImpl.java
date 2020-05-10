package com.amdocs.media.userprofile.dao.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.amdocs.media.userprofile.dao.IUserProfileDao;
import com.amdocs.media.userprofile.dao.entity.UserProfileEntity;
import com.amdocs.media.userprofile.dao.entity.repository.UserProfileRepository;

@Repository
public class UserProfileDaoImpl implements IUserProfileDao {

	@Autowired
	private UserProfileRepository userProfileRepository;

	@Override
	public UserProfileEntity createUserProfile(UserProfileEntity userProfileEntity) {
		return userProfileRepository.save(userProfileEntity);
	}

	@Override
	public UserProfileEntity getUserProfile(Long id) {
		Optional<UserProfileEntity> findByIdResult = userProfileRepository.findById(id);
		return findByIdResult.isPresent() ? findByIdResult.get() : null;
	}

	@Override
	public UserProfileEntity updateUserProfile(UserProfileEntity userProfileEntity) {
		return userProfileRepository.save(userProfileEntity);
	}

	@Override
	public int removeUserProfile(Long id) {
		userProfileRepository.deleteById(id);
		return 1;
	}

}
