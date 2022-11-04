package com.educandoweb.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.educandoweb.course.entities.OrderItem;

//************************************************************
//                 REPOSITORY LAYER
// Lower layer responsible for accessing the database,
// it extends JPARepository interface
//***********************************************************

//***************************************************************************
// No need to register as @Repository this interface since it inherits from
// JpaRepository class this one already registered as @Repository
//***************************************************************************

public interface OrdemItemRepository extends JpaRepository<OrderItem, Long> {

}
