package com.salesmanager.shop.model.customer.address;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Customer or someone address
 * @author carlsamson
 *
 */
public class Address extends AddressLocation implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotEmpty(message="{NotEmpty.customer.firstName}")
	private String firstName;
	
	@NotEmpty(message="{NotEmpty.customer.lastName}")
	private String lastName;
	
	private String bilstateOther;

	private String company;

	private String phone;
	private String address;
	private String city;
	

	

	private String stateProvince;
	private boolean billingAddress;
	
	private String latitude;
	private String longitude;
	
	private String zone;//code
	
	@NotEmpty(message="{NotEmpty.customer.billing.country}")
	private String country;//code
	


	public void setStateProvince(String stateProvince) {
		this.stateProvince = stateProvince;
	}

	public void setCountry(String country) {
		this.country = country;
	}



	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}



	public String getStateProvince() {
		return stateProvince;
	}

	public String getCountry() {
		return country;
	}

	public void setZone(String zone) {
		this.zone = zone;
	}

	public String getZone() {
		return zone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPhone() {
		return phone;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

    public boolean isBillingAddress()
    {
        return billingAddress;
    }

    public void setBillingAddress( boolean billingAddress )
    {
        this.billingAddress = billingAddress;
    }

    public String getBilstateOther()
    {
        return bilstateOther;
    }

    public void setBilstateOther( String bilstateOther )
    {
        this.bilstateOther = bilstateOther;
    }

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

}
