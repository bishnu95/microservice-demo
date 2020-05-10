package com.amdocs.media.userprofile.test.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.amdocs.media.model.Address;
import com.amdocs.media.model.UserProfileInfo;
import com.amdocs.media.userprofile.dao.IUserProfileDao;
import com.amdocs.media.userprofile.dao.entity.AddressEntity;
import com.amdocs.media.userprofile.dao.entity.UserProfileEntity;
import com.amdocs.media.userprofile.service.IUserProfileService;
import com.amdocs.media.userprofile.test.config.TestConfiguration;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestConfiguration.class)
public class UserProfileServiceTest {

	@Autowired
	private IUserProfileService iUserProfileService;

	@MockBean
	private IUserProfileDao iUserProfileDao;

	@Test
	public void testGetUserProfile() {
		String id = "1";
		UserProfileEntity userProfileEntity = defaultUserProfileEntity();
		Mockito.when(iUserProfileDao.getUserProfile(Mockito.eq(Long.valueOf(id)))).thenReturn(userProfileEntity);
		Assert.assertNotNull(iUserProfileService.getUserProfile(id));
	}

	@Test
	public void testCreateUserProfile() {
		UserProfileInfo userProfileInfo = defaultUserProfileInfo();
		UserProfileEntity userProfileEntity = defaultUserProfileEntity();
		Mockito.doReturn(userProfileEntity).when(iUserProfileDao)
				.createUserProfile(Mockito.any(UserProfileEntity.class));
		Assert.assertEquals(userProfileInfo.getFirstName(),
				iUserProfileService.createUserProfile(userProfileInfo).getFirstName());

	}

	private UserProfileInfo defaultUserProfileInfo() {
		UserProfileInfo userProfileInfo = new UserProfileInfo();
		userProfileInfo.setFirstName("FisrtName");
		userProfileInfo.setLastName("LastName");
		userProfileInfo.setEmail("example@domain.com");
		userProfileInfo.setPhoneNumber("9999999999");
		Address address = new Address();
		userProfileInfo.setAddress(address);
		address.setAddressLine1("addLine1");
		address.setAddressLine2("addLine2");
		address.setCountry("country");
		address.setState("state");
		address.setZipCode("111111");
		return userProfileInfo;
	}

	private UserProfileEntity defaultUserProfileEntity() {
		UserProfileEntity userProfileEntity = new UserProfileEntity();
		userProfileEntity.setFirstName("FisrtName");
		userProfileEntity.setLastName("LastName");
		userProfileEntity.setEmail("example@domain.com");
		userProfileEntity.setPhoneNumber("9999999999");
		AddressEntity address = new AddressEntity();
		userProfileEntity.setAddress(address);
		address.setAddressLine1("addLine1");
		address.setAddressLine2("addLine2");
		address.setCountry("country");
		address.setState("state");
		address.setZipCode("111111");
		return userProfileEntity;
	}
}
