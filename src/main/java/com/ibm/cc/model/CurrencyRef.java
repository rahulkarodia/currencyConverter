package com.ibm.cc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "currency_ref")
public class CurrencyRef {

	@Id
	@Column(name = "COUNTRY_ID", updatable = false, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long countryId;
	
	@Column(name = "COUNTRY_CODE", length = 4, nullable = false, unique = true)
	private String countryCode;
	
	@Column(name = "FACTOR", nullable = false, precision = 3)
	private double factor;

	public CurrencyRef(Long countryId, String countryCode, double factor) {
		super();
		this.countryId = countryId;
		this.countryCode = countryCode;
		this.factor = factor;
	}

	public CurrencyRef(String countryCode, double factor) {
		super();
		this.countryCode = countryCode;
		this.factor = factor;
	}

	public Long getCountryId() {
		return countryId;
	}

	public void setCountryId(Long countryId) {
		this.countryId = countryId;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public double getFactor() {
		return factor;
	}

	public void setFactor(double factor) {
		this.factor = factor;
	}
}
