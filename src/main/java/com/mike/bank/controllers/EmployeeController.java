package com.mike.bank.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mike.bank.entities.Address;
import com.mike.bank.entities.Employee;
import com.mike.bank.entities.Response;

@RestController
public class EmployeeController {
	
	@Value("${name.option}")
	private int nameOption;
	
	@Value("${address.option}")
	private String addressOption;
	
	private static final String FULL = "full";
	private static final String PARTIAL = "partial";
	
	@PostMapping("/receive")
	public ResponseEntity<Object> process(@RequestBody Employee employee) {	
		Address address = employee.getAddress();
		Response response = new Response();
		String employeeName = "";
		if (nameOption == 1) {
			employeeName = employee.getEmployeeFirstName() + " " + employee.getEmployeeMiddleName() + " " + employee.getEmployeeLastName();
		} else if (nameOption == 2) {
			employeeName = employee.getEmployeeLastName() + " " + employee.getEmployeeFirstName() + " " + employee.getEmployeeMiddleName();
		}
		response.setEmployeeName(employeeName);
		
		String concatAddress = "";
		if (addressOption.equals(FULL)) {
			concatAddress = address.getAddressLine1() + " " + address.getAddressLine2() + " " + address.getCity() + " " + address.getState() + " " + address.getZip();
		} else if (addressOption.equals(PARTIAL)){
			concatAddress = address.getAddressLine1() + " " + address.getCity() + " " + address.getState() + " " + address.getZip();
		}
		response.setAddress(concatAddress);
		
	    return new ResponseEntity<>(response,HttpStatus.OK);
	}
}
