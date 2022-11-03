package com.educandoweb.course.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

//********************************************************************
// Annotations below instruct JPA how to convert objects to
// relational database model.
// @Entity --> always import specification not implementation! 
// in this case "jakarta.persistence.Entity" and not the 
// Hibernate implementation
// @Table change the name of the table "User" to "tb_user" since
// User is a reserved word for H2 databse
//********************************************************************


@Entity
@Table(name = "tb_user")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	// @Id annotation tells JPA wich primary key database uses, 
	// in our case is
	// @GeneratedValue annotation tells JPA that the key is autoicremented by
	// the data base GenerationType.IDENTITY
	// https://www.devmedia.com.br/jpa-como-usar-a-anotacao-id/38508
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	private String email;
	private String phone;
	private String password;
	
	// List is a collection
	// One client can have many orders, so this relationship
	// is addressed by @OneToMany annotation
	// Client is mapped in Order class by attribute "client"
	
	// Annotation @JsonIgnore
	// Marker annotation that indicates that the logical property that the accessor
	// (field, getter/setter method or Creator parameter[of JsonCreator-annotated 
	// constructor or factory method]) is to be ignored by introspection-basedserialization 
	// and deserialization functionality. 
	// To avoid loop caused by bidirecional relationship between User and Order.
	
	// LAZY LOADING, relationships OneToMany JPA does not allow to load the associated objets, because of
	// (potential) trafic overloading issues.
	
	@JsonIgnore
	@OneToMany(mappedBy = "client")
	private List<Order> orders = new ArrayList<Order>();

	public User() {
	}

	public User(Long id, String name, String email, String phone, String password) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public List<Order> getOrders() {
		return orders;
	}	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}