package com.educandoweb.course.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.educandoweb.course.entities.Product;
import com.educandoweb.course.services.ProductService;

//******************************************************************************
//***                       REST CONTROLER (HTTP)                            ***
// Web resource for class Product -  
// Anottation @RestController tells that this classe implements a 
// REST controller
// Annotation @RequestMapping tells what is the name of endpoint that 
// is embedded in the GET message
//*****************************************************************************

@RestController
@RequestMapping(value = "/products")
public class ProductResource {

	// create injection dependency for ProductService Service Layer class
	// Spring Boot automatically does that with annotation @Autowired

	// ProductService class **MUST BE** registered as a Spring componente for
	// @Autowired works!

	@Autowired
	private ProductService service;

	//************************************************************************************
	// ENDPOINT findAll()
	//************************************************************************************	//
 
	// @GetMapping annotation indicates HTTP requisition is a GET type
	@GetMapping
	public ResponseEntity<List<Product>> findAll() {

		// instanciate a Product list calling Service Layer findAll()
		List<Product> list = service.findAll();

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
	public ResponseEntity<Product> findById(@PathVariable Long id) {
		
		// request from Product Service layer to find user by id
		Product obj = service.findById(id);
		
		// returns user from DB by Id
		return ResponseEntity.ok().body(obj);

	}

}