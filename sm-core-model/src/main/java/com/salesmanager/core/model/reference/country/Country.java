package com.salesmanager.core.model.reference.country;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Transient;

import com.salesmanager.core.constants.SchemaConstant;
import com.salesmanager.core.model.generic.SalesManagerEntity;
import com.salesmanager.core.model.reference.geozone.GeoZone;
import com.salesmanager.core.model.reference.zone.Zone;

@Entity
@Table(name = "COUNTRY", schema=SchemaConstant.SALESMANAGER_SCHEMA)
@Cacheable
public class Country extends SalesManagerEntity<Integer, Country> {
	private static final long serialVersionUID = -7388011537255588035L;

	@Id
	@Column(name="COUNTRY_ID")
	@TableGenerator(name = "TABLE_GEN", table = "SM_SEQUENCER", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT",
	pkColumnValue = "COUNTRY_SEQ_NEXT_VAL")
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "TABLE_GEN")
	private Integer id;
	
	@OneToMany(mappedBy = "country", cascade = CascadeType.ALL)
	private Set<CountryDescription> descriptions = new HashSet<CountryDescription>();

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "country")
	private Set<Zone> zones = new HashSet<Zone>();
	
	@ManyToOne(targetEntity = GeoZone.class)
	@JoinColumn(name = "GEOZONE_ID")
	private GeoZone geoZone;
	
	@Column(name = "COUNTRY_SUPPORTED")
	private boolean supported = true;
	
	@Column(name = "COUNTRY_ISOCODE", unique=true, nullable = false)
	private String isoCode;
	
	@Transient
	private String name;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Country() {
	}
	
	public Country(String isoCode) {
		this.setIsoCode(isoCode);
	}
	
	public boolean getSupported() {
		return supported;
	}

	public void setSupported(boolean supported) {
		this.supported = supported;
	}

	public String getIsoCode() {
		return isoCode;
	}

	public void setIsoCode(String isoCode) {
		this.isoCode = isoCode;
	}


	@Override
	public Integer getId() {
		return id;
	}

	@Override
	public void setId(Integer id) {
		this.id = id;
	}


	public Set<Zone> getZones() {
		return zones;
	}

	public void setZones(Set<Zone> zones) {
		this.zones = zones;
	}


	public GeoZone getGeoZone() {
		return geoZone;
	}

	public void setGeoZone(GeoZone geoZone) {
		this.geoZone = geoZone;
	}
	
	
	public Set<CountryDescription> getDescriptions() {
		return descriptions;
	}

	public void setDescriptions(Set<CountryDescription> descriptions) {
		this.descriptions = descriptions;
	}
}
