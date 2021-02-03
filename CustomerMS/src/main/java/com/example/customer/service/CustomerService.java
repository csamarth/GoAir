package com.example.customer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.customer.dto.LoginDetails;
import com.example.customer.entity.Customer;
import com.example.customer.exception.ExceptionConstants;
import com.example.customer.exception.AirGoServiceException;
import com.example.customer.repository.CustomerRepository;


@Service
public class CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	public boolean isUserValid(LoginDetails loginDetails) throws AirGoServiceException {

		Customer customer = customerRepository.findById(loginDetails.getUserId()).get();

		if (customer == null) {
			throw new AirGoServiceException(ExceptionConstants.USER_NOT_FOUND.toString(), "User record not found");
		} else if (!(customer.getPassword().equals(loginDetails.getPassword()))) {
			throw new AirGoServiceException(ExceptionConstants.USER_INVALID.toString(), "Invalid credentials");
		}
		return true;

	}
	
	public Boolean insertUser(Customer customer) throws AirGoServiceException {

		Customer cust = customerRepository.saveAndFlush(customer);

		if (cust == null) {
			throw new AirGoServiceException("User record not found");
		} else {
			return true;
		}

	}
	
}
