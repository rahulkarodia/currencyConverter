package com.ibm.cc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.cc.dao.CurrencyDAO;
import com.ibm.cc.model.CurrencyDM;
import com.ibm.cc.model.CurrencyRef;

@Service
public class CurrencyService {

	@Autowired
	CurrencyDAO cd;
	
	public double getConversionFactor(String cc) {
		
		CurrencyRef cr = cd.findByCountryCode(cc);
		
		if (cr != null) {
			return cr.getFactor();
		} else {
			return 0;
		}
	}
	
	public List<CurrencyRef> findAllFactors() {
		return cd.findAll();
	}
	
	public CurrencyDM addCurrencyFactor(String cc, double factor) {
		CurrencyRef ref = new CurrencyRef(cc, factor);
		ref = cd.saveAndFlush(ref);
		if(ref != null) {
			return createCurrencyDM(ref);
		}
		return null;
	}
	
	public CurrencyDM updateCurrencyFactor(String cc, double factor) {
		deleteCountry(cc);
		CurrencyRef ref = new CurrencyRef(cc, factor);
		ref = cd.saveAndFlush(ref);
		if(ref != null) {
			return createCurrencyDM(ref);
		}
		return null;
	}
	
	public void deleteCountry(String cc) {
		CurrencyRef cRef = cd.findByCountryCode(cc);
		cd.deleteById(cRef.getCountryId());
	}

	private CurrencyDM createCurrencyDM(CurrencyRef ref) {
		CurrencyDM cDM = new CurrencyDM();
		cDM.setId(ref.getCountryId().intValue());
		cDM.setCountrycode(ref.getCountryCode());
		cDM.setValue(ref.getFactor());
		return cDM;
	}
}
