package com.educandoweb.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.educandoweb.course.entities.Category;
import com.educandoweb.course.repositories.CategoryRepository;

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
public class CategoryService {

	// CategoryService have a dependency from Interface CategoryRepository
	// @Autorwired wraps the dependency injection, coder
	// doesn´t need to worry about coding the pattern
	@Autowired
	private CategoryRepository repository;

	// create method to return all users from DB
	public List<Category> findAll() {
		return repository.findAll();

	}
	
	// create method to return an user by Id
	//
	public Category findById(Long id) {
		
		// returns a Optional object, a container object which may or may not contain a non-null value.
		Optional<Category> obj = repository.findById(id);
		
		return obj.get();
		
	}

}
