package com.main.domain.location;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Embeddable
public class Address {
	private String business;

	@Column(nullable = false)
	@NotEmpty(message = "required")
	@NotNull(message = "required")
	private String streetLine1;

	private String streetLine2;

	@Column(nullable = false)
	private String city;

	@Column(nullable = false)
	private String postcode;

	public Address(String business, String streetLine1, String streetLine2, String city, String postcode) {
		this.business = business;
		this.streetLine1 = streetLine1;
		this.streetLine2 = streetLine2;
		this.city = city;
		this.postcode = postcode;
	}

	public Address() {
	}

	public String getBusiness() {
		return business;
	}

	public void setBusiness(String business) {
		this.business = business;
	}

	public String getStreetLine1() {
		return streetLine1;
	}

	public void setStreetLine1(String streetLine1) {
		this.streetLine1 = streetLine1;
	}

	public String getStreetLine2() {
		return streetLine2;
	}

	public void setStreetLine2(String streetLine2) {
		this.streetLine2 = streetLine2;
	}

	public String getcity() {
		return city;
	}

	public void setcity(String city) {
		this.city = city;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Address address = (Address) o;
		return Objects.equals(business, address.business) && Objects.equals(streetLine1, address.streetLine1)
				&& Objects.equals(streetLine2, address.streetLine2) && city == address.city
				&& Objects.equals(postcode, address.postcode);
	}

	@Override
	public int hashCode() {
		return Objects.hash(business, streetLine1, streetLine2, city, postcode);
	}

	@Override
	public String toString() {
		return "Address{" + "business='" + business + '\'' + ", streetLine1='" + streetLine1 + '\'' + ", streetLine2='"
				+ streetLine2 + '\'' + ", city=" + city + ", postcode=" + postcode + '}';
	}
}
