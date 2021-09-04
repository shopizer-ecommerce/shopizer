package com.salesmanager.core.model.reference.country;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.salesmanager.core.constants.SchemaConstant;
import com.salesmanager.core.model.common.description.Description;
import com.salesmanager.core.model.reference.language.Language;

@Entity
@Table(name = "COUNTRY_DESCRIPTION", uniqueConstraints={
	@UniqueConstraint(columnNames={
			"COUNTRY_ID",
			"LANGUAGE_ID"
		})
	}
)
@TableGenerator(name = "description_gen", table = "SM_SEQUENCER", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "country_description_seq", allocationSize = SchemaConstant.DESCRIPTION_ID_ALLOCATION_SIZE, initialValue = SchemaConstant.DESCRIPTION_ID_START_VALUE)
//@SequenceGenerator(name = "description_gen", sequenceName = "country_description_seq", allocationSize = SchemaConstant.DESCRIPTION_ID_SEQUENCE_START)
public class CountryDescription extends Description {
	private static final long serialVersionUID = 1L;
	
	@JsonIgnore
	@ManyToOne(targetEntity = Country.class)
	@JoinColumn(name = "COUNTRY_ID", nullable = false)
	private Country country;
	
	public CountryDescription() {
	}
	
	public CountryDescription(Language language, String name) {
		this.setLanguage(language);
		this.setName(name);
	}
	
	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}
	
}
