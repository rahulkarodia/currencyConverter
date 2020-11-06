package com.ibm.cc.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ibm.cc.model.CurrencyRef;

@Repository
public interface CurrencyDAO extends JpaRepository<CurrencyRef, Long> {
	
	public CurrencyRef findByCountryCode(String countryCode);

}
