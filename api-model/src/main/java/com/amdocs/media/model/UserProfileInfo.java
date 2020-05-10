package com.amdocs.media.model;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

public class UserProfileInfo {
	private Long id;
	@Pattern(regexp = "[a-zA-Z]{3,}")
	private String firstName;
	@Pattern(regexp = "[a-zA-Z]{3,}")
	private String lastName;
	@Pattern(regexp = "[0-9]{10}")
	private String phoneNumber;
	@Email
	private String email;
	@Valid
	private Address address;

	/**
	 * 
	 */
	public UserProfileInfo() {
		super();
	}

	/**
	 * @param id
	 * @param firstName
	 * @param lastName
	 * @param phoneNumber
	 * @param email
	 * @param address
	 */
	public UserProfileInfo(Long id, @Pattern(regexp = "[a-zA-Z]") String firstName,
			@Pattern(regexp = "[a-zA-Z]") String lastName, @Pattern(regexp = "[0-9]{10}") String phoneNumber,
			@Email String email, @Valid Address address) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.address = address;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName
	 *            the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName
	 *            the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the phoneNumber
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * @param phoneNumber
	 *            the phoneNumber to set
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the address
	 */
	public Address getAddress() {
		return address;
	}

	/**
	 * @param address
	 *            the address to set
	 */
	public void setAddress(Address address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "UserProfileInfo [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", phoneNumber="
				+ phoneNumber + ", email=" + email + ", address=" + address + "]";
	}

}
