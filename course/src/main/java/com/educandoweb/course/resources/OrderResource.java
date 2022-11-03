package com.educandoweb.course.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.educandoweb.course.entities.Order;
import com.educandoweb.course.services.OrderService;

//******************************************************************************
//***                       REST CONTROLER (HTTP)                            ***
// Web resource for class Order -  
// Anottation @RestController tells that this classe implements a 
// REST controller
// Annotation @RequestMapping tells what is the name of endpoint that 
// is embedded in the GET message
//*****************************************************************************

@RestController
@RequestMapping(value = "/orders")
public class OrderResource {

	// create injection dependency for OrderService Service Layer class
	// Spring Boot automatically does that with annotation @Autowired

	// OrderService class **MUST BE** registered as a Spring componente for
	// @Autowired works!

	@Autowired
	private OrderService service;

	//************************************************************************************
	// ENDPOINT findAll()
	//************************************************************************************	//
 
	// @GetMapping annotation indicates HTTP requisition is a GET type
	@GetMapping
	public ResponseEntity<List<Order>> findAll() {

		// instanciate a Order list calling Service Layer findAll()
		List<Order> list = service.findAll();

		// return list of users 
		// OK send success message to HTTP
		return ResponseEntity.ok().body(list);
	}

	// @GetMapping annotation indicates HTTP requisition is a GET type
	// I must inform that GET requisition ask for a parameter (value = "/{id}")
	// so that parameter of the function is a parameter of GET requisition, 
	// so the need of @PathVariable annotation

	//************************************************************************************
	// ENDPOINT findById()
	//************************************************************************************	//
 
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Order> findById(@PathVariable Long id) {
		
		// request from Order Service layer to find user by id
		Order obj = service.findById(id);
		
		// returns user from DB by Id
		return ResponseEntity.ok().body(obj);

	}

}