package com.amdocs.media.userprofile.test.dao;

import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.amdocs.media.userprofile.dao.IUserProfileDao;
import com.amdocs.media.userprofile.dao.entity.AddressEntity;
import com.amdocs.media.userprofile.dao.entity.UserProfileEntity;
import com.amdocs.media.userprofile.dao.entity.repository.UserProfileRepository;
import com.amdocs.media.userprofile.test.config.TestConfiguration;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestConfiguration.class)
public class UserProfileDaoTest {

	@Autowired
	private IUserProfileDao iUserProfileDao;

	@MockBean
	private UserProfileRepository userProfileRepository;

	@Test
	public void testPostCities() {
		UserProfileEntity userProfileEntity = defaultUserProfileEntity();
		Mockito.doReturn(userProfileEntity).when(userProfileRepository).save(Mockito.any(UserProfileEntity.class));
		Assert.assertNotNull(iUserProfileDao.createUserProfile(userProfileEntity));
	}

	@Test
	public void testGetCity() {
		Long id = 1L;
		UserProfileEntity userProfileEntity = defaultUserProfileEntity();
		Optional<UserProfileEntity> optional = Optional.of(userProfileEntity);
		Mockito.doReturn(optional).when(userProfileRepository).findById(Mockito.eq(id));
		Assert.assertEquals(userProfileEntity, iUserProfileDao.getUserProfile(id));

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
