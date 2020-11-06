package com.ibm.cc.model;

public class CurrencyDM {

	private int id;
	private String countrycode;
	private double value;

	public CurrencyDM(int id, String countrycode, double value) {
		this.id = id;
		this.setCountrycode(countrycode);
		this.setValue(value);
	}

	public CurrencyDM() {
	}

	public CurrencyDM(String countrycode, double value) {
		this.setCountrycode(countrycode);
		this.setValue(value);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "ConcurrencyConversionDTO [id=" + id + ", countrycode=" + countrycode + ", value=" + value + "]";
	}

	public String getCountrycode() {
		return countrycode;
	}

	public void setCountrycode(String countrycode) {
		this.countrycode = countrycode;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double d) {
		this.value = d;
	}
}
