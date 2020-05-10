package com.amdocs.media.assignement.api.controller;

import java.util.Objects;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amdocs.media.assignement.service.IAssignementService;
import com.amdocs.media.model.UserProfileInfo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@Api("user-profile-service")
@RequestMapping("/assignement")
public class AssignmentServiceController {

	private final static Logger LOG = LoggerFactory.getLogger(AssignmentServiceController.class);
	@Autowired
	private Environment env;
	@Autowired
	private IAssignementService iAssignementService;

	/**
	 * PUT
	 * 
	 * Update an user profile
	 * 
	 * @param userId
	 * @param userProfileInfo
	 * @return
	 */
	@PutMapping(value = "/profiles", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "update a profiles details", tags = "PUT /users", response = UserProfileInfo.class)
	@ApiResponses({ @ApiResponse(code = 204, message = "Successfully updated profile details"),
			@ApiResponse(code = 404, message = "The resource you were trying to update is not found") })
	public ResponseEntity<UserProfileInfo> getTransferProfiles(@Valid @RequestBody UserProfileInfo userProfileInfo) {
		LOG.info(env.getProperty("user-profile.put.request.received"));
		iAssignementService.updateUserProfile(userProfileInfo);
		LOG.info(env.getProperty("user-profile.put.request.executed"), userProfileInfo);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	/**
	 * DELETE
	 * 
	 * remove an user profile
	 * 
	 * @param userId
	 * @return
	 */
	@DeleteMapping(value = "/profiles/{userId}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "update a profiles details", tags = "DELETE /users/{userId}", response = UserProfileInfo.class)
	@ApiResponses({ @ApiResponse(code = 200, message = "Successfully updated profile details"),
			@ApiResponse(code = 404, message = "The resource you were trying to update is not found") })
	public ResponseEntity<UserProfileInfo> removeTransferProfiles(@PathVariable("userId") Long userId) {
		LOG.info(env.getProperty("user-profile.delete.request.received"), userId);
		iAssignementService.removeUserProfile(userId);
		LOG.info(env.getProperty("user-profile.delete.request.executed"));
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	/**
	 * POST
	 * 
	 * Create an user profile
	 * 
	 * @param userProfileInfo
	 * @return
	 */
	@PostMapping(value = "/profiles", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Add a user profile details", tags = "POST /users")
	@ApiResponses({ @ApiResponse(code = 201, message = "Successfully created user profile") })
	public ResponseEntity<UserProfileInfo> createTransferProfile(@Valid @RequestBody UserProfileInfo userProfileInfo) {
		LOG.info(env.getProperty("user-profile.post.request.received"), userProfileInfo);
		UserProfileInfo profileInfo = iAssignementService.createUserProfile(userProfileInfo);
		LOG.info(env.getProperty("user-profile.post.request.executed"));
		return new ResponseEntity<>(profileInfo, HttpStatus.CREATED);
	}

	/**
	 * GET
	 * 
	 * Get an user profile info by userId
	 * 
	 * @param userId
	 * @return
	 */
	@GetMapping(value = "/users/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "retrieve a types details", tags = "GET /users/{userId}", response = UserProfileInfo.class)
	@ApiResponses({ @ApiResponse(code = 200, message = "Successfully retrieved user info"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	public ResponseEntity<UserProfileInfo> getUserProfile(@PathVariable("userId") Long userId) {
		LOG.info(env.getProperty("user-profile.get.request.received"));
		UserProfileInfo userProfileInfo = iAssignementService.getUserProfile(userId);
		if (Objects.nonNull(userProfileInfo)) {
			LOG.info(env.getProperty("user-profile.get.request.executed"), userId);
			return new ResponseEntity<>(userProfileInfo, HttpStatus.OK);
		} else {
			LOG.info(env.getProperty("user-profile.no.record.found"));
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

}
