package com.educandoweb.course;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//************************************************************
//                 APPLICATION LOGICAL LAYERS
//*************************************************************

//|------------------------------------------------------------|
//|                  APLICATION                                |
//| * Web Browswer                                             |
//|------------------------------------------------------------|
//                          |
//                          |
//                         \ /
//|------------------------------------------------------------|
//|                 RESOURCE LAYER                             |
//|               (REST controllers)                   		   |            
//| * UserResource                                             |    
//|------------------------------------------------------------|                   
//              |
//              |
//             \ /
//-------------------------------------|  |--------------------|
//|        SERVICE LAYER               |  |                    |
//| * UserService                      |  |                    |
//|------------------------------------|  |                    |
//              |                         |                    |
//              |                         |      ENTITIES      |
//             \ /                        |                    |
//|------------------------------------|  |                    |
//|       DATA ACCESS LAYER            |  |                    |
//| * UsersRepository                  |  |                    |
//|------------------------------------|  |--------------------|


@SpringBootApplication
public class CourseApplication {

	public static void main(String[] args) {
		SpringApplication.run(CourseApplication.class, args);
	}

}
