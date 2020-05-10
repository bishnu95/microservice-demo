package com.amdocs.media.model;

import javax.validation.constraints.Pattern;

public class Address {

	private Long id;
	private String addressLine1;
	private String addressLine2;
	@Pattern(regexp = "[0-9a-zA-Z]{3,}")
	private String state;
	@Pattern(regexp = "[a-zA-Z]{3,}")
	private String country;
	@Pattern(regexp = "[0-9]{6}")
	private String zipCode;

	public Address() {
		super();
	}

	/**
	 * @param id
	 * @param addressLine1
	 * @param addressLine2
	 * @param state
	 * @param country
	 * @param zipCode
	 */
	public Address(Long id, String addressLine1, String addressLine2, @Pattern(regexp = "[0-9a-zA-Z]{3,}") String state,
			@Pattern(regexp = "[a-zA-Z]{3,}") String country, @Pattern(regexp = "[0-9]{6}") String zipCode) {
		super();
		this.id = id;
		this.addressLine1 = addressLine1;
		this.addressLine2 = addressLine2;
		this.state = state;
		this.country = country;
		this.zipCode = zipCode;
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
	 * @return the addressLine1
	 */
	public String getAddressLine1() {
		return addressLine1;
	}

	/**
	 * @param addressLine1
	 *            the addressLine1 to set
	 */
	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	/**
	 * @return the addressLine2
	 */
	public String getAddressLine2() {
		return addressLine2;
	}

	/**
	 * @param addressLine2
	 *            the addressLine2 to set
	 */
	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state
	 *            the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @param country
	 *            the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * @return the zipCode
	 */
	public String getZipCode() {
		return zipCode;
	}

	/**
	 * @param zipCode
	 *            the zipCode to set
	 */
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	@Override
	public String toString() {
		return "Address [id=" + id + ", addressLine1=" + addressLine1 + ", addressLine2=" + addressLine2 + ", state="
				+ state + ", country=" + country + ", zipCode=" + zipCode + "]";
	}

}
