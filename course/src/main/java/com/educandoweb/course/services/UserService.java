package com.educandoweb.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.educandoweb.course.entities.User;
import com.educandoweb.course.repositories.UserRepository;
import com.educandoweb.course.resources.exceptions.DatabaseException;
import com.educandoweb.course.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

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
		
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
		
	}
	
	// create method to inser user
    //
	public User insert(User obj) {
		return this.repository.save(obj);
	}
	
	
	// create method to delete user by id
    //	
	public void delete(Long id) {
		try {
		this.repository.deleteById(id);
		}
		// Exception EmptyResultDataAccessException captured with RunTimeException e.printStackTrace()
		catch (EmptyResultDataAccessException e){
			throw new ResourceNotFoundException(id);
		}
		catch (DataIntegrityViolationException e){
			throw new DatabaseException(e.getMessage());
		}
//		catch (RuntimeException e){
//			e.printStackTrace();
//		}
	}
	
	// create method to delete user by id
    //	
	public User update(Long id, User obj) {
		// Returns a reference to the entity with the given identifier. 
		// Depending on how the JPA persistence provider isimplemented 
		// this is very likely to always return an instance and throw an 
		// jakarta.persistence.EntityNotFoundException on first access. 
		//Some of them will reject invalid identifiersimmediately.
		try {
		User entity = repository.getReferenceById(id);  // doesn´t access directly the DB, it prepares the objet and then 
		                                                // access DB, findById on the contrary, access the DB
		updateData(entity, obj);
		return repository.save(entity);
		}
		catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}

	// create method to update user by id
    //	
	private void updateData(User entity, User obj) {
		entity.setName(obj.getName());
		entity.setEmail(obj.getEmail());
		entity.setPhone(obj.getPhone());

		
	}

}
