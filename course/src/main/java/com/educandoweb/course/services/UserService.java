package com.educandoweb.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.educandoweb.course.entities.User;
import com.educandoweb.course.repositories.UserRepository;

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
public class UserService {

	// UserService have a dependency from Interface UserRepository
	// @Autorwired wraps the dependency injection, coder
	// doesn´t need to worry about coding the pattern
	// from help: Marks a constructor, field, setter method, or config method 
	// as to be autowired bySpring's dependency injection facilities.
	@Autowired
	private UserRepository repository;

	// create method to return all users from DB
	public List<User> findAll() {
		return repository.findAll();

	}
	
	// create method to return an user by Id
	//
	public User findById(Long id) {
		
		// returns a Optional object, a container object which may or may not contain a non-null value.
		Optional<User> obj = repository.findById(id);
		
		return obj.get();
		
	}
	
	public User insert(User obj) {
		return this.repository.save(obj);
	}

}
