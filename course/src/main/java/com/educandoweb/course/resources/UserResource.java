package com.educandoweb.course.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.educandoweb.course.entities.User;
import com.educandoweb.course.services.UserService;

//******************************************************************************
//***                       REST CONTROLER (HTTP)                            ***
// Web resource for class User -  
// Anottation @RestController tells that this classe implements a 
// REST controller
// Annotation @RequestMapping tells what is the name of endpoint that 
// is embedded in the GET message
//*****************************************************************************

@RestController
@RequestMapping(value = "/users")
public class UserResource {

	// create injection dependency for UserService Service Layer class
	// Spring Boot automatically does that with annotation @Autowired

	// UserService class **MUST BE** registered as a Spring componente for
	// @Autowired works!

	@Autowired
	private UserService service;

	//************************************************************************************
	// ENDPOINT findAll()
	//************************************************************************************	//
 
	// @GetMapping annotation indicates HTTP requisition is a GET type
	@GetMapping
	public ResponseEntity<List<User>> findAll() {

		// instanciate a User list calling Service Layer findAll()
		List<User> list = service.findAll();

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
	//************************************************************************************
 
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<User> findById(@PathVariable Long id) {
		
		// request from User Service layer to find user by id
		User obj = service.findById(id);
		
		// returns user from DB by Id
		return ResponseEntity.ok().body(obj);

	}
	
	// @PostMapping is a SET HTTP request annotation
	// RequestBody annotation indicating a method parameter should be bound to the body of the web request.
	// The body of the request is passed through an HttpMessageConverter to resolve themethod 
	// argument depending on the content type of the request. Optionally, automaticvalidation 
	// can be applied by annotating the argument with @Valid. 
	@PostMapping
	public ResponseEntity<User> insert(@RequestBody User obj){
		
		obj = service.insert(obj);
		// http 201 response expects a header with the address (URI) where you added the new 
		// row
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}

	// @DeleteMapping is a DELETE HTTP request annotation
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		service.delete(id);
		return ResponseEntity.noContent().build();   // response with no content HTTP code 204
	}
	
	// @PutMapping is a PUT HTTP request annotation
	@PutMapping(value = "/{id}")
	public ResponseEntity<User> update(@PathVariable Long id,@RequestBody User obj ){
		obj = service.update(id, obj);
		return ResponseEntity.ok().body(obj);
	}
	
}