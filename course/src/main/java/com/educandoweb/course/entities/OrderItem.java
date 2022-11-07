package com.educandoweb.course.entities;

import java.io.Serializable;

import com.educandoweb.course.entities.pk.OrderItemPK;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name ="tb_order_item")
public class OrderItem implements Serializable {
	

	private static final long serialVersionUID = 1L;
	
	// Annotation Applied to a persistent field or property 
	// of an entity class or mapped superclass to denote a 
	// composite primary key that is an embeddable class. 
	//  The embeddable classmust be annotated as Embeddable. 
	
	@EmbeddedId
	private OrderItemPK id = new OrderItemPK();
	
	private Integer quantity;
	private Double price;
	
	public OrderItem() {
	}

	public OrderItem(Order order, Product product, Integer quantity, Double price) {
		super();
		id.setOrder(order);
		id.setProduct(product);
		this.quantity = quantity;
		this.price = price;
	}
	
	// Enterprise platform, method must have
	// get prefix
	public double getSubTotal() {
		return this.price*this.quantity;
	}

	// block association OrderItem -->Order
	@JsonIgnore
	public Order getOrder()
	{
		return id.getOrder();
	}
	
	public void setOrder(Order order) {
		id.setOrder(order);
	}
	
	// now when I request a order, I wanna show
	// OrderItems and for each OrderItem I wanna 
	// show product details, thus I enable access to
	// DB while rendering JSON, thus remove @JsonIgnore
	// @JsonIgnore
	public Product getProduct()
	{
		return id.getProduct();
	}
	
	public void setProduct(Product product) {
		id.setProduct(product);
	}	
	
	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
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
		OrderItem other = (OrderItem) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	

}
