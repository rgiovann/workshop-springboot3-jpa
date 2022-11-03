package com.educandoweb.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.educandoweb.course.entities.Order;
import com.educandoweb.course.repositories.OrderRepository;

//************************************
//    *** SERVICE LAYER CLASS  ***
//***********************************

//************************************************************************
// Spring have specific annotations for registering classes:
// @Component - generic registration
// @Repository - register a Repository class
// @Service - register a Service class
// This class is a Service layer class so will be registered with @Service
//************************************************************************

@Service
public class OrderService {

	// OrderService have a dependency from Interface OrderRepository
	// @Autorwired wraps the dependency injection, coder
	// doesn´t need to worry about coding the pattern
	@Autowired
	private OrderRepository repository;

	// create method to return all users from DB
	public List<Order> findAll() {
		return repository.findAll();

	}
	
	// create method to return an user by Id
	//
	public Order findById(Long id) {
		
		// returns a Optional object, a container object which may or may not contain a non-null value.
		Optional<Order> obj = repository.findById(id);
		
		return obj.get();
		
	}

}
