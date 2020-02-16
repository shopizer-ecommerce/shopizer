package com.salesmanager.core.model.reference.geozone;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.salesmanager.core.constants.SchemaConstant;
import com.salesmanager.core.model.common.description.Description;

@Entity
@Table(name="GEOZONE_DESCRIPTION", schema=SchemaConstant.SALESMANAGER_SCHEMA, uniqueConstraints={
		@UniqueConstraint(columnNames={
			"GEOZONE_ID",
			"LANGUAGE_ID"
		})
	}
)
public class GeoZoneDescription extends Description {
	private static final long serialVersionUID = 7759498146450786218L;
	
	@ManyToOne(targetEntity = GeoZone.class)
	@JoinColumn(name = "GEOZONE_ID")
	private GeoZone geoZone;
	
	public GeoZoneDescription() {
	}

	public GeoZone getGeoZone() {
		return geoZone;
	}

	public void setGeoZone(GeoZone geoZone) {
		this.geoZone = geoZone;
	}
}
