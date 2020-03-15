package com.salesmanager.shop.model.customer.address;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import io.swagger.annotations.ApiModelProperty;

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

	@ApiModelProperty(notes = "Customer billing first name")
	@NotEmpty(message="{NotEmpty.customer.firstName}")
	private String firstName;
	
	@ApiModelProperty(notes = "Customer billing last name")
	@NotEmpty(message="{NotEmpty.customer.lastName}")
	private String lastName;
	
	private String bilstateOther;

	private String company;

	private String phone;
	@ApiModelProperty(notes = "Customer billing address")
	private String address;
	@ApiModelProperty(notes = "Customer billing city")
	private String city;
	

	
	@ApiModelProperty(notes = "Customer billing state / province (if no 2 letter codes, example: North estate)")
	private String stateProvince;
	private boolean billingAddress;
	
	private String latitude;
	private String longitude;
	
	@ApiModelProperty(notes = "Customer billing state / province (2 letter code CA, ON...)")
	private String zone;//code
	
	@ApiModelProperty(notes = "Customer billing country code (2 letter code US, CA, UK, IT, IN, CN...)")
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
