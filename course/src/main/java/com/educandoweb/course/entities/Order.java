package com.educandoweb.course.entities;

import java.io.Serializable;
import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

//********************************************************************
// Annotations below instruct JPA how to convert objects to
// relational database model.
// @Entity --> always import specification not implementation! 
// in this case "jakarta.persistence.Entity" and not the 
// Hibernate implementation
// @Table change the name of the table "User" to "tb_user" since
// Order is a reserved word for SQL syntax
//********************************************************************

@Entity
@Table(name = "tb_order")
public class Order implements Serializable {

	// @Id annotation tells JPA wich primary key database uses, 
	// in our case is
	// @GeneratedValue annotation tells JPA that the key is autoicremented by
	// the data base GenerationType.IDENTITY
	// https://www.devmedia.com.br/jpa-como-usar-a-anotacao-id/38508


	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	// annotation @JsonFormat to format date/time in JSON file
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'",timezone = "GMT")
	private Instant moment;
	
	// Now need to add annotation to inform JPA how to handle foreign 
	// keys. Order class has a relationship many-to-one with Client class
	// So it will be used annotation @ManyToOne
	// Annotation @JoinColumn to add client id to the table with 
	// the column names as "cliend_id"
	
	@ManyToOne
	@JoinColumn(name = "cliend_id")
	private User client;
	
		
	public Order() {
	}

	public Order(Long id, Instant moment, User client) {
		super();
		this.id = id;
		this.moment = moment;
		this.client = client;
	}

	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Instant getMoment() {
		return moment;
	}
	public void setMoment(Instant moment) {
		this.moment = moment;
	}
	public User getClient() {
		return client;
	}
	public void setClient(User client) {
		this.client = client;
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
		Order other = (Order) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
		
}
