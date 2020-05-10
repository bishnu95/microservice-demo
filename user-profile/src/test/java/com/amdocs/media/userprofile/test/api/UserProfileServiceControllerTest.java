package com.amdocs.media.userprofile.test.api;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.amdocs.media.model.Address;
import com.amdocs.media.model.UserProfileInfo;
import com.amdocs.media.userprofile.api.controller.UserProfileServiceController;
import com.amdocs.media.userprofile.service.IUserProfileService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(UserProfileServiceController.class)
@PropertySource("classpath:log.message")
public class UserProfileServiceControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private IUserProfileService iUserProfileService;

	@Test
	public void testGetUserProfile() throws Exception {
		Mockito.when(iUserProfileService.getUserProfile(Mockito.any(String.class))).thenReturn(defaultUserProfile());
		mockMvc.perform(MockMvcRequestBuilders.get("/users/1").accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(HttpStatus.OK.value()))
				.andDo(MockMvcResultHandlers.print());
	}

	@Test

	public void testCreateUserProfile() throws Exception {

		Mockito.when(iUserProfileService.createUserProfile(Mockito.any(UserProfileInfo.class)))
				.thenReturn(defaultUserProfile());

		ObjectMapper mapper = new ObjectMapper();
		String requestBody = mapper.writeValueAsString(defaultUserProfile());

		mockMvc.perform(MockMvcRequestBuilders.post("/users").content(requestBody).accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(HttpStatus.CREATED.value()))
				.andDo(MockMvcResultHandlers.print());

	}

	private UserProfileInfo defaultUserProfile() {
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

}
