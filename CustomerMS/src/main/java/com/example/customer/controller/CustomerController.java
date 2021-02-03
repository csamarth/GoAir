package com.example.customer.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.customer.dto.LoginDetails;
import com.example.customer.entity.Customer;
import com.example.customer.exception.AirGoServiceException;
import com.example.customer.service.CustomerService;

@RestController
public class CustomerController {

	@Autowired
	CustomerService customerService;


	@RequestMapping(value = "/login", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<Boolean> Validate(@Valid @RequestBody LoginDetails login) throws AirGoServiceException {
		Boolean result = false;
		result = customerService.isUserValid(login);
		return new ResponseEntity<Boolean>(result, HttpStatus.OK);
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<Boolean> customer(@RequestBody Customer customer) throws AirGoServiceException {
		Boolean result = customerService.insertUser(customer);
		return new ResponseEntity<Boolean>(result, HttpStatus.OK);
	}
	
}
