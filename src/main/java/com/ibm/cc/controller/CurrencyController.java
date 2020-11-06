package com.ibm.cc.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.cc.model.CurrencyDM;
import com.ibm.cc.model.CurrencyRef;
import com.ibm.cc.service.CurrencyService;

@RestController
public class CurrencyController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	CurrencyService cService;
	
	@GetMapping(path = "/currencyfactor/get/{countryCode}")
	public double getCurrencyFactor(@PathVariable String cc) {
		if (cc != null) {
			logger.info("getConversionFactor : " + cc);
			return cService.getConversionFactor(cc);
		}
		return 0;
	}
	
	@GetMapping(path = "/currencyfactor/getall")
	public List<CurrencyRef> getAllFactors() {
		return cService.findAllFactors();
	}
	
	@PutMapping(path = "/currencyfactor/add/{countrycode}/{factor}")
	public ResponseEntity<?> addCurrencyFactor(@PathVariable String countryCode, @PathVariable double factor) {
		ResponseEntity<?> response = null;
		try {
			CurrencyDM cDM = cService.addCurrencyFactor(countryCode, factor);
			if(cDM != null) {
				response = new ResponseEntity<CurrencyDM>(cDM, HttpStatus.CREATED);
			} else {
				response = new ResponseEntity<String>("Bad data. Could not add currency factor", HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			response = new ResponseEntity<String>("Unkown exception while adding a currency factor", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response;
	}
	
	@PostMapping(path = "/currencyfactor/update/{countrycode}/{factor}")
	public ResponseEntity<?> updateCurrencyFactor(@PathVariable String countryCode, @PathVariable double factor) {
		ResponseEntity<?> response = null;
		try {
			CurrencyDM cDM = cService.updateCurrencyFactor(countryCode, factor);
			if(cDM != null) {
				response = new ResponseEntity<CurrencyDM>(cDM, HttpStatus.ACCEPTED);
			} else {
				response = new ResponseEntity<String>("Bad data. Could not update currency factor", HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			response = new ResponseEntity<String>("Unkown exception while updating a currency factor " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response;
	}
	
	@DeleteMapping(path = "/currencyfactor/delete/{countrycode}")
	public ResponseEntity<?> deleteCurrencyFactor(@PathVariable String countryCode) {
		ResponseEntity<?> response = null;
		try {
			cService.deleteCountry(countryCode);
		} catch (Exception e) {
			response = new ResponseEntity<String>("Unkown exception while deleting a country", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response;
	}
}
