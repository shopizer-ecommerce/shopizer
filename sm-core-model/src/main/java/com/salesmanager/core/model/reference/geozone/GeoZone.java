package com.salesmanager.core.model.reference.geozone;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.salesmanager.core.constants.SchemaConstant;
import com.salesmanager.core.model.generic.SalesManagerEntity;
import com.salesmanager.core.model.reference.country.Country;

@Entity
@Table(name = "GEOZONE", schema=SchemaConstant.SALESMANAGER_SCHEMA)
// TODO : create DAO / Service
public class GeoZone extends SalesManagerEntity<Long, GeoZone> {
	private static final long serialVersionUID = -5992008645857938825L;
	
	@Id
	@Column(name = "GEOZONE_ID")
	@TableGenerator(name = "TABLE_GEN", table = "SM_SEQUENCER", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "GEOZONE_SEQ_NEXT_VAL")
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "TABLE_GEN")
	private Long id;
	
	@OneToMany(mappedBy = "geoZone", cascade = CascadeType.ALL)
	private List<GeoZoneDescription> descriptions = new ArrayList<GeoZoneDescription>();
	
	@JsonIgnore
	@OneToMany(mappedBy = "geoZone", targetEntity = Country.class)
	private List<Country> countries = new ArrayList<Country>();
	

	
	@Column(name = "GEOZONE_NAME")
	private String name;
	
	@Column(name = "GEOZONE_CODE")
	private String code;
	
	public GeoZone() {
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public List<Country> getCountries() {
		return countries;
	}

	public void setCountries(List<Country> countries) {
		this.countries = countries;
	}

	public List<GeoZoneDescription> getDescriptions() {
		return descriptions;
	}

	public void setDescriptions(List<GeoZoneDescription> descriptions) {
		this.descriptions = descriptions;
	}


}
