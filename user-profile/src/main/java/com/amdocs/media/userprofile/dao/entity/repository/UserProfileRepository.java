package com.amdocs.media.userprofile.dao.entity.repository;

import org.springframework.data.repository.CrudRepository;

import com.amdocs.media.userprofile.dao.entity.UserProfileEntity;

public interface UserProfileRepository extends CrudRepository<UserProfileEntity, Long> {

}
